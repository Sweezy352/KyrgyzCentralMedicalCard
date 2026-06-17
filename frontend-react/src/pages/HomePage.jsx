import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import userService from '../services/userService';
import allergieService from '../services/allergieService';
import historyService from '../services/historyService';
import receiptService from '../services/receiptService';
import insuranceService from '../services/insuranceService';
import diagnosisService from '../services/diagnosisService';
import './HomePage.css';

const TABS = ['Информация', 'История', 'Аллергии', 'Рецепты', 'Диагнозы'];

const HomePage = () => {
  const [user, setUser] = useState(null);
  const [history, setHistory] = useState([]);
  const [allergies, setAllergies] = useState([]);
  const [receipts, setReceipts] = useState([]);
  const [insurance, setInsurance] = useState([]);
  const [diagnoses, setDiagnoses] = useState([]);
  const [activeTab, setActiveTab] = useState('Информация');
  const [showQr, setShowQr] = useState(false);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');

  useEffect(() => {
    userService.getCurrentUser()
      .then(async (res) => {
        const u = res.data;
        setUser(u);
        const [h, a, r, ins, d] = await Promise.allSettled([
          historyService.getHistoryByUserId(u.id),
          allergieService.getAllergiesByUserId(u.id),
          receiptService.getReceiptsByUserId(u.id),
          insuranceService.getUserInsurancesByUserId(u.id),
          diagnosisService.getAllDiagnosesByUserId(u.id),
        ]);
        if (h.status === 'fulfilled') setHistory(h.value.data);
        if (a.status === 'fulfilled') setAllergies(a.value.data);
        if (r.status === 'fulfilled') setReceipts(r.value.data);
        if (ins.status === 'fulfilled') setInsurance(ins.value.data);
        if (d.status === 'fulfilled') setDiagnoses(d.value.data);
      })
      .catch(() => setError('Не удалось загрузить данные.'))
      .finally(() => setLoading(false));
  }, []);

const fmt = (d) => d ? new Date(d).toLocaleDateString('ru-RU') : '—';

  if (loading) return <div className="loading-state"><div className="loading-spinner" />Загрузка...</div>;
  if (error) return <div className="alert alert-error">{error}</div>;
  if (!user) return null;

  const initials = user.fio?.split(' ').map(w => w[0]).join('').slice(0, 2).toUpperCase();

  return (
    <div className="hp-root">

      <h2 className="hp-page-title">Профиль</h2>

      <div className="hp-header-card">
        <div className="hp-avatar">{initials}</div>
        <div className="hp-header-info">
          <h1 className="hp-name">{user.fio}</h1>
          <p className="hp-inn">ИНН: {user.inn}</p>
          <span className="hp-role-badge">Пациент</span>
        </div>
      </div>
      <button className="hp-qr-btn-wide" onClick={() => setShowQr(true)}>
        Показать QR-код
      </button>

      {showQr && (
        <div className="hp-qr-overlay" onClick={() => setShowQr(false)}>
          <div className="hp-qr-modal" onClick={e => e.stopPropagation()}>
            <p className="hp-qr-modal-title">Ваш QR-код</p>
            <img
              src={`/api/qrcode/qr-code/users/${user.id}`}
              alt="QR-код"
              className="hp-qr-canvas"
            />
            <p className="hp-qr-inn">{user.inn}</p>
            <button className="hp-pill-btn" onClick={() => setShowQr(false)}>Скрыть</button>
          </div>
        </div>
      )}

      <div className="hp-tabs">
        {TABS.map(tab => (
          <button
            key={tab}
            className={`hp-tab ${activeTab === tab ? 'active' : ''}`}
            onClick={() => setActiveTab(tab)}
          >
            {tab}
          </button>
        ))}
      </div>

      {/* TAB: Информация */}
      {activeTab === 'Информация' && (
        <div className="hp-tab-content">
          <div className="hp-info-grid">

            <div className="hp-info-card">
              <p className="hp-info-label">ФИО</p>
              <p className="hp-info-value">{user.fio}</p>
              <p className="hp-info-label" style={{ marginTop: '0.75rem' }}>Пол</p>
              <p className="hp-info-value">{user.gender || '—'}</p>
            </div>

            <div className="hp-info-card">
              <p className="hp-info-label">Дата рождения</p>
              <p className="hp-info-value">{user.birthDate ? new Date(user.birthDate).toLocaleDateString('ru-RU') : '—'}</p>
              <button className="hp-pill-btn">Изменить дату</button>
            </div>

            <div className="hp-info-card">
              <p className="hp-info-label">Чрезвычайный номер</p>
              <p className="hp-info-value">{user.emergencyPhone || '—'}</p>
              <button className="hp-pill-btn">Изменить</button>
            </div>

            <div className="hp-info-card">
              <p className="hp-info-label">Страховка</p>
              <p className="hp-info-value">
                {insurance.length > 0 ? `${insurance.length} полис(ов)` : 'Нет страховки'}
              </p>
              <Link to="/insurance" className="hp-pill-btn">Посмотреть</Link>
            </div>

            <div className="hp-info-card hp-info-card--full">
              <div className="hp-blood-row">
                <div>
                  <p className="hp-info-label">Группа крови</p>
                  <p className="hp-info-value">{user.bloodGroup || '—'}</p>
                </div>
                <div>
                  <p className="hp-info-label">Резус-фактор</p>
                  <p className="hp-info-value">{user.rhFactor ? `Rh${user.rhFactor}` : '—'}</p>
                </div>
              </div>
            </div>

          </div>


        </div>
      )}

      {/* TAB: История */}
      {activeTab === 'История' && (
        <div className="hp-tab-content">
          {history.length === 0 ? (
            <div className="empty-state">Записей в истории болезней нет.</div>
          ) : (
            <div className="hp-history-list">
              {history.map((rec, i) => (
                <div key={rec.id} className={`hp-history-card ${i === 0 ? 'hp-history-card--active' : ''}`}>
                  <div className="hp-history-top">
                    <span className="hp-history-title">{rec.name}</span>
                    <span className="hp-history-date">{fmt(rec.dateCreated)}</span>
                  </div>
                  <p className="hp-history-desc">{rec.description}</p>
                  {rec.userDoc && <p className="hp-history-doc">Врач: {rec.userDoc.fio}</p>}
                </div>
              ))}
            </div>
          )}
        </div>
      )}

      {/* TAB: Аллергии */}
      {activeTab === 'Аллергии' && (
        <div className="hp-tab-content">
          {allergies.length === 0 ? (
            <div className="empty-state">Аллергий не зарегистрировано.</div>
          ) : (
            <div className="hp-allergy-grid">
              {allergies.map(a => (
                <div key={a.id} className="hp-allergy-card">
                  <h3 className="hp-allergy-title">{a.name}</h3>
                  <p className="hp-allergy-desc">{a.description}</p>
                  <p className="hp-allergy-warn">
                    При экстренной ситуации нажмите на блок ради должных инструкций
                  </p>
                </div>
              ))}
            </div>
          )}
        </div>
      )}

      {/* TAB: Рецепты */}
      {activeTab === 'Рецепты' && (
        <div className="hp-tab-content">
          {receipts.length === 0 ? (
            <div className="empty-state">Рецептов нет.</div>
          ) : (
            <div className="hp-receipt-list">
              {receipts.map(r => (
                <div key={r.id} className="hp-receipt-card">
                  <div className="hp-receipt-top">
                    <span className="hp-receipt-title">Рецепт № {r.number || r.id}</span>
                    <span className="hp-receipt-date">{fmt(r.dateCreated)}</span>
                  </div>
                  <p className="hp-receipt-name">{r.name}</p>
                  <p className="hp-receipt-desc">{r.description}</p>
                  {r.userViewDoc && <p className="hp-receipt-doc">Врач: {r.userViewDoc.fio}</p>}
                </div>
              ))}
            </div>
          )}
        </div>
      )}

      {/* TAB: Диагнозы */}
      {activeTab === 'Диагнозы' && (
        <div className="hp-tab-content">
          {diagnoses.length === 0 ? (
            <div className="empty-state">Диагнозов не найдено.</div>
          ) : (
            <div className="hp-history-list">
              {diagnoses.map((d, i) => (
                <div key={d.id} className={`hp-history-card ${i === 0 ? 'hp-history-card--active' : ''}`}>
                  <div className="hp-history-top">
                    <span className="hp-history-title">{d.name}</span>
                    <span className="hp-history-date">{fmt(d.dateCreated)}</span>
                  </div>
                  <p className="hp-history-desc">{d.description}</p>
                  {d.status && <p className="hp-history-doc">Статус: {d.status}</p>}
                  {d.userDoc && <p className="hp-history-doc">Врач: {d.userDoc.fio}</p>}
                </div>
              ))}
            </div>
          )}
        </div>
      )}

    </div>
  );
};

export default HomePage;
