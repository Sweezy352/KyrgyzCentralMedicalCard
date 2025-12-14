import axios from 'axios';

const API_URL = '/api/auth';

/**
 * Отправляет запрос на регистрацию нового пользователя.
 * @param {string} inn
 * @param {string} fio
 * @param {string} password
 * @returns {Promise<any>}
 */
const register = (inn, fio, password) => {
  return axios.post(`${API_URL}/register`, {
    inn,
    fio,
    password,
  });
};

/**
 * Отправляет запрос на вход в систему.
 * @param {string} inn - ИНН пользователя.
 * @param {string} password - Пароль пользователя.
 * @returns {Promise<object>} - Возвращает объект { token: string, roles: string[] } в случае успеха.
 */
const login = async (inn, password) => {
  const response = await axios.post(`${API_URL}/login`, {
    inn,
    password,
  });

  if (response.data && response.data.token) {
    localStorage.setItem('token', response.data.token);
    localStorage.setItem('userRoles', JSON.stringify(response.data.roles)); // Сохраняем роли
  }

  return response.data;
};

/**
 * Выход из системы (просто удаляем токен и роли).
 */
const logout = () => {
  localStorage.removeItem('token');
  localStorage.removeItem('userRoles');
};

/**
 * Получение текущего токена.
 * @returns {string|null}
 */
const getCurrentUserToken = () => {
  return localStorage.getItem('token');
};

/**
 * Получение ролей текущего пользователя.
 * @returns {string[]|null}
 */
const getCurrentUserRoles = () => {
  const roles = localStorage.getItem('userRoles');
  return roles ? JSON.parse(roles) : null;
};

/**
 * Проверяет, имеет ли текущий пользователь одну из указанных ролей.
 * @param {string[]} allowedRoles - Массив ролей, которые разрешены.
 * @returns {boolean}
 */
const hasRole = (allowedRoles) => {
  const userRoles = getCurrentUserRoles();
  if (!userRoles || userRoles.length === 0) {
    return false;
  }
  return userRoles.some(role => allowedRoles.includes(role));
};


const authService = {
  register,
  login,
  logout,
  getCurrentUserToken,
  getCurrentUserRoles,
  hasRole,
};

export default authService;
