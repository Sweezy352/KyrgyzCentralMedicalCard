import axios from 'axios';
import authService from './authService';

const API_URL = '/api/diagnosis';

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
 * Создает новый диагноз для указанного пользователя.
 * @param {number} userId - ID пользователя, для которого создается диагноз.
 * @param {object} diagnosisData - Объект с данными диагноза (name, description).
 * @returns {Promise<any>}
 */
const createDiagnosis = (userId, diagnosisData) => {
  return axiosInstance.post(`${API_URL}/create-diagnosis/${userId}`, diagnosisData);
};

/**
 * Получает все диагнозы для указанного ID пользователя.
 * @param {number} userId - ID пользователя.
 * @returns {Promise<any>}
 */
const getAllDiagnosesByUserId = (userId) => {
  return axiosInstance.get(`${API_URL}/get-all-by-user/${userId}`);
};

const diagnosisService = {
  createDiagnosis,
  getAllDiagnosesByUserId, // Добавляем новый метод
};

export default diagnosisService;
