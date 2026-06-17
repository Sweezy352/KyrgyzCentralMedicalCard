import React, { useEffect, useState } from 'react';
import historyService from '../services/historyService';
import userService from '../services/userService';

const HistoryPage = () => {
  const [history, setHistory] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');

  useEffect(() => {
    userService.getCurrentUser()
      .then(res => historyService.getHistoryByUserId(res.data.id))
      .then(res => setHistory(res.data))
      .catch(() => setError('Не удалось загрузить историю болезней.'))
      .finally(() => setLoading(false));
  }, []);

  if (loading) return <div className="loading-state"><div className="loading-spinner" />Загрузка...</div>;
  if (error) return <div className="alert alert-error">{error}</div>;

  return (
    <div>
      <h1 className="page-title">История болезней</h1>
      {history.length === 0 ? (
        <div className="empty-state">Записей в истории болезней нет.</div>
      ) : (
        <div className="card-list">
          {history.map(record => (
            <div key={record.id} className="card">
              <div className="card-header"><h3>{record.name}</h3></div>
              <div className="card-body"><p>{record.description}</p></div>
              <div className="card-footer">
                <span>{new Date(record.dateCreated).toLocaleDateString('ru-RU')}</span>
                {record.userDoc && <span>{record.userDoc.fio}</span>}
              </div>
            </div>
          ))}
        </div>
      )}
    </div>
  );
};

export default HistoryPage;
