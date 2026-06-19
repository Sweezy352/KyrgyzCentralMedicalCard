import api from './api';

const getHistoryByUserId = (userId) => api.get(`/history/get-all-by-user/${userId}`);
const createHistory = (userId, data) => api.post(`/history/create-history/${userId}`, data);

const historyService = { getHistoryByUserId, createHistory };
export default historyService;
