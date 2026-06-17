import React, { createContext, useState, useContext } from 'react';
import authService from '../services/authService';

const AuthContext = createContext(null);

export const AuthProvider = ({ children }) => {
  const [isAuthenticated, setIsAuthenticated] = useState(!!authService.getCurrentUserToken());
  const [userRoles, setUserRoles] = useState(authService.getCurrentUserRoles());

  const login = async (inn, password) => {
    const data = await authService.login(inn, password);
    setIsAuthenticated(true);
    setUserRoles(data.roles || []);
    return data;
  };

  const logout = () => {
    authService.logout();
    setIsAuthenticated(false);
    setUserRoles([]);
  };

  const hasRole = (roles) => userRoles.some(r => roles.includes(r));

  return (
    <AuthContext.Provider value={{ isAuthenticated, userRoles, login, logout, hasRole }}>
      {children}
    </AuthContext.Provider>
  );
};

export const useAuth = () => {
  const ctx = useContext(AuthContext);
  if (!ctx) throw new Error('useAuth must be used within AuthProvider');
  return ctx;
};
