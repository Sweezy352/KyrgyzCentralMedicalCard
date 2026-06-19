import api from './api';

// EMPLOYEE_HEALTH_GROUPS
const create = (data) => api.post('/health-groups', data);
const getByOrganizationId = (orgId) => api.get(`/organizations/${orgId}/health-groups`);
const getByPatientId = (patientId) => api.get(`/patients/${patientId}/health-groups`);

const healthGroupService = { create, getByOrganizationId, getByPatientId };
export default healthGroupService;
