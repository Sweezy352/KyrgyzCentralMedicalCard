import api from './api';

const getByPatientId = (patientId) => api.get(`/patients/${patientId}/consents`);
const grant = (patientId, data) => api.post(`/patients/${patientId}/consents`, data);
const revoke = (patientId, consentId) => api.delete(`/patients/${patientId}/consents/${consentId}`);

const consentService = { getByPatientId, grant, revoke };
export default consentService;
