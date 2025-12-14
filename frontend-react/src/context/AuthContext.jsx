import React, { createContext, useState, useEffect, useContext } from 'react';
import authService from '../services/authService';

const AuthContext = createContext(null);

export const AuthProvider = ({ children }) => {
  const [isAuthenticated, setIsAuthenticated] = useState(!!authService.getCurrentUserToken());
  const [userRoles, setUserRoles] = useState(authService.getCurrentUserRoles() || []);

  // Обновляем состояние при изменении localStorage (например, при входе/выходе)
  useEffect(() => {
    const handleStorageChange = () => {
      setIsAuthenticated(!!authService.getCurrentUserToken());
      setUserRoles(authService.getCurrentUserRoles() || []);
    };

    window.addEventListener('storage', handleStorageChange);
    // Также можно добавить слушатель для кастомных событий, если вы их используете
    // window.addEventListener('loginEvent', handleStorageChange);
    // window.addEventListener('logoutEvent', handleStorageChange);

    return () => {
      window.removeEventListener('storage', handleStorageChange);
      // window.removeEventListener('loginEvent', handleStorageChange);
      // window.removeEventListener('logoutEvent', handleStorageChange);
    };
  }, []);

  const login = async (inn, password) => {
    try {
      const response = await authService.login(inn, password);
      setIsAuthenticated(true);
      setUserRoles(response.roles);
      return response;
    } catch (error) {
      setIsAuthenticated(false);
      setUserRoles([]);
      throw error;
    }
  };

  const logout = () => {
    authService.logout();
    setIsAuthenticated(false);
    setUserRoles([]);
  };

  const hasRole = (roles) => {
    if (!userRoles || userRoles.length === 0) return false;
    return userRoles.some(role => roles.includes(role));
  };

  return (
    <AuthContext.Provider value={{ isAuthenticated, userRoles, login, logout, hasRole }}>
      {children}
    </AuthContext.Provider>
  );
};

export const useAuth = () => {
  const context = useContext(AuthContext);
  if (!context) {
    throw new Error('useAuth must be used within an AuthProvider');
  }
  return context;
};
