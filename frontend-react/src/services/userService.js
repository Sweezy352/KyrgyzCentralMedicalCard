import api from './api';

const getCurrentUser = () => api.get('/auth/get-current');
const getById = (id) => api.get(`/users/get-by-id/${id}`);
const getByInn = (inn) => api.get('/users/get-by-inn', { params: { inn } });
const getByFio = (fio) => api.get('/users/get-by-fio', { params: { fio } });
const assignRole = (id, roleName) => api.put(`/users/${id}/assign-role`, null, { params: { roleName } });

const userService = { getCurrentUser, getById, getByInn, getByFio, assignRole };
export default userService;
