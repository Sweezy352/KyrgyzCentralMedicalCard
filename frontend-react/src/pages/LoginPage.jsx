import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { useAuth } from '../context/AuthContext'; // Импортируем useAuth
import './LoginPage.css';

const LoginPage = () => {
  const [inn, setInn] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');
  const navigate = useNavigate();
  const { login } = useAuth(); // Используем login из контекста

  const handleLogin = async (e) => {
    e.preventDefault();
    setError('');

    if (!inn || !password) {
      setError('Пожалуйста, введите ИНН и пароль.');
      return;
    }

    try {
      await login(inn, password); // Вызываем login из контекста
      navigate('/'); // Перенаправляем на главную страницу после успешного входа
    } catch (err) {
      setError('Неверный ИНН или пароль. Попробуйте снова.');
      console.error('Login error:', err);
    }
  };

  return (
    <div className="login-container">
      <div className="login-form">
        <h2>Вход в систему</h2>
        <form onSubmit={handleLogin}>
          <div className="form-group">
            <label htmlFor="inn">ИНН</label>
            <input
              type="text"
              id="inn"
              value={inn}
              onChange={(e) => setInn(e.target.value)}
              placeholder="Введите ваш ИНН"
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
              placeholder="Введите ваш пароль"
              required
            />
          </div>
          {error && <p className="error-message">{error}</p>}
          <button type="submit" className="login-button">Войти</button>
        </form>
        <p className="register-link">
          Нет аккаунта? <a href="/register">Зарегистрироваться</a>
        </p>
      </div>
    </div>
  );
};

export default LoginPage;
