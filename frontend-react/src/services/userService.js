import axios from 'axios';
import authService from './authService';

const API_URL_AUTH = '/api/auth';
const API_URL_USERS = '/api/users';

// Настраиваем axios для автоматической отправки токена
const axiosInstance = axios.create();

axiosInstance.interceptors.request.use(
  (config) => {
    const token = authService.getCurrentUserToken();
    if (token) {
      config.headers['Authorization'] = 'Bearer ' + token;
    }
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

/**
 * Получает данные текущего авторизованного пользователя.
 * @returns {Promise<any>}
 */
const getCurrentUser = () => {
  return axiosInstance.get(`${API_URL_AUTH}/get-current`);
};

/**
 * Получает список всех пользователей.
 * @returns {Promise<any>}
 */
const getAllUsers = () => {
  return axiosInstance.get(`${API_URL_USERS}/get-all-users`);
};

/**
 * Получает пользователя по его ID.
 * @param {string} id - ID пользователя.
 * @returns {Promise<any>}
 */
const getById = (id) => {
  return axiosInstance.get(`${API_URL_USERS}/get-by-id/${id}`);
};

/**
 * Получает пользователя по его ИНН.
 * @param {string} inn - ИНН пользователя.
 * @returns {Promise<any>}
 */
const getByInn = (inn) => {
  return axiosInstance.get(`${API_URL_USERS}/get-by-inn`, { params: { inn } });
};

/**
 * Получает пользователя по его ФИО.
 * @param {string} fio - ФИО пользователя.
 * @returns {Promise<any>}
 */
const getByFio = (fio) => {
  return axiosInstance.get(`${API_URL_USERS}/get-by-fio`, { params: { fio } });
};

const userService = {
  getCurrentUser,
  getAllUsers,
  getById,
  getByInn, // Добавляем новый метод
  getByFio, // Добавляем новый метод
};

export default userService;
