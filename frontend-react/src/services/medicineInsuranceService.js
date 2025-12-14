import axios from 'axios';
import authService from './authService';

const API_URL = '/api/medicine-insurance';

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
 * Получает детали медицинской страховки по её ID.
 * @param {number} id - ID медицинской страховки.
 * @returns {Promise<any>}
 */
const getMedicineInsuranceById = (id) => {
  return axiosInstance.get(`${API_URL}/get-by-id/${id}`);
};

const medicineInsuranceService = {
  getMedicineInsuranceById,
};

export default medicineInsuranceService;
