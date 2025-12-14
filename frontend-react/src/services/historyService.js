import axios from 'axios';
import authService from './authService';

const API_URL = '/api/history';

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
 * Получает историю болезней для указанного ID пользователя.
 * @param {number} userId - ID пользователя.
 * @returns {Promise<any>}
 */
const getHistoryByUserId = (userId) => {
  return axiosInstance.get(`${API_URL}/get-all-by-user/${userId}`);
};

/**
 * Создает новую запись в истории болезни для указанного пользователя.
 * @param {number} userId - ID пользователя, для которого создается запись.
 * @param {object} historyData - Объект с данными истории (name, description).
 * @returns {Promise<any>}
 */
const createHistory = (userId, historyData) => {
  return axiosInstance.post(`${API_URL}/create-history/${userId}`, historyData);
};

const historyService = {
  getHistoryByUserId,
  createHistory, // Добавляем новый метод
};

export default historyService;
