import React, { useEffect, useState } from 'react';
import diagnosisService from '../services/diagnosisService';
import userService from '../services/userService';
import './DiagnosesPage.css';

const DiagnosesPage = () => {
  const [diagnoses, setDiagnoses] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');

  useEffect(() => {
    const fetchDiagnoses = async () => {
      try {
        // 1. Получаем данные текущего пользователя, чтобы узнать его ID
        const userResponse = await userService.getCurrentUser();
        const userId = userResponse.data.id;

        if (userId) {
          // 2. Загружаем диагнозы для этого пользователя
          const diagnosesResponse = await diagnosisService.getAllDiagnosesByUserId(userId);
          setDiagnoses(diagnosesResponse.data);
        } else {
          setError('Не удалось определить пользователя.');
        }
      } catch (err) {
        setError('Не удалось загрузить список диагнозов.');
        console.error('Fetch diagnoses error:', err);
      } finally {
        setLoading(false);
      }
    };

    fetchDiagnoses();
  }, []);

  if (loading) {
    return <div className="loading">Загрузка диагнозов...</div>;
  }

  if (error) {
    return <div className="error">{error}</div>;
  }

  return (
    <div className="diagnoses-container">
      <h1>Мои Диагнозы</h1>
      {diagnoses.length > 0 ? (
        <div className="diagnoses-list">
          {diagnoses.map((diagnosis) => (
            <div key={diagnosis.id} className="diagnosis-card">
              <h3>{diagnosis.name}</h3>
              <p>{diagnosis.description}</p>
              <div className="card-footer">
                <span>Статус: {diagnosis.status || 'Не указан'}</span>
                <span>Дата: {new Date(diagnosis.dateCreated).toLocaleDateString()}</span>
                {diagnosis.userDoc && ( // Проверяем наличие userDoc
                  <span>Врач: {diagnosis.userDoc.fio} (ИНН: {diagnosis.userDoc.inn})</span>
                )}
              </div>
            </div>
          ))}
        </div>
      ) : (
        <p>У вас пока нет зарегистрированных диагнозов.</p>
      )}
    </div>
  );
};

export default DiagnosesPage;
