import React, { useEffect, useState } from 'react';
import allergieService from '../services/allergieService';
import userService from '../services/userService';
import './AllergiesPage.css'; // Создадим этот файл стилей

const AllergiesPage = () => {
  const [allergies, setAllergies] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');

  useEffect(() => {
    const fetchAllergies = async () => {
      try {
        // 1. Получаем данные текущего пользователя, чтобы узнать его ID
        const userResponse = await userService.getCurrentUser();
        const userId = userResponse.data.id;

        if (userId) {
          // 2. Загружаем аллергии для этого пользователя
          const allergiesResponse = await allergieService.getAllergiesByUserId(userId);
          setAllergies(allergiesResponse.data);
        } else {
          setError('Не удалось определить пользователя.');
        }
      } catch (err) {
        setError('Не удалось загрузить список аллергий.');
        console.error('Fetch allergies error:', err);
      } finally {
        setLoading(false);
      }
    };

    fetchAllergies();
  }, []);

  if (loading) {
    return <div className="loading">Загрузка аллергий...</div>;
  }

  if (error) {
    return <div className="error">{error}</div>;
  }

  return (
    <div className="allergies-container">
      <h1>Мои Аллергии</h1>
      {allergies.length > 0 ? (
        <div className="allergies-list">
          {allergies.map((allergie) => (
            <div key={allergie.id} className={`allergie-card ${allergie.status ? 'active' : 'inactive'}`}>
              <h3>{allergie.name}</h3>
              <p>{allergie.description}</p>
              <div className="card-footer">
                <span>Статус: {allergie.status ? 'Активна' : 'Неактивна'}</span>
                <span>Дата: {new Date(allergie.dateCreated).toLocaleDateString()}</span>
                {allergie.doctorView && <span>Врач: {allergie.doctorView.fio}</span>}
              </div>
            </div>
          ))}
        </div>
      ) : (
        <p>У вас пока нет зарегистрированных аллергий.</p>
      )}
    </div>
  );
};

export default AllergiesPage;
