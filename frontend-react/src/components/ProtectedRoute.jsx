import React from 'react';
import { Navigate, Outlet } from 'react-router-dom';
import authService from '../services/authService';

const ProtectedRoute = () => {
  const token = authService.getCurrentUserToken();

  if (!token) {
    // Если токена нет, перенаправляем на страницу входа
    return <Navigate to="/login" />;
  }

  // Если токен есть, показываем вложенный компонент (страницу)
  return <Outlet />;
};

export default ProtectedRoute;
