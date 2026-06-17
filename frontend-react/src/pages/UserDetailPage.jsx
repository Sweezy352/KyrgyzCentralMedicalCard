import React, { useEffect, useState } from 'react';
import { useParams, Link } from 'react-router-dom';
import userService from '../services/userService';
import { useAuth } from '../context/AuthContext';
import './UserDetailPage.css';

const UserDetailPage = () => {
  const { id } = useParams();
  const [user, setUser] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');
  const { hasRole } = useAuth();

  useEffect(() => {
    userService.getById(id)
      .then(res => setUser(res.data))
      .catch(() => setError('Не удалось загрузить данные пользователя.'))
      .finally(() => setLoading(false));
  }, [id]);

  if (loading) return <div className="loading-state"><div className="loading-spinner" />Загрузка...</div>;
  if (error) return <div className="alert alert-error">{error}</div>;
  if (!user) return <div className="empty-state">Пользователь не найден.</div>;

  return (
    <div>
      <h1 className="page-title">Профиль пациента</h1>

      <div className="card user-detail-card">
        <div className="user-detail-avatar">{user.fio?.charAt(0)}</div>
        <div className="user-detail-info">
          <h2>{user.fio}</h2>
          <p>ИНН: <strong>{user.inn}</strong></p>
        </div>
      </div>

      {hasRole(['ROLE_DOCTOR']) && (
        <div className="card actions-card">
          <h3>Действия врача</h3>
          <div className="actions-grid">
            <Link to={`/users/${user.id}/create-history`} className="btn btn-primary">
              Добавить историю
            </Link>
            <Link to={`/users/${user.id}/create-diagnosis`} className="btn btn-primary">
              Добавить диагноз
            </Link>
            <Link to={`/users/${user.id}/create-allergie`} className="btn btn-primary">
              Добавить аллергию
            </Link>
          </div>
        </div>
      )}
    </div>
  );
};

export default UserDetailPage;
