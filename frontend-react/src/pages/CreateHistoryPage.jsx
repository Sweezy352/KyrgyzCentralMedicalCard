import React, { useState, useEffect } from 'react';
import { useParams, useNavigate, Link } from 'react-router-dom';
import historyService from '../services/historyService';
import userService from '../services/userService';
import './CreatePage.css';

const CreateHistoryPage = () => {
  const { id } = useParams();
  const navigate = useNavigate();
  const [patient, setPatient] = useState(null);
  const [form, setForm] = useState({ name: '', description: '' });
  const [error, setError] = useState('');
  const [success, setSuccess] = useState('');
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    userService.getById(id).then(res => setPatient(res.data));
  }, [id]);

  const set = (field) => (e) => setForm({ ...form, [field]: e.target.value });

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError('');
    setLoading(true);
    try {
      await historyService.createHistory(id, form);
      setSuccess('История болезни успешно добавлена!');
      setTimeout(() => navigate(`/users/${id}`), 1500);
    } catch {
      setError('Ошибка при создании записи.');
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="create-page">
      <h1 className="page-title">Создать историю болезни</h1>
      {patient && (
        <p className="create-subtitle">
          Пациент: <strong>{patient.fio}</strong> (ИНН: {patient.inn})
        </p>
      )}
      <div className="card">
        <form onSubmit={handleSubmit}>
          <div className="form-group">
            <label>Название</label>
            <input type="text" placeholder="Например: ОРВИ, Перелом руки"
              value={form.name} onChange={set('name')} required />
          </div>
          <div className="form-group">
            <label>Описание</label>
            <textarea placeholder="Подробное описание, лечение и рекомендации"
              value={form.description} onChange={set('description')} required />
          </div>
          {error && <div className="alert alert-error">{error}</div>}
          {success && <div className="alert alert-success">{success}</div>}
          <div className="form-actions">
            <button type="submit" className="btn btn-primary" disabled={loading}>
              {loading ? 'Сохранение...' : 'Создать запись'}
            </button>
            <Link to={`/users/${id}`} className="btn btn-outline">Отмена</Link>
          </div>
        </form>
      </div>
    </div>
  );
};

export default CreateHistoryPage;
