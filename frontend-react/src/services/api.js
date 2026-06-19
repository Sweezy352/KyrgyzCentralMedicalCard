import axios from 'axios';

const api = axios.create({ baseURL: '/api' });

api.interceptors.request.use((config) => {
  const token = localStorage.getItem('token');
  if (token) config.headers.Authorization = `Bearer ${token}`;
  return config;
});

// Ошибки не вызывают принудительный редирект — страницы сами показывают сообщение.
// Доступом без токена управляет ProtectedRoute.
api.interceptors.response.use(
  (res) => res,
  (error) => Promise.reject(error)
);

export default api;
