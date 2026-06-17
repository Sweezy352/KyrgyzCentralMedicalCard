import api from './api';

const getAllNews = () => api.get('/news/get-all-news');

const newsService = { getAllNews };
export default newsService;
