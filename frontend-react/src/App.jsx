import React from 'react';
import { Route, Routes } from 'react-router-dom';
import LoginPage from './pages/LoginPage';
import RegisterPage from './pages/RegisterPage';
import HomePage from './pages/HomePage';
import ProtectedRoute from './components/ProtectedRoute';
import MainLayout from './layout/MainLayout';
import HistoryPage from './pages/HistoryPage';
import ReceiptsPage from './pages/ReceiptsPage';
import AllergiesPage from './pages/AllergiesPage';
import InsurancePage from './pages/InsurancePage';
import NewsPage from './pages/NewsPage';
import AdminPanelPage from './pages/AdminPanelPage';
import DoctorDashboardPage from './pages/DoctorDashboardPage';
import UserDetailPage from './pages/UserDetailPage';
import CreateHistoryPage from './pages/CreateHistoryPage';
import CreateDiagnosisPage from './pages/CreateDiagnosisPage';
import DiagnosesPage from './pages/DiagnosesPage';
import CreateAllergiePage from './pages/CreateAllergiePage'; // Импортируем CreateAllergiePage
import { AuthProvider } from './context/AuthContext';

function App() {
  return (
    <AuthProvider>
      <Routes>
        {/* Публичные роуты */}
        <Route path="/login" element={<LoginPage />} />
        <Route path="/register" element={<RegisterPage />} />

        {/* Защищенные роуты */}
        <Route element={<ProtectedRoute />}>
          <Route element={<MainLayout />}>
            <Route path="/" element={<HomePage />} />
            <Route path="/history" element={<HistoryPage />} />
            <Route path="/receipts" element={<ReceiptsPage />} />
            <Route path="/allergies" element={<AllergiesPage />} />
            <Route path="/diagnoses" element={<DiagnosesPage />} />
            <Route path="/insurance" element={<InsurancePage />} />
            <Route path="/news" element={<NewsPage />} />
            <Route path="/admin-panel" element={<AdminPanelPage />} />
            <Route path="/doctor-dashboard" element={<DoctorDashboardPage />} />
            <Route path="/users/:id" element={<UserDetailPage />} />
            <Route path="/users/:id/create-history" element={<CreateHistoryPage />} />
            <Route path="/users/:id/create-diagnosis" element={<CreateDiagnosisPage />} />
            <Route path="/users/:id/create-allergie" element={<CreateAllergiePage />} /> {/* Добавляем маршрут */}
          </Route>
        </Route>

        {/* Можно добавить страницу 404 Not Found */}
        <Route path="*" element={<div>Страница не найдена</div>} />
      </Routes>
    </AuthProvider>
  );
}

export default App;
