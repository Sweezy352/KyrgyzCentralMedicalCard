import React, { useState } from 'react';
import { useNavigate, Link } from 'react-router-dom';
import authService from '../services/authService';
import './LoginPage.css';

const BLOOD_GROUPS = ['A(I)', 'B(II)', 'AB(III)', 'O(IV)'];
const RH_FACTORS = ['+', '-'];
const GENDERS = ['Мужской', 'Женский'];

const RegisterPage = () => {
  const [form, setForm] = useState({
    inn: '', fio: '', password: '',
    gender: '', birthDate: '', emergencyPhone: '',
    bloodGroup: '', rhFactor: ''
  });
  const [error, setError] = useState('');
  const [success, setSuccess] = useState('');
  const [loading, setLoading] = useState(false);
  const navigate = useNavigate();

  const set = (field) => (e) => setForm({ ...form, [field]: e.target.value });

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError('');
    setLoading(true);
    try {
      await authService.register(
        form.inn, form.fio, form.password,
        form.gender, form.birthDate || null,
        form.emergencyPhone, form.bloodGroup, form.rhFactor
      );
      setSuccess('Регистрация успешна! Перенаправление...');
      setTimeout(() => navigate('/login'), 2000);
    } catch (err) {
      setError(err.response?.data?.message || 'Ошибка при регистрации.');
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="auth-page">
      <div className="auth-card" style={{ maxWidth: 520 }}>
        <div className="auth-logo">🏥</div>
        <h1 className="auth-title">Регистрация</h1>
        <p className="auth-subtitle">Создайте аккаунт в МедКарте</p>

        <form onSubmit={handleSubmit}>
          <div className="form-group">
            <label>ИНН</label>
            <input type="text" placeholder="14 символов" value={form.inn}
              onChange={set('inn')} maxLength={14} required />
          </div>

          <div className="form-group">
            <label>ФИО</label>
            <input type="text" placeholder="Полное имя" value={form.fio}
              onChange={set('fio')} required />
          </div>

          <div className="form-group">
            <label>Пароль</label>
            <input type="password" placeholder="Создайте пароль" value={form.password}
              onChange={set('password')} required />
          </div>

          <div className="reg-row">
            <div className="form-group">
              <label>Пол</label>
              <select value={form.gender} onChange={set('gender')}>
                <option value="">— Выберите —</option>
                {GENDERS.map(g => <option key={g} value={g}>{g}</option>)}
              </select>
            </div>

            <div className="form-group">
              <label>Дата рождения</label>
              <input type="date" value={form.birthDate} onChange={set('birthDate')} />
            </div>
          </div>

          <div className="form-group">
            <label>Чрезвычайный номер</label>
            <input type="tel" placeholder="+996 XXX XXX XXX" value={form.emergencyPhone}
              onChange={set('emergencyPhone')} />
          </div>

          <div className="reg-row">
            <div className="form-group">
              <label>Группа крови</label>
              <select value={form.bloodGroup} onChange={set('bloodGroup')}>
                <option value="">— Выберите —</option>
                {BLOOD_GROUPS.map(b => <option key={b} value={b}>{b}</option>)}
              </select>
            </div>

            <div className="form-group">
              <label>Резус-фактор</label>
              <select value={form.rhFactor} onChange={set('rhFactor')}>
                <option value="">— Выберите —</option>
                {RH_FACTORS.map(r => <option key={r} value={r}>Rh{r}</option>)}
              </select>
            </div>
          </div>

          {error && <div className="alert alert-error">{error}</div>}
          {success && <div className="alert alert-success">{success}</div>}

          <button type="submit" className="btn btn-primary auth-btn" disabled={loading}>
            {loading ? 'Регистрация...' : 'Зарегистрироваться'}
          </button>
        </form>

        <p className="auth-link">
          Уже есть аккаунт? <Link to="/login">Войти</Link>
        </p>
      </div>
    </div>
  );
};

export default RegisterPage;
