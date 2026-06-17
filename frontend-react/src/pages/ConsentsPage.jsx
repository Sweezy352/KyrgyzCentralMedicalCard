import React, { useEffect, useState } from 'react';
import consentService from '../services/consentService';
import userService from '../services/userService';
import organizationService from '../services/organizationService';
import './CreatePage.css';

const CONSENT_LABELS = {
  FULL_ACCESS: 'Полный доступ',
  READ_ONLY: 'Только чтение',
  EMERGENCY_ONLY: 'Экстренный',
};

const fmt = (d) => d ? new Date(d).toLocaleDateString('ru-RU') : 'Бессрочно';

const ConsentsPage = () => {
  const [user, setUser] = useState(null);
  const [consents, setConsents] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');
  const [showForm, setShowForm] = useState(false);
  const [form, setForm] = useState({ organizationId: '', consentType: 'READ_ONLY', expiresAt: '' });
  const [formError, setFormError] = useState('');
  const [formLoading, setFormLoading] = useState(false);

  useEffect(() => {
    userService.getCurrentUser()
      .then(res => {
        setUser(res.data);
        return consentService.getByPatientId(res.data.id);
      })
      .then(res => setConsents(res.data))
      .catch(() => setError('Не удалось загрузить согласия.'))
      .finally(() => setLoading(false));
  }, []);

  const set = (field) => (e) => setForm({ ...form, [field]: e.target.value });

  const handleGrant = async (e) => {
    e.preventDefault();
    setFormError('');
    setFormLoading(true);
    try {
      await organizationService.getById(form.organizationId);
      await consentService.grant(user.id, {
        organizationId: Number(form.organizationId),
        consentType: form.consentType,
        expiresAt: form.expiresAt || null,
      });
      const res = await consentService.getByPatientId(user.id);
      setConsents(res.data);
      setShowForm(false);
      setForm({ organizationId: '', consentType: 'READ_ONLY', expiresAt: '' });
    } catch {
      setFormError('Ошибка. Проверьте ID организации.');
    } finally {
      setFormLoading(false);
    }
  };

  const handleRevoke = async (consentId) => {
    try {
      await consentService.revoke(user.id, consentId);
      setConsents(consents.filter(c => c.id !== consentId));
    } catch {
      setError('Не удалось отозвать согласие.');
    }
  };

  if (loading) return <div className="loading-state"><div className="loading-spinner" />Загрузка...</div>;
  if (error) return <div className="alert alert-error">{error}</div>;

  return (
    <div>
      <h1 className="page-title">Мои согласия</h1>

      {consents.length === 0 ? (
        <div className="empty-state">Вы не выдали ни одного согласия.</div>
      ) : (
        <div className="hp-history-list" style={{ marginBottom: '1.5rem' }}>
          {consents.map(c => (
            <div key={c.id} className="hp-history-card">
              <div className="hp-history-top">
                <span className="hp-history-title">{c.organizationName}</span>
                <span className="hp-history-date">{CONSENT_LABELS[c.consentType] || c.consentType}</span>
              </div>
              <p className="hp-history-desc">
                Выдано: {fmt(c.grantedAt)} · Действует до: {fmt(c.expiresAt)}
              </p>
              {!c.revokedAt && (
                <button className="btn btn-danger" style={{ marginTop: '0.5rem' }}
                  onClick={() => handleRevoke(c.id)}>
                  Отозвать
                </button>
              )}
              {c.revokedAt && (
                <span style={{ color: 'var(--text-muted)', fontSize: '0.85rem' }}>
                  Отозвано: {fmt(c.revokedAt)}
                </span>
              )}
            </div>
          ))}
        </div>
      )}

      {!showForm && (
        <button className="btn btn-primary" onClick={() => setShowForm(true)}>
          Добавить согласие
        </button>
      )}

      {showForm && (
        <div className="card" style={{ maxWidth: 480 }}>
          <form onSubmit={handleGrant}>
            <div className="form-group">
              <label>ID организации</label>
              <input type="number" placeholder="Введите ID организации"
                value={form.organizationId} onChange={set('organizationId')} required />
            </div>
            <div className="form-group">
              <label>Тип доступа</label>
              <select value={form.consentType} onChange={set('consentType')}>
                <option value="FULL_ACCESS">Полный доступ</option>
                <option value="READ_ONLY">Только чтение</option>
                <option value="EMERGENCY_ONLY">Экстренный</option>
              </select>
            </div>
            <div className="form-group">
              <label>Срок действия (необязательно)</label>
              <input type="datetime-local" value={form.expiresAt} onChange={set('expiresAt')} />
            </div>
            {formError && <div className="alert alert-error">{formError}</div>}
            <div className="form-actions">
              <button type="submit" className="btn btn-primary" disabled={formLoading}>
                {formLoading ? 'Сохранение...' : 'Выдать согласие'}
              </button>
              <button type="button" className="btn btn-outline" onClick={() => setShowForm(false)}>
                Отмена
              </button>
            </div>
          </form>
        </div>
      )}
    </div>
  );
};

export default ConsentsPage;
