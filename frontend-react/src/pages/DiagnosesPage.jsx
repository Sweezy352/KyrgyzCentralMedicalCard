import React, { useEffect, useState } from 'react';
import diagnosisService from '../services/diagnosisService';
import userService from '../services/userService';

const DiagnosesPage = () => {
  const [diagnoses, setDiagnoses] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');

  useEffect(() => {
    userService.getCurrentUser()
      .then(res => diagnosisService.getAllDiagnosesByUserId(res.data.id))
      .then(res => setDiagnoses(res.data))
      .catch(() => setError('Не удалось загрузить диагнозы.'))
      .finally(() => setLoading(false));
  }, []);

  if (loading) return <div className="loading-state"><div className="loading-spinner" />Загрузка...</div>;
  if (error) return <div className="alert alert-error">{error}</div>;

  return (
    <div>
      <h1 className="page-title">Мои Диагнозы</h1>
      {diagnoses.length === 0 ? (
        <div className="empty-state">🔬 Диагнозов не найдено.</div>
      ) : (
        <div className="card-list">
          {diagnoses.map(d => (
            <div key={d.id} className="card">
              <div className="card-header">
                <h3>{d.name}</h3>
                {d.status && <span className="badge badge-blue">{d.status}</span>}
              </div>
              <div className="card-body"><p>{d.description}</p></div>
              <div className="card-footer">
                <span>{new Date(d.dateCreated).toLocaleDateString('ru-RU')}</span>
                {d.userDoc && <span>{d.userDoc.fio}</span>}
              </div>
            </div>
          ))}
        </div>
      )}
    </div>
  );
};

export default DiagnosesPage;
