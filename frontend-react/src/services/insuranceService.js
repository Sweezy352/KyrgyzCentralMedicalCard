import api from './api';

const getUserInsurancesByUserId = (userId) => api.get(`/user-insurance/get-all-by-user/${userId}`);

const insuranceService = { getUserInsurancesByUserId };
export default insuranceService;
