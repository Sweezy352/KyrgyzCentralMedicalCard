import React, { useState } from 'react';
import userService from '../services/userService';
import { Link } from 'react-router-dom';
import './AdminPanelPage.css';

const AdminPanelPage = () => {
  const [innSearch, setInnSearch] = useState('');
  const [fioSearch, setFioSearch] = useState('');
  const [foundUser, setFoundUser] = useState(null);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState('');

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

  return (
    <div className="admin-panel-container">
      <h1>Панель Администратора</h1>
      <p>Используйте поиск для управления пользователями системы.</p>

      <div className="search-section">
        <h2>Поиск по ИНН</h2>
        <form onSubmit={handleInnSearch}>
          <input
            type="text"
            placeholder="Введите ИНН пользователя"
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
            placeholder="Введите ФИО пользователя"
            value={fioSearch}
            onChange={(e) => setFioSearch(e.target.value)}
          />
          <button type="submit">Найти по ФИО</button>
        </form>
      </div>

      {loading && <div className="loading">Поиск...</div>}
      {error && <div className="error">{error}</div>}

      {foundUser && (
        <div className="found-user-card">
          <h3>Найденный пользователь:</h3>
          <p>{foundUser.fio} ({foundUser.inn})</p>
          <Link to={`/users/${foundUser.id}`} className="view-profile-button">Профиль пользователя</Link>
        </div>
      )}
    </div>
  );
};

export default AdminPanelPage;
