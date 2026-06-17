import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import userService from '../services/userService';
import './AdminPanelPage.css';

const DoctorDashboardPage = () => {
  const [innSearch, setInnSearch] = useState('');
  const [fioSearch, setFioSearch] = useState('');
  const [qrInput, setQrInput] = useState('');
  const [foundUser, setFoundUser] = useState(null);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState('');
  const navigate = useNavigate();

  const search = async (fn) => {
    setLoading(true);
    setError('');
    setFoundUser(null);
    try {
      const res = await fn();
      setFoundUser(res.data);
    } catch {
      setError('Пациент не найден.');
    } finally {
      setLoading(false);
    }
  };

  const handleQr = (e) => {
    e.preventDefault();
    const match = qrInput.match(/\/api\/users\/get-by-id\/(\d+)/);
    if (match) navigate(`/users/${match[1]}`);
    else setError('Некорректный формат QR-кода.');
  };

  return (
    <div>
      <h1 className="page-title">Панель Врача</h1>

      <div className="search-grid">
        <div className="card">
          <h3 className="search-title">Поиск по ИНН</h3>
          <form onSubmit={(e) => { e.preventDefault(); search(() => userService.getByInn(innSearch)); }}>
            <div className="form-group">
              <input type="text" placeholder="ИНН пациента" value={innSearch}
                onChange={(e) => setInnSearch(e.target.value)} />
            </div>
            <button type="submit" className="btn btn-primary">Найти</button>
          </form>
        </div>

        <div className="card">
          <h3 className="search-title">Поиск по ФИО</h3>
          <form onSubmit={(e) => { e.preventDefault(); search(() => userService.getByFio(fioSearch)); }}>
            <div className="form-group">
              <input type="text" placeholder="ФИО пациента" value={fioSearch}
                onChange={(e) => setFioSearch(e.target.value)} />
            </div>
            <button type="submit" className="btn btn-primary">Найти</button>
          </form>
        </div>

        <div className="card">
          <h3 className="search-title">Поиск по QR-коду</h3>
          <form onSubmit={handleQr}>
            <div className="form-group">
              <input type="text" placeholder="Вставьте URL из QR-кода" value={qrInput}
                onChange={(e) => setQrInput(e.target.value)} />
            </div>
            <button type="submit" className="btn btn-outline">Перейти</button>
          </form>
        </div>
      </div>

      {loading && <div className="loading-state"><div className="loading-spinner" />Поиск...</div>}
      {error && <div className="alert alert-error">{error}</div>}

      {foundUser && (
        <div className="card found-card">
          <div className="found-info">
            <div className="found-avatar">{foundUser.fio?.charAt(0)}</div>
            <div>
              <p className="found-name">{foundUser.fio}</p>
              <p className="found-inn">ИНН: {foundUser.inn}</p>
            </div>
          </div>
          <Link to={`/users/${foundUser.id}`} className="btn btn-primary">Открыть профиль</Link>
        </div>
      )}
    </div>
  );
};

export default DoctorDashboardPage;
