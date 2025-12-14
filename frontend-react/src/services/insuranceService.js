import axios from 'axios';
import authService from './authService';

const API_URL = '/api/user-insurance';

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
 * Получает страховки для указанного ID пользователя.
 * @param {number} userId - ID пользователя.
 * @returns {Promise<any>}
 */
const getUserInsurancesByUserId = (userId) => {
  return axiosInstance.get(`${API_URL}/get-all-by-user/${userId}`);
};

const insuranceService = {
  getUserInsurancesByUserId,
};

export default insuranceService;
