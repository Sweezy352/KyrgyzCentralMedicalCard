import React, { useState, useEffect } from 'react';
import { useParams, useNavigate, Link } from 'react-router-dom';
import visitService from '../services/visitService';
import userService from '../services/userService';
import './CreatePage.css';

const VISIT_TYPES = ['CONSULTATION', 'PROCEDURE', 'EMERGENCY', 'CHECKUP'];
const VISIT_LABELS = {
  CONSULTATION: 'Консультация',
  PROCEDURE: 'Процедура',
  EMERGENCY: 'Экстренный',
  CHECKUP: 'Плановый осмотр',
};

const CreateVisitPage = () => {
  const { id } = useParams();
  const navigate = useNavigate();
  const [patient, setPatient] = useState(null);
  const [form, setForm] = useState({
    clinicId: '',
    doctorId: '',
    visitDate: '',
    visitType: 'CONSULTATION',
    chiefComplaint: '',
    diagnosisPrimary: '',
    diagnosisSecondary: '',
    treatmentPlan: '',
    notes: '',
    durationMinutes: '',
    cost: '',
  });
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
      await visitService.create({
        patientId: Number(id),
        clinicId: Number(form.clinicId),
        doctorId: Number(form.doctorId),
        visitDate: form.visitDate,
        visitType: form.visitType,
        chiefComplaint: form.chiefComplaint,
        diagnosisPrimary: form.diagnosisPrimary,
        diagnosisSecondary: form.diagnosisSecondary,
        treatmentPlan: form.treatmentPlan,
        notes: form.notes,
        durationMinutes: form.durationMinutes ? Number(form.durationMinutes) : null,
        cost: form.cost ? Number(form.cost) : null,
      });
      setSuccess('Визит успешно создан!');
      setTimeout(() => navigate(`/users/${id}`), 1500);
    } catch {
      setError('Ошибка при создании визита. Проверьте согласие пациента.');
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="create-page">
      <h1 className="page-title">Создать визит</h1>
      {patient && (
        <p className="create-subtitle">
          Пациент: <strong>{patient.fio}</strong> (ИНН: {patient.inn})
        </p>
      )}
      <div className="card">
        <form onSubmit={handleSubmit}>
          <div className="form-group">
            <label>ID клиники</label>
            <input type="number" placeholder="ID клиники"
              value={form.clinicId} onChange={set('clinicId')} required />
          </div>
          <div className="form-group">
            <label>ID врача (organization_user)</label>
            <input type="number" placeholder="ID врача в организации"
              value={form.doctorId} onChange={set('doctorId')} required />
          </div>
          <div className="form-group">
            <label>Дата визита</label>
            <input type="datetime-local"
              value={form.visitDate} onChange={set('visitDate')} required />
          </div>
          <div className="form-group">
            <label>Тип визита</label>
            <select value={form.visitType} onChange={set('visitType')}>
              {VISIT_TYPES.map(t => (
                <option key={t} value={t}>{VISIT_LABELS[t]}</option>
              ))}
            </select>
          </div>
          <div className="form-group">
            <label>Жалоба пациента</label>
            <textarea placeholder="Опишите жалобы пациента"
              value={form.chiefComplaint} onChange={set('chiefComplaint')} />
          </div>
          <div className="form-group">
            <label>Основной диагноз (МКБ-10)</label>
            <input type="text" placeholder="Например: J06.9"
              value={form.diagnosisPrimary} onChange={set('diagnosisPrimary')} />
          </div>
          <div className="form-group">
            <label>Сопутствующий диагноз</label>
            <input type="text" placeholder="Дополнительные диагнозы"
              value={form.diagnosisSecondary} onChange={set('diagnosisSecondary')} />
          </div>
          <div className="form-group">
            <label>План лечения</label>
            <textarea placeholder="Назначения и рекомендации"
              value={form.treatmentPlan} onChange={set('treatmentPlan')} />
          </div>
          <div className="form-group">
            <label>Заметки</label>
            <textarea placeholder="Дополнительные заметки"
              value={form.notes} onChange={set('notes')} />
          </div>
          <div className="form-group">
            <label>Длительность (мин)</label>
            <input type="number" placeholder="30"
              value={form.durationMinutes} onChange={set('durationMinutes')} />
          </div>
          <div className="form-group">
            <label>Стоимость (сом)</label>
            <input type="number" placeholder="0.00"
              value={form.cost} onChange={set('cost')} />
          </div>
          {error && <div className="alert alert-error">{error}</div>}
          {success && <div className="alert alert-success">{success}</div>}
          <div className="form-actions">
            <button type="submit" className="btn btn-primary" disabled={loading}>
              {loading ? 'Сохранение...' : 'Создать визит'}
            </button>
            <Link to={`/users/${id}`} className="btn btn-outline">Отмена</Link>
          </div>
        </form>
      </div>
    </div>
  );
};

export default CreateVisitPage;
