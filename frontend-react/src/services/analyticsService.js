import api from './api';

const getMyClinicDashboard = (from, to) =>
  api.get('/analytics/clinic/my/dashboard', { params: { from, to } });

const getMyEmployerDashboard = (from, to) =>
  api.get('/analytics/employer/my/dashboard', { params: { from, to } });

const getClinicDashboard = (id, from, to) =>
  api.get(`/analytics/clinic/${id}/dashboard`, { params: { from, to } });

const getEmployerDashboard = (id, from, to) =>
  api.get(`/analytics/employer/${id}/dashboard`, { params: { from, to } });

const analyticsService = { getMyClinicDashboard, getMyEmployerDashboard, getClinicDashboard, getEmployerDashboard };
export default analyticsService;
