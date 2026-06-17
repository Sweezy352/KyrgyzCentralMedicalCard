import api from './api';

const getMedicineInsuranceById = (id) => api.get(`/medicine-insurance/get-by-id/${id}`);

const medicineInsuranceService = { getMedicineInsuranceById };
export default medicineInsuranceService;
