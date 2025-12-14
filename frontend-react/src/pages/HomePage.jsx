import React, { useEffect, useState } from 'react';
import userService from '../services/userService';
import { useAuth } from '../context/AuthContext';
import './HomePage.css';

const HomePage = () => {
  const [user, setUser] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');
  const { hasRole } = useAuth();

  useEffect(() => {
    const fetchUser = async () => {
      try {
        const response = await userService.getCurrentUser();
        setUser(response.data);
      } catch (err) {
        setError('Не удалось загрузить данные пользователя.');
        console.error('Fetch user error:', err);
      } finally {
        setLoading(false);
      }
    };

    fetchUser();
  }, []);

  if (loading) {
    return <div className="loading">Загрузка...</div>;
  }

  if (error) {
    return <div className="error">{error}</div>;
  }

  return (
    <div className="home-container">
      <h1>Личный кабинет</h1>
      {user ? (
        <div className="profile-grid">
          <div className="user-card">
            <h2>{user.fio}</h2>
            <p><strong>ИНН:</strong> {user.inn}</p>
            {/* Здесь можно будет добавить другую информацию о пользователе */}
          </div>
          <div className="qr-code-card">
            <h3>Ваш QR-код</h3>
            <p>Покажите этот QR-код в регистратуре для быстрого доступа к вашей медкарте.</p>
            <img 
              src={`/api/qrcode/qr-code/users/${user.id}`} 
              alt={`QR-код для ${user.fio}`} 
              className="qr-code-image"
            />
          </div>
        </div>
      ) : (
        <p>Нет данных о пользователе.</p>
      )}
    </div>
  );
};

export default HomePage;
