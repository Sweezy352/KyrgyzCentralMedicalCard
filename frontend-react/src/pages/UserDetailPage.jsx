import React, { useEffect, useState } from 'react';
import { useParams, Link } from 'react-router-dom';
import userService from '../services/userService';
import { useAuth } from '../context/AuthContext';
import './UserDetailPage.css';

const UserDetailPage = () => {
  const { id } = useParams(); // Получаем ID пользователя из URL
  const [user, setUser] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');
  const { hasRole } = useAuth();

  useEffect(() => {
    const fetchUser = async () => {
      try {
        const response = await userService.getById(id);
        setUser(response.data);
      } catch (err) {
        setError('Не удалось загрузить данные пользователя.');
        console.error('Fetch user error:', err);
      } finally {
        setLoading(false);
      }
    };

    fetchUser();
  }, [id]); // Перезагружаем данные при изменении ID в URL

  if (loading) {
    return <div className="loading">Загрузка профиля пользователя...</div>;
  }

  if (error) {
    return <div className="error">{error}</div>;
  }

  if (!user) {
    return <div className="error">Пользователь не найден.</div>;
  }

  return (
    <div className="user-detail-container">
      <h1>Профиль пользователя: {user.fio}</h1>
      <div className="user-detail-card">
        <p><strong>ИНН:</strong> {user.inn}</p>
        {/* Здесь можно добавить другую информацию о пользователе */}

        {hasRole(['ROLE_DOCTOR']) && (
          <div className="doctor-actions-patient-profile">
            <h3>Действия для пациента</h3>
            <Link to={`/users/${user.id}/create-history`} className="action-button">Создать историю болезни</Link>
            <Link to={`/users/${user.id}/create-diagnosis`} className="action-button">Добавить диагноз</Link>
            <Link to={`/users/${user.id}/create-allergie`} className="action-button">Добавить аллергию</Link> {/* Добавлена кнопка */}
            {/* Дополнительные действия врача для пациента */}
          </div>
        )}
      </div>
    </div>
  );
};

export default UserDetailPage;
