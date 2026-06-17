import api from './api';

const create = (data) => api.post('/visits', data);
const getById = (id) => api.get(`/visits/${id}`);
const getByPatientId = (patientId) => api.get(`/patients/${patientId}/visits`);
const getByOrganizationId = (orgId) => api.get(`/organizations/${orgId}/visits`);

const visitService = { create, getById, getByPatientId, getByOrganizationId };
export default visitService;
