import api from './api';

const createDiagnosis = (userId, data) => api.post(`/diagnosis/create-diagnosis/${userId}`, data);
const getAllDiagnosesByUserId = (userId) => api.get(`/diagnosis/get-all-by-user/${userId}`);

const diagnosisService = { createDiagnosis, getAllDiagnosesByUserId };
export default diagnosisService;
