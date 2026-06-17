import api from './api';

const create = (data) => api.post('/organizations', data);
const getById = (id) => api.get(`/organizations/${id}`);
const update = (id, data) => api.put(`/organizations/${id}`, data);
const getStaff = (id) => api.get(`/organizations/${id}/staff`);
const addStaff = (id, data) => api.post(`/organizations/${id}/staff`, data);
const removeStaff = (id, uid) => api.delete(`/organizations/${id}/staff/${uid}`);

const organizationService = { create, getById, update, getStaff, addStaff, removeStaff };
export default organizationService;
