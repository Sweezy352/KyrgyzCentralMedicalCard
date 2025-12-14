import React, { useState, useEffect } from 'react'; // Добавляем useEffect
import { useParams, useNavigate } from 'react-router-dom';
import historyService from '../services/historyService';
import userService from '../services/userService'; // Импортируем userService
import './CreateHistoryPage.css';

const CreateHistoryPage = () => {
  const { id } = useParams(); // ID пользователя из URL
  const navigate = useNavigate();
  const [name, setName] = useState('');
  const [description, setDescription] = useState('');
  const [error, setError] = useState('');
  const [success, setSuccess] = useState('');
  const [patient, setPatient] = useState(null); // Состояние для хранения данных пациента
  const [patientLoading, setPatientLoading] = useState(true);
  const [patientError, setPatientError] = useState('');

  // Загрузка данных пациента
  useEffect(() => {
    const fetchPatient = async () => {
      try {
        const response = await userService.getById(id);
        setPatient(response.data);
      } catch (err) {
        setPatientError('Не удалось загрузить данные пациента.');
        console.error('Fetch patient error:', err);
      } finally {
        setPatientLoading(false);
      }
    };

    fetchPatient();
  }, [id]);

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError('');
    setSuccess('');

    if (!name || !description) {
      setError('Пожалуйста, заполните все поля.');
      return;
    }

    try {
      await historyService.createHistory(id, { name, description });
      setSuccess('Запись в истории болезни успешно создана!');
      // Перенаправляем обратно на профиль пользователя через 2 секунды
      setTimeout(() => {
        navigate(`/users/${id}`);
      }, 2000);
    } catch (err) {
      const errorMessage = err.response?.data?.message || 'Ошибка при создании записи в истории болезни.';
      setError(errorMessage);
      console.error('Create history error:', err);
    }
  };

  if (patientLoading) {
    return <div className="loading">Загрузка данных пациента...</div>;
  }

  if (patientError) {
    return <div className="error">{patientError}</div>;
  }

  if (!patient) {
    return <div className="error">Пациент не найден.</div>;
  }

  return (
    <div className="create-history-container">
      <div className="create-history-form">
        <h1>Создать запись в истории болезни</h1>
        <p>для пользователя: <strong>{patient.fio}</strong> (ИНН: {patient.inn})</p> {/* Обновленный текст */}
        <form onSubmit={handleSubmit}>
          <div className="form-group">
            <label htmlFor="name">Название</label>
            <input
              type="text"
              id="name"
              value={name}
              onChange={(e) => setName(e.target.value)}
              placeholder="Например: ОРВИ, Перелом руки"
              required
            />
          </div>
          <div className="form-group">
            <label htmlFor="description">Описание</label>
            <textarea
              id="description"
              value={description}
              onChange={(e) => setDescription(e.target.value)}
              placeholder="Подробное описание диагноза, лечения и рекомендаций"
              rows="5"
              required
            ></textarea>
          </div>
          {error && <p className="error-message">{error}</p>}
          {success && <p className="success-message">{success}</p>}
          <button type="submit" className="submit-button">Создать запись</button>
        </form>
      </div>
    </div>
  );
};

export default CreateHistoryPage;
