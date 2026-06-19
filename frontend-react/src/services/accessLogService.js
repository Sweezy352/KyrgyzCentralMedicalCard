import api from './api';

// Лог событий доступа (ACCESS_LOGS)
const create = (data) => api.post('/access-logs', data);
const getByOrganizationId = (orgId) => api.get(`/organizations/${orgId}/access-logs`);
const getByPatientId = (patientId) => api.get(`/patients/${patientId}/access-logs`);

const accessLogService = { create, getByOrganizationId, getByPatientId };
export default accessLogService;
