import React from 'react';
import { Outlet, useNavigate, NavLink } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';
import './MainLayout.css';

const HEADER_NAV = [
  { to: '/', label: 'Профиль', end: true },
  { to: '/consents', label: 'Мои согласия' },
];

const MainLayout = () => {
  const navigate = useNavigate();
  const { logout, hasRole } = useAuth();

  const handleLogout = () => { logout(); navigate('/login'); };

  return (
    <div className="layout">
      <header className="header">
        <div className="header-left">
          <div className="logo">Kyrgyz Central Medical Card</div>
        </div>

        <nav className="header-nav">
          {HEADER_NAV.map(({ to, label, end }) => (
            <NavLink key={label} to={to} end={end}
              className={({ isActive }) => `header-nav-link ${isActive ? 'active' : ''}`}>
              {label}
            </NavLink>
          ))}
          {hasRole(['ROLE_ADMIN']) && (
            <NavLink to="/admin-panel"
              className={({ isActive }) => `header-nav-link ${isActive ? 'active' : ''}`}>
              Администратор
            </NavLink>
          )}
          {hasRole(['ROLE_DOCTOR']) && (
            <NavLink to="/doctor-dashboard"
              className={({ isActive }) => `header-nav-link ${isActive ? 'active' : ''}`}>
              Врач
            </NavLink>
          )}
          {hasRole(['ROLE_CLINIC_ADMIN']) && (
            <NavLink to="/dashboard/clinic"
              className={({ isActive }) => `header-nav-link ${isActive ? 'active' : ''}`}>
              Аналитика клиники
            </NavLink>
          )}
          {hasRole(['ROLE_COMPANY_HR']) && (
            <NavLink to="/dashboard/employer"
              className={({ isActive }) => `header-nav-link ${isActive ? 'active' : ''}`}>
              Аналитика компании
            </NavLink>
          )}
        </nav>

        <div className="header-right">
          <button className="btn btn-danger" onClick={handleLogout}>Выйти</button>
        </div>
      </header>

      <div className="layout-body">
        <main className="main">
          <Outlet />
        </main>
      </div>
    </div>
  );
};

export default MainLayout;
