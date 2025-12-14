import React, { useEffect, useState } from 'react';
import receiptService from '../services/receiptService';
import userService from '../services/userService';
import './ReceiptsPage.css'; // Создадим этот файл стилей

const ReceiptsPage = () => {
  const [receipts, setReceipts] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');

  useEffect(() => {
    const fetchReceipts = async () => {
      try {
        // 1. Получаем данные текущего пользователя, чтобы узнать его ID
        const userResponse = await userService.getCurrentUser();
        const userId = userResponse.data.id;

        if (userId) {
          // 2. Загружаем рецепты для этого пользователя
          const receiptsResponse = await receiptService.getReceiptsByUserId(userId);
          setReceipts(receiptsResponse.data);
        } else {
          setError('Не удалось определить пользователя.');
        }
      } catch (err) {
        setError('Не удалось загрузить рецепты.');
        console.error('Fetch receipts error:', err);
      } finally {
        setLoading(false);
      }
    };

    fetchReceipts();
  }, []);

  if (loading) {
    return <div className="loading">Загрузка рецептов...</div>;
  }

  if (error) {
    return <div className="error">{error}</div>;
  }

  return (
    <div className="receipts-container">
      <h1>Мои Рецепты</h1>
      {receipts.length > 0 ? (
        <div className="receipts-list">
          {receipts.map((receipt) => (
            <div key={receipt.id} className="receipt-card">
              <h3>{receipt.name}</h3>
              <p><strong>Номер:</strong> {receipt.number}</p>
              <p>{receipt.description}</p>
              <div className="card-footer">
                <span>Выписан: {receipt.userViewDoc ? receipt.userViewDoc.fio : 'Неизвестно'}</span>
                <span>Дата: {new Date(receipt.dateCreated).toLocaleDateString()}</span>
              </div>
            </div>
          ))}
        </div>
      ) : (
        <p>У вас пока нет выписанных рецептов.</p>
      )}
    </div>
  );
};

export default ReceiptsPage;
