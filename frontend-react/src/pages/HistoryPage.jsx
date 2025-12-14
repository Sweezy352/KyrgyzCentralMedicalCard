import React, { useEffect, useState } from 'react';
import historyService from '../services/historyService';
import userService from '../services/userService';
import './HistoryPage.css';

const HistoryPage = () => {
  const [history, setHistory] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');

  useEffect(() => {
    const fetchHistory = async () => {
      try {
        // 1. Получаем данные текущего пользователя, чтобы узнать его ID
        const userResponse = await userService.getCurrentUser();
        const userId = userResponse.data.id;

        if (userId) {
          // 2. Загружаем историю болезней для этого пользователя
          const historyResponse = await historyService.getHistoryByUserId(userId);
          setHistory(historyResponse.data);
        } else {
          setError('Не удалось определить пользователя.');
        }
      } catch (err) {
        setError('Не удалось загрузить историю болезней.');
        console.error('Fetch history error:', err);
      } finally {
        setLoading(false);
      }
    };

    fetchHistory();
  }, []);

  if (loading) {
    return <div className="loading">Загрузка истории...</div>;
  }

  if (error) {
    return <div className="error">{error}</div>;
  }

  return (
    <div className="history-container">
      <h1>История болезней</h1>
      {history.length > 0 ? (
        <div className="history-list">
          {history.map((record) => (
            <div key={record.id} className="history-card">
              <h3>{record.name}</h3>
              <p>{record.description}</p>
              <div className="card-footer">
                <span>Дата: {new Date(record.dateCreated).toLocaleDateString()}</span>
                {record.userDoc && ( // Проверяем наличие userDoc
                  <span>Врач: {record.userDoc.fio} (ИНН: {record.userDoc.inn})</span>
                )}
              </div>
            </div>
          ))}
        </div>
      ) : (
        <p>У вас пока нет записей в истории болезней.</p>
      )}
    </div>
  );
};

export default HistoryPage;
