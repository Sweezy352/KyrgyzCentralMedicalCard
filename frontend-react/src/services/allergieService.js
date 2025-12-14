import axios from 'axios';
import authService from './authService';

const API_URL = '/api/allergies';

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
 * Получает аллергии для указанного ID пользователя.
 * @param {number} userId - ID пользователя.
 * @returns {Promise<any>}
 */
const getAllergiesByUserId = (userId) => {
  return axiosInstance.get(`${API_URL}/get-all-by-user/${userId}`);
};

/**
 * Добавляет новую аллергию для указанного пользователя.
 * @param {number} userId - ID пользователя, для которого добавляется аллергия.
 * @param {object} allergieData - Объект с данными аллергии (name, description).
 * @returns {Promise<any>}
 */
const addAllergie = (userId, allergieData) => {
  return axiosInstance.post(`${API_URL}/add-allergie/${userId}`, allergieData);
};

const allergieService = {
  getAllergiesByUserId,
  addAllergie, // Добавляем новый метод
};

export default allergieService;
