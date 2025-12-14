import React, { useState } from 'react';
import { Outlet, useNavigate, NavLink } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';
import './MainLayout.css';

const MainLayout = () => {
  const navigate = useNavigate();
  const [isSidebarOpen, setSidebarOpen] = useState(false);
  const { logout, hasRole } = useAuth();

  const handleLogout = () => {
    logout();
    navigate('/login');
  };

  const toggleSidebar = () => {
    setSidebarOpen(!isSidebarOpen);
  };

  return (
    <div className="main-layout">
      <header className="main-header">
        <div className="header-left">
          <button className="sidebar-toggle" onClick={toggleSidebar}>
            ☰
          </button>
          <div className="logo">МедКарта</div>
        </div>
        <button onClick={handleLogout} className="logout-button">
          Выход
        </button>
      </header>
      <div className="content-wrapper">
        <aside className={`sidebar ${isSidebarOpen ? 'open' : ''}`}>
          <nav className="sidebar-nav">
            <NavLink to="/" end className={({ isActive }) => isActive ? 'nav-link active' : 'nav-link'}>
              Профиль
            </NavLink>
            <NavLink to="/history" className={({ isActive }) => isActive ? 'nav-link active' : 'nav-link'}>
              История болезней
            </NavLink>
            <NavLink to="/receipts" className={({ isActive }) => isActive ? 'nav-link active' : 'nav-link'}>
              Рецепты
            </NavLink>
            <NavLink to="/allergies" className={({ isActive }) => isActive ? 'nav-link active' : 'nav-link'}>
              Аллергии
            </NavLink>
            <NavLink to="/diagnoses" className={({ isActive }) => isActive ? 'nav-link active' : 'nav-link'}> {/* Добавлена ссылка на Диагнозы */}
              Мои Диагнозы
            </NavLink>
            <NavLink to="/insurance" className={({ isActive }) => isActive ? 'nav-link active' : 'nav-link'}>
              Страховка
            </NavLink>
            <NavLink to="/news" className={({ isActive }) => isActive ? 'nav-link active' : 'nav-link'}>
              Новости
            </NavLink>
            {/* Пример условного рендеринга для ADMIN */}
            {hasRole(['ROLE_ADMIN']) && (
              <NavLink to="/admin-panel" className={({ isActive }) => isActive ? 'nav-link active' : 'nav-link'}>
                Админ-панель
              </NavLink>
            )}
            {hasRole(['ROLE_DOCTOR']) && (
              <NavLink to="/doctor-dashboard" className={({ isActive }) => isActive ? 'nav-link active' : 'nav-link'}>
                Панель врача
              </NavLink>
            )}
          </nav>
        </aside>
        <main className="main-content">
          <Outlet /> {/* Здесь будут отображаться дочерние страницы */}
        </main>
      </div>
    </div>
  );
};

export default MainLayout;
