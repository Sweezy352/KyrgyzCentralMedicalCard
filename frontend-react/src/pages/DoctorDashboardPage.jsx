import React, { useState } from 'react';
import userService from '../services/userService';
import { Link, useNavigate } from 'react-router-dom';
import './DoctorDashboardPage.css';

const DoctorDashboardPage = () => {
  const [innSearch, setInnSearch] = useState('');
  const [fioSearch, setFioSearch] = useState('');
  const [qrCodeInput, setQrCodeInput] = useState('');
  const [foundUser, setFoundUser] = useState(null);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState('');
  const navigate = useNavigate();

  const handleInnSearch = async (e) => {
    e.preventDefault();
    setLoading(true);
    setError('');
    setFoundUser(null);
    try {
      const response = await userService.getByInn(innSearch);
      setFoundUser(response.data);
    } catch (err) {
      setError('Пользователь с таким ИНН не найден.');
      console.error('INN search error:', err);
    } finally {
      setLoading(false);
    }
  };

  const handleFioSearch = async (e) => {
    e.preventDefault();
    setLoading(true);
    setError('');
    setFoundUser(null);
    try {
      const response = await userService.getByFio(fioSearch);
      setFoundUser(response.data);
    } catch (err) {
      setError('Пользователь с таким ФИО не найден.');
      console.error('FIO search error:', err);
    } finally {
      setLoading(false);
    }
  };

  const handleQrCodeSearch = (e) => {
    e.preventDefault();
    setError('');
    setFoundUser(null);
    // Ожидаем, что QR-код содержит URL вида http://localhost:8080/api/users/get-by-id/{id}
    const regex = /\/api\/users\/get-by-id\/(\d+)/;
    const match = qrCodeInput.match(regex);

    if (match && match[1]) {
      const userId = match[1];
      navigate(`/users/${userId}`); // Перенаправляем на страницу профиля пользователя
    } else {
      setError('Некорректный формат QR-кода. Ожидается URL профиля пользователя.');
    }
  };

  return (
    <div className="doctor-dashboard-container">
      <h1>Панель Врача</h1>
      <p>Используйте поиск для нахождения пациентов.</p>

      <div className="search-section">
        <h2>Поиск по ИНН</h2>
        <form onSubmit={handleInnSearch}>
          <input
            type="text"
            placeholder="Введите ИНН пациента"
            value={innSearch}
            onChange={(e) => setInnSearch(e.target.value)}
          />
          <button type="submit">Найти по ИНН</button>
        </form>
      </div>

      <div className="search-section">
        <h2>Поиск по ФИО</h2>
        <form onSubmit={handleFioSearch}>
          <input
            type="text"
            placeholder="Введите ФИО пациента"
            value={fioSearch}
            onChange={(e) => setFioSearch(e.target.value)}
          />
          <button type="submit">Найти по ФИО</button>
        </form>
      </div>

      <div className="search-section">
        <h2>Поиск по QR-коду</h2>
        <form onSubmit={handleQrCodeSearch}>
          <input
            type="text"
            placeholder="Вставьте URL из QR-кода"
            value={qrCodeInput}
            onChange={(e) => setQrCodeInput(e.target.value)}
          />
          <button type="submit">Перейти по QR</button>
        </form>
      </div>

      {loading && <div className="loading">Поиск...</div>}
      {error && <div className="error">{error}</div>}

      {foundUser && (
        <div className="found-user-card">
          <h3>Найденный пациент:</h3>
          <p>{foundUser.fio} ({foundUser.inn})</p>
          <Link to={`/users/${foundUser.id}`} className="view-profile-button">Профиль пациента</Link>
        </div>
      )}
    </div>
  );
};

export default DoctorDashboardPage;
