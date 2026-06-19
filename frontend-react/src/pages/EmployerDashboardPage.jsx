import React, { useEffect, useState } from 'react';
import analyticsService from '../services/analyticsService';
import './AdminPanelPage.css';

const toIso = (d) => d.toISOString().slice(0, 19);
const fmtDate = (d) => d ? new Date(d).toLocaleDateString('ru-RU') : '—';
const HEALTH_GROUP_COLORS = { I: '#22c55e', II: '#84cc16', III: '#eab308', IV: '#f97316', V: '#ef4444' };

const EmployerDashboardPage = () => {
  const now = new Date();
  const [from, setFrom] = useState(toIso(new Date(now.getFullYear(), now.getMonth() - 3, now.getDate())));
  const [to, setTo] = useState(toIso(now));
  const [showFilter, setShowFilter] = useState(false);
  const [data, setData] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');

  const load = (dateFrom, dateTo) => {
    setLoading(true);
    setError('');
    analyticsService.getMyEmployerDashboard(dateFrom, dateTo)
      .then(res => setData(res.data))
      .catch(err => {
        if (err.response?.status === 403) {
          setError('Нет доступа. Убедитесь что вы привязаны к организации.');
        } else {
          setError('Не удалось загрузить аналитику.');
        }
      })
      .finally(() => setLoading(false));
  };

  useEffect(() => { load(from, to); }, []);

  const handleApply = (e) => {
    e.preventDefault();
    setShowFilter(false);
    load(from, to);
  };

  const maxCount = (arr, key) => Math.max(...arr.map(x => x[key] || 0), 1);

  return (
    <div>
      <div style={{ display: 'flex', alignItems: 'center', justifyContent: 'space-between', marginBottom: '1.5rem' }}>
        <h1 className="page-title" style={{ margin: 0 }}>Аналитика компании</h1>
        <button className="btn btn-outline" onClick={() => setShowFilter(v => !v)}>
          {showFilter ? 'Скрыть фильтр' : 'Выбрать период'}
        </button>
      </div>

      {showFilter && (
        <div className="card" style={{ maxWidth: 480, marginBottom: '1.5rem' }}>
          <form onSubmit={handleApply}>
            <div className="search-grid">
              <div className="form-group">
                <label>С</label>
                <input type="datetime-local" value={from} onChange={e => setFrom(e.target.value)} required />
              </div>
              <div className="form-group">
                <label>По</label>
                <input type="datetime-local" value={to} onChange={e => setTo(e.target.value)} required />
              </div>
            </div>
            <div className="form-actions">
              <button type="submit" className="btn btn-primary">Применить</button>
              <button type="button" className="btn btn-outline" onClick={() => setShowFilter(false)}>Отмена</button>
            </div>
          </form>
        </div>
      )}

      {!showFilter && data && (
        <p style={{ color: 'var(--text-muted)', fontSize: '0.875rem', marginBottom: '1.25rem' }}>
          Период: {fmtDate(from)} — {fmtDate(to)}
        </p>
      )}

      {loading && <div className="loading-state"><div className="loading-spinner" />Загрузка...</div>}
      {error && <div className="alert alert-error">{error}</div>}

      {!loading && data && (
        <div>
          <div className="search-grid">
            <div className="card">
              <p style={{ color: 'var(--text-muted)', fontSize: '0.85rem' }}>Всего сотрудников</p>
              <p style={{ fontSize: '2rem', fontWeight: 700, color: 'var(--primary)' }}>{data.totalEmployees}</p>
            </div>
            <div className="card">
              <p style={{ color: 'var(--text-muted)', fontSize: '0.85rem' }}>Просроченных осмотров</p>
              <p style={{ fontSize: '2rem', fontWeight: 700, color: '#ef4444' }}>{data.overdueCheckups}</p>
            </div>
          </div>

          {data.healthGroups && Object.keys(data.healthGroups).length > 0 && (
            <div className="card" style={{ marginTop: '1.25rem' }}>
              <h3 className="search-title">Группы здоровья</h3>
              <div style={{ display: 'flex', gap: '0.75rem', flexWrap: 'wrap' }}>
                {Object.entries(data.healthGroups).map(([group, count]) => (
                  <div key={group} style={{
                    display: 'flex', flexDirection: 'column', alignItems: 'center',
                    background: 'var(--bg)', borderRadius: 12, padding: '0.75rem 1.25rem',
                    border: `2px solid ${HEALTH_GROUP_COLORS[group] || 'var(--border)'}`,
                    minWidth: 80
                  }}>
                    <span style={{ fontSize: '1.5rem', fontWeight: 700, color: HEALTH_GROUP_COLORS[group] || 'var(--text)' }}>
                      {count}
                    </span>
                    <span style={{ fontSize: '0.8rem', color: 'var(--text-muted)' }}>Группа {group}</span>
                  </div>
                ))}
              </div>
            </div>
          )}

          {data.sickDaysByDepartment?.length > 0 && (
            <div className="card" style={{ marginTop: '1.25rem' }}>
              <h3 className="search-title">Больничные дни по отделам</h3>
              <table style={{ width: '100%', borderCollapse: 'collapse' }}>
                <thead>
                  <tr style={{ borderBottom: '1px solid var(--border)' }}>
                    <th style={{ textAlign: 'left', padding: '0.5rem 0', color: 'var(--text-muted)', fontWeight: 500 }}>Отдел</th>
                    <th style={{ textAlign: 'right', padding: '0.5rem 0', color: 'var(--text-muted)', fontWeight: 500 }}>Дней</th>
                  </tr>
                </thead>
                <tbody>
                  {data.sickDaysByDepartment.map((d, i) => (
                    <tr key={i} style={{ borderBottom: '1px solid var(--border)' }}>
                      <td style={{ padding: '0.5rem 0' }}>{d.department || '—'}</td>
                      <td style={{ padding: '0.5rem 0', textAlign: 'right', fontWeight: 600 }}>{d.sickDays}</td>
                    </tr>
                  ))}
                </tbody>
              </table>
            </div>
          )}

          {data.topDiagnoses?.length > 0 && (
            <div className="card" style={{ marginTop: '1.25rem' }}>
              <h3 className="search-title">Топ заболеваний сотрудников</h3>
              {data.topDiagnoses.map((d, i) => (
                <div key={i} style={{ marginBottom: '0.6rem' }}>
                  <div style={{ display: 'flex', justifyContent: 'space-between', marginBottom: '0.25rem' }}>
                    <span style={{ fontSize: '0.875rem' }}>{d.name || '—'}</span>
                    <span style={{ fontSize: '0.875rem', fontWeight: 600 }}>{d.count}</span>
                  </div>
                  <div style={{ background: 'var(--border)', borderRadius: 4, height: 8 }}>
                    <div style={{
                      background: 'var(--primary)', borderRadius: 4, height: 8,
                      width: `${Math.min(100, (d.count / maxCount(data.topDiagnoses, 'count')) * 100)}%`,
                      transition: 'width 0.4s ease'
                    }} />
                  </div>
                </div>
              ))}
            </div>
          )}
        </div>
      )}
    </div>
  );
};

export default EmployerDashboardPage;
