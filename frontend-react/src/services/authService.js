import axios from 'axios';

const login = async (inn, password) => {
  const response = await axios.post('/api/auth/login', { inn, password });
  if (response.data?.token) {
    localStorage.setItem('token', response.data.token);
    localStorage.setItem('userRoles', JSON.stringify(response.data.roles));
  }
  return response.data;
};

const register = (inn, fio, password, gender, birthDate, emergencyPhone, bloodGroup, rhFactor) =>
  axios.post('/api/auth/register', { inn, fio, password, gender, birthDate, emergencyPhone, bloodGroup, rhFactor });

const logout = () => {
  localStorage.removeItem('token');
  localStorage.removeItem('userRoles');
};

const getCurrentUserToken = () => localStorage.getItem('token');

const getCurrentUserRoles = () => {
  const roles = localStorage.getItem('userRoles');
  return roles ? JSON.parse(roles) : [];
};

const authService = { login, register, logout, getCurrentUserToken, getCurrentUserRoles };
export default authService;
