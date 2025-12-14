import axios from 'axios';
import authService from './authService';

const API_URL = '/api/news';

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
 * Получает все новости.
 * @returns {Promise<any>}
 */
const getAllNews = () => {
  return axiosInstance.get(`${API_URL}/get-all-news`);
};

const newsService = {
  getAllNews,
};

export default newsService;
