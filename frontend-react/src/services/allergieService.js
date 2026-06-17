import api from './api';

const getAllergiesByUserId = (userId) => api.get(`/allergies/get-all-by-user/${userId}`);
const addAllergie = (userId, data) => api.post(`/allergies/add-allergie/${userId}`, data);

const allergieService = { getAllergiesByUserId, addAllergie };
export default allergieService;
