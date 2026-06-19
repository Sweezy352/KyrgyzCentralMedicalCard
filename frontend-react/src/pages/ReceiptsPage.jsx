import React, { useEffect, useState } from 'react';
import receiptService from '../services/receiptService';
import userService from '../services/userService';

const ReceiptsPage = () => {
  const [receipts, setReceipts] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');

  useEffect(() => {
    userService.getCurrentUser()
      .then(res => receiptService.getReceiptsByUserId(res.data.id))
      .then(res => setReceipts(res.data))
      .catch(() => setError('Не удалось загрузить рецепты.'))
      .finally(() => setLoading(false));
  }, []);

  if (loading) return <div className="loading-state"><div className="loading-spinner" />Загрузка...</div>;
  if (error) return <div className="alert alert-error">{error}</div>;

  return (
    <div>
      <h1 className="page-title">Мои Рецепты</h1>
      {receipts.length === 0 ? (
        <div className="empty-state">Рецептов нет.</div>
      ) : (
        <div className="card-list">
          {receipts.map(r => (
            <div key={r.id} className="card">
              <div className="card-header">
                <h3>{r.name}</h3>
                {r.number && <span className="badge badge-blue">№{r.number}</span>}
              </div>
              <div className="card-body"><p>{r.description}</p></div>
              <div className="card-footer">
                <span>{new Date(r.dateCreated).toLocaleDateString('ru-RU')}</span>
                {r.userViewDoc && <span> {r.userViewDoc.fio}</span>}
              </div>
            </div>
          ))}
        </div>
      )}
    </div>
  );
};

export default ReceiptsPage;
