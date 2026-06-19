import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import userService from '../services/userService';
import organizationService from '../services/organizationService';
import healthGroupService from '../services/healthGroupService';
import accessLogService from '../services/accessLogService';
import './AdminPanelPage.css';

const TABS = ['Поиск', 'Организации', 'Сотрудники', 'Группы здоровья', 'Лог событий'];
const ROLES = ['ADMIN', 'DOCTOR', 'NURSE', 'PHARMACIST', 'CLINIC_ADMIN', 'COMPANY_HR', 'USER'];

const Notice = ({ error, success }) => (
  <>
    {error && <div className="alert alert-error">{error}</div>}
    {success && <div className="alert alert-success">{success}</div>}
  </>
);

// ---------- Поиск по ИНН ----------
const SearchTab = () => {
  const [inn, setInn] = useState('');
  const [user, setUser] = useState(null);
  const [error, setError] = useState('');
  const [loading, setLoading] = useState(false);

  const search = async (e) => {
    e.preventDefault();
    setLoading(true); setError(''); setUser(null);
    try {
      const res = await userService.getByInn(inn);
      setUser(res.data);
    } catch {
      setError('Пользователь не найден.');
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="card">
      <h3 className="search-title">Поиск по ИНН</h3>
      <form onSubmit={search}>
        <div className="form-group">
          <input type="text" placeholder="Введите ИНН" value={inn} onChange={(e) => setInn(e.target.value)} />
        </div>
        <button type="submit" className="btn btn-primary">Найти</button>
      </form>
      {loading && <div className="loading-state"><div className="loading-spinner" />Поиск...</div>}
      <Notice error={error} />
      {user && (
        <div className="found-card" style={{ marginTop: '1rem' }}>
          <div className="found-info">
            <div className="found-avatar">{user.fio?.charAt(0)}</div>
            <div>
              <p className="found-name">{user.fio}</p>
              <p className="found-inn">ИНН: {user.inn}</p>
            </div>
          </div>
          <Link to={`/users/${user.id}`} className="btn btn-primary">Открыть профиль</Link>
        </div>
      )}
    </div>
  );
};

// ---------- Организации (клиники и работодатели) ----------
const OrganizationsTab = () => {
  const empty = { name: '', type: 'CLINIC', bin: '', address: '', phone: '', email: '', contractStart: '', contractEnd: '' };
  const [form, setForm] = useState(empty);
  const [error, setError] = useState('');
  const [success, setSuccess] = useState('');
  const [created, setCreated] = useState(null);
  const set = (f) => (e) => setForm({ ...form, [f]: e.target.value });

  const submit = async (e) => {
    e.preventDefault();
    setError(''); setSuccess(''); setCreated(null);
    try {
      const res = await organizationService.create({
        name: form.name,
        type: form.type,
        bin: form.bin,
        address: form.address || null,
        phone: form.phone || null,
        email: form.email || null,
        contractStart: form.contractStart || null,
        contractEnd: form.contractEnd || null,
      });
      setCreated(res.data);
      setSuccess(`Организация создана (ID ${res.data.id}).`);
      setForm(empty);
    } catch {
      setError('Не удалось создать организацию. Проверьте БИН (уникальный) и поля.');
    }
  };

  return (
    <div className="card">
      <h3 className="search-title">Добавить организацию (клинику / работодателя)</h3>
      <form onSubmit={submit}>
        <div className="form-group">
          <label>Название</label>
          <input type="text" value={form.name} onChange={set('name')} required />
        </div>
        <div className="form-group">
          <label>Тип</label>
          <select value={form.type} onChange={set('type')}>
            <option value="CLINIC">Клиника</option>
            <option value="EMPLOYER">Работодатель</option>
          </select>
        </div>
        <div className="form-group">
          <label>БИН</label>
          <input type="text" value={form.bin} onChange={set('bin')} maxLength={14} required />
        </div>
        <div className="form-group">
          <label>Адрес</label>
          <input type="text" value={form.address} onChange={set('address')} />
        </div>
        <div className="form-group">
          <label>Телефон</label>
          <input type="text" value={form.phone} onChange={set('phone')} />
        </div>
        <div className="form-group">
          <label>Email</label>
          <input type="email" value={form.email} onChange={set('email')} />
        </div>
        <div className="form-group">
          <label>Договор: начало</label>
          <input type="date" value={form.contractStart} onChange={set('contractStart')} />
        </div>
        <div className="form-group">
          <label>Договор: конец</label>
          <input type="date" value={form.contractEnd} onChange={set('contractEnd')} />
        </div>
        <Notice error={error} success={success} />
        <button type="submit" className="btn btn-primary">Создать организацию</button>
      </form>
      {created && (
        <p className="create-subtitle" style={{ marginTop: '1rem' }}>
          Создано: <strong>{created.name}</strong> (ID {created.id}, {created.type})
        </p>
      )}
    </div>
  );
};

// ---------- Привязка пользователей к организации ----------
const StaffTab = () => {
  const [form, setForm] = useState({ organizationId: '', userId: '', role: 'DOCTOR', department: '' });
  const [assignSystemRole, setAssignSystemRole] = useState(true);
  const [staff, setStaff] = useState([]);
  const [error, setError] = useState('');
  const [success, setSuccess] = useState('');
  const set = (f) => (e) => setForm({ ...form, [f]: e.target.value });

  const loadStaff = async () => {
    setError('');
    try {
      const res = await organizationService.getStaff(form.organizationId);
      setStaff(res.data);
    } catch {
      setError('Не удалось загрузить сотрудников организации.');
    }
  };

  const submit = async (e) => {
    e.preventDefault();
    setError(''); setSuccess('');
    try {
      await organizationService.addStaff(form.organizationId, {
        userId: Number(form.userId),
        role: form.role,
        department: form.department || null,
      });
      // По желанию назначаем системную роль, чтобы пользователь получил доступ к функциям
      if (assignSystemRole) {
        await userService.assignRole(form.userId, form.role);
      }
      setSuccess('Пользователь привязан к организации.');
      loadStaff();
    } catch {
      setError('Не удалось привязать пользователя. Проверьте ID организации и пользователя.');
    }
  };

  return (
    <div className="card">
      <h3 className="search-title">Привязать пользователя к организации</h3>
      <form onSubmit={submit}>
        <div className="form-group">
          <label>ID организации</label>
          <input type="number" value={form.organizationId} onChange={set('organizationId')} required />
        </div>
        <div className="form-group">
          <label>ID пользователя</label>
          <input type="number" value={form.userId} onChange={set('userId')} required />
        </div>
        <div className="form-group">
          <label>Роль в организации</label>
          <select value={form.role} onChange={set('role')}>
            {ROLES.map((r) => <option key={r} value={r}>{r}</option>)}
          </select>
        </div>
        <div className="form-group">
          <label>Отдел</label>
          <input type="text" value={form.department} onChange={set('department')} />
        </div>
        <div className="form-group form-check">
          <label>
            <input type="checkbox" checked={assignSystemRole} onChange={(e) => setAssignSystemRole(e.target.checked)} />
            {' '}Также назначить системную роль ({form.role})
          </label>
        </div>
        <Notice error={error} success={success} />
        <div className="form-actions">
          <button type="submit" className="btn btn-primary">Привязать</button>
          <button type="button" className="btn btn-outline" onClick={loadStaff} disabled={!form.organizationId}>
            Показать сотрудников
          </button>
        </div>
      </form>
      {staff.length > 0 && (
        <table className="admin-table">
          <thead><tr><th>ID</th><th>ФИО</th><th>Роль</th><th>Отдел</th></tr></thead>
          <tbody>
            {staff.map((s) => (
              <tr key={s.id}>
                <td>{s.id}</td>
                <td>{s.user?.fio}</td>
                <td>{s.role}</td>
                <td>{s.department || '—'}</td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
};

// ---------- Группы здоровья ----------
const HealthGroupsTab = () => {
  const empty = { organizationId: '', patientId: '', employeeId: '', department: '', position: '', healthGroup: '1', lastCheckupDate: '', nextCheckupDate: '' };
  const [form, setForm] = useState(empty);
  const [list, setList] = useState([]);
  const [error, setError] = useState('');
  const [success, setSuccess] = useState('');
  const set = (f) => (e) => setForm({ ...form, [f]: e.target.value });

  const load = async () => {
    setError('');
    try {
      const res = await healthGroupService.getByOrganizationId(form.organizationId);
      setList(res.data);
    } catch {
      setError('Не удалось загрузить группы здоровья.');
    }
  };

  const submit = async (e) => {
    e.preventDefault();
    setError(''); setSuccess('');
    try {
      await healthGroupService.create({
        organizationId: form.organizationId ? Number(form.organizationId) : null,
        patientId: Number(form.patientId),
        employeeId: form.employeeId || null,
        department: form.department || null,
        position: form.position || null,
        healthGroup: form.healthGroup,
        lastCheckupDate: form.lastCheckupDate || null,
        nextCheckupDate: form.nextCheckupDate || null,
      });
      setSuccess('Группа здоровья добавлена.');
      if (form.organizationId) load();
    } catch {
      setError('Не удалось добавить группу здоровья.');
    }
  };

  return (
    <div className="card">
      <h3 className="search-title">Группы здоровья сотрудников</h3>
      <form onSubmit={submit}>
        <div className="form-group">
          <label>ID организации (необязательно — иначе ваша)</label>
          <input type="number" value={form.organizationId} onChange={set('organizationId')} />
        </div>
        <div className="form-group">
          <label>ID пациента</label>
          <input type="number" value={form.patientId} onChange={set('patientId')} required />
        </div>
        <div className="form-group">
          <label>Табельный номер</label>
          <input type="text" value={form.employeeId} onChange={set('employeeId')} />
        </div>
        <div className="form-group">
          <label>Отдел</label>
          <input type="text" value={form.department} onChange={set('department')} />
        </div>
        <div className="form-group">
          <label>Должность</label>
          <input type="text" value={form.position} onChange={set('position')} />
        </div>
        <div className="form-group">
          <label>Группа здоровья</label>
          <select value={form.healthGroup} onChange={set('healthGroup')}>
            {['1', '2', '3', '4', '5'].map((g) => <option key={g} value={g}>{g}</option>)}
          </select>
        </div>
        <div className="form-group">
          <label>Последний осмотр</label>
          <input type="date" value={form.lastCheckupDate} onChange={set('lastCheckupDate')} />
        </div>
        <div className="form-group">
          <label>Следующий осмотр</label>
          <input type="date" value={form.nextCheckupDate} onChange={set('nextCheckupDate')} />
        </div>
        <Notice error={error} success={success} />
        <div className="form-actions">
          <button type="submit" className="btn btn-primary">Добавить</button>
          <button type="button" className="btn btn-outline" onClick={load} disabled={!form.organizationId}>
            Показать по организации
          </button>
        </div>
      </form>
      {list.length > 0 && (
        <table className="admin-table">
          <thead><tr><th>ID</th><th>Пациент</th><th>Отдел</th><th>Группа</th><th>След. осмотр</th></tr></thead>
          <tbody>
            {list.map((g) => (
              <tr key={g.id}>
                <td>{g.id}</td>
                <td>{g.patientFio}</td>
                <td>{g.department || '—'}</td>
                <td>{g.healthGroup}</td>
                <td>{g.nextCheckupDate || '—'}</td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
};

// ---------- Лог событий ----------
const AccessLogTab = () => {
  const [form, setForm] = useState({ organizationId: '', patientId: '', accessType: 'VIEW' });
  const [list, setList] = useState([]);
  const [error, setError] = useState('');
  const [success, setSuccess] = useState('');
  const set = (f) => (e) => setForm({ ...form, [f]: e.target.value });

  const load = async () => {
    setError('');
    try {
      const res = await accessLogService.getByOrganizationId(form.organizationId);
      setList(res.data);
    } catch {
      setError('Не удалось загрузить лог событий.');
    }
  };

  const submit = async (e) => {
    e.preventDefault();
    setError(''); setSuccess('');
    try {
      await accessLogService.create({
        organizationId: form.organizationId ? Number(form.organizationId) : null,
        patientId: Number(form.patientId),
        accessType: form.accessType,
      });
      setSuccess('Событие записано в лог.');
      if (form.organizationId) load();
    } catch {
      setError('Не удалось записать событие.');
    }
  };

  return (
    <div className="card">
      <h3 className="search-title">Лог событий доступа</h3>
      <form onSubmit={submit}>
        <div className="form-group">
          <label>ID организации (необязательно — иначе ваша)</label>
          <input type="number" value={form.organizationId} onChange={set('organizationId')} />
        </div>
        <div className="form-group">
          <label>ID пациента</label>
          <input type="number" value={form.patientId} onChange={set('patientId')} required />
        </div>
        <div className="form-group">
          <label>Тип события</label>
          <select value={form.accessType} onChange={set('accessType')}>
            {['VIEW', 'CREATE', 'UPDATE', 'EXPORT', 'PRINT'].map((t) => <option key={t} value={t}>{t}</option>)}
          </select>
        </div>
        <Notice error={error} success={success} />
        <div className="form-actions">
          <button type="submit" className="btn btn-primary">Записать событие</button>
          <button type="button" className="btn btn-outline" onClick={load} disabled={!form.organizationId}>
            Показать по организации
          </button>
        </div>
      </form>
      {list.length > 0 && (
        <table className="admin-table">
          <thead><tr><th>Время</th><th>Кто</th><th>Пациент</th><th>Событие</th><th>IP</th></tr></thead>
          <tbody>
            {list.map((l) => (
              <tr key={l.id}>
                <td>{l.accessedAt ? new Date(l.accessedAt).toLocaleString('ru-RU') : '—'}</td>
                <td>{l.accessedByFio || '—'}</td>
                <td>{l.patientFio || '—'}</td>
                <td>{l.accessType}</td>
                <td>{l.ipAddress || '—'}</td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
};

const AdminPanelPage = () => {
  const [tab, setTab] = useState('Поиск');

  return (
    <div>
      <h1 className="page-title">Панель Администратора</h1>

      <div className="hp-tabs admin-tabs">
        {TABS.map((t) => (
          <button key={t} className={`hp-tab ${tab === t ? 'active' : ''}`} onClick={() => setTab(t)}>
            {t}
          </button>
        ))}
      </div>

      {tab === 'Поиск' && <SearchTab />}
      {tab === 'Организации' && <OrganizationsTab />}
      {tab === 'Сотрудники' && <StaffTab />}
      {tab === 'Группы здоровья' && <HealthGroupsTab />}
      {tab === 'Лог событий' && <AccessLogTab />}
    </div>
  );
};

export default AdminPanelPage;
