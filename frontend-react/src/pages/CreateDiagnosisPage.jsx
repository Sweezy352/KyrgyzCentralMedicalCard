import React, { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import diagnosisService from '../services/diagnosisService';
import userService from '../services/userService';
import './CreateDiagnosisPage.css'; // Создадим этот файл стилей

const CreateDiagnosisPage = () => {
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
      await diagnosisService.createDiagnosis(id, { name, description });
      setSuccess('Диагноз успешно добавлен!');
      // Перенаправляем обратно на профиль пользователя через 2 секунды
      setTimeout(() => {
        navigate(`/users/${id}`);
      }, 2000);
    } catch (err) {
      const errorMessage = err.response?.data?.message || 'Ошибка при добавлении диагноза.';
      setError(errorMessage);
      console.error('Create diagnosis error:', err);
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
    <div className="create-diagnosis-container">
      <div className="create-diagnosis-form">
        <h1>Добавить диагноз</h1>
        <p>для пользователя: <strong>{patient.fio}</strong> (ИНН: {patient.inn})</p>
        <form onSubmit={handleSubmit}>
          <div className="form-group">
            <label htmlFor="name">Название диагноза</label>
            <input
              type="text"
              id="name"
              value={name}
              onChange={(e) => setName(e.target.value)}
              placeholder="Например: ОРВИ, Гипертония"
              required
            />
          </div>
          <div className="form-group">
            <label htmlFor="description">Описание диагноза</label>
            <textarea
              id="description"
              value={description}
              onChange={(e) => setDescription(e.target.value)}
              placeholder="Подробное описание диагноза"
              rows="5"
              required
            ></textarea>
          </div>
          {error && <p className="error-message">{error}</p>}
          {success && <p className="success-message">{success}</p>}
          <button type="submit" className="submit-button">Добавить диагноз</button>
        </form>
      </div>
    </div>
  );
};

export default CreateDiagnosisPage;
