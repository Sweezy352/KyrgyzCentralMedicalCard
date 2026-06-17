import React, { useEffect, useState } from 'react';
import allergieService from '../services/allergieService';
import userService from '../services/userService';

const AllergiesPage = () => {
  const [allergies, setAllergies] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');

  useEffect(() => {
    userService.getCurrentUser()
      .then(res => allergieService.getAllergiesByUserId(res.data.id))
      .then(res => setAllergies(res.data))
      .catch(() => setError('Не удалось загрузить аллергии.'))
      .finally(() => setLoading(false));
  }, []);

  if (loading) return <div className="loading-state"><div className="loading-spinner" />Загрузка...</div>;
  if (error) return <div className="alert alert-error">{error}</div>;

  return (
    <div>
      <h1 className="page-title">Мои Аллергии</h1>
      {allergies.length === 0 ? (
        <div className="empty-state">Аллергий не зарегистрировано.</div>
      ) : (
        <div className="card-list">
          {allergies.map(a => (
            <div key={a.id} className="card">
              <div className="card-header">
                <h3>{a.name}</h3>
                <span className={`badge ${a.status ? 'badge-green' : 'badge-gray'}`}>
                  {a.status ? 'Активна' : 'Неактивна'}
                </span>
              </div>
              <div className="card-body"><p>{a.description}</p></div>
              <div className="card-footer">
                <span>{new Date(a.dateCreated).toLocaleDateString('ru-RU')}</span>
                {a.userViewDoc && <span>{a.userViewDoc.fio}</span>}
              </div>
            </div>
          ))}
        </div>
      )}
    </div>
  );
};

export default AllergiesPage;
