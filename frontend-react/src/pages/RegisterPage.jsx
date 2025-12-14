import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import authService from '../services/authService';
import './RegisterPage.css'; // Используем тот же стиль, что и для LoginPage

const RegisterPage = () => {
  const [inn, setInn] = useState('');
  const [fio, setFio] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');
  const [success, setSuccess] = useState('');
  const navigate = useNavigate();

  const handleRegister = async (e) => {
    e.preventDefault();
    setError('');
    setSuccess('');

    if (!inn || !fio || !password) {
      setError('Все поля обязательны для заполнения.');
      return;
    }

    try {
      await authService.register(inn, fio, password);
      setSuccess('Вы успешно зарегистрированы! Сейчас вы будете перенаправлены на страницу входа.');
      
      // Перенаправляем на страницу входа через 3 секунды
      setTimeout(() => {
        navigate('/login');
      }, 3000);

    } catch (err) {
      const errorMessage = err.response?.data?.message || 'Произошла ошибка при регистрации.';
      setError(errorMessage);
      console.error('Registration error:', err);
    }
  };

  return (
    <div className="register-container">
      <div className="register-form">
        <h2>Регистрация</h2>
        <form onSubmit={handleRegister}>
          <div className="form-group">
            <label htmlFor="inn">ИНН</label>
            <input
              type="text"
              id="inn"
              value={inn}
              onChange={(e) => setInn(e.target.value)}
              placeholder="Введите ваш ИНН (14 символов)"
              required
              maxLength="14"
            />
          </div>
          <div className="form-group">
            <label htmlFor="fio">ФИО</label>
            <input
              type="text"
              id="fio"
              value={fio}
              onChange={(e) => setFio(e.target.value)}
              placeholder="Введите ваше полное имя"
              required
            />
          </div>
          <div className="form-group">
            <label htmlFor="password">Пароль</label>
            <input
              type="password"
              id="password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              placeholder="Создайте пароль"
              required
            />
          </div>
          {error && <p className="error-message">{error}</p>}
          {success && <p className="success-message">{success}</p>}
          <button type="submit" className="register-button">Зарегистрироваться</button>
        </form>
        <p className="login-link">
          Уже есть аккаунт? <a href="/login">Войти</a>
        </p>
      </div>
    </div>
  );
};

export default RegisterPage;
