import React, { useEffect, useState } from 'react';
import insuranceService from '../services/insuranceService';
import medicineInsuranceService from '../services/medicineInsuranceService';
import userService from '../services/userService';
import './InsurancePage.css'; // Создадим этот файл стилей

const InsurancePage = () => {
  const [userInsurances, setUserInsurances] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');

  useEffect(() => {
    const fetchInsurances = async () => {
      try {
        const userResponse = await userService.getCurrentUser();
        const userId = userResponse.data.id;

        if (userId) {
          const userInsurancesResponse = await insuranceService.getUserInsurancesByUserId(userId);
          const insurancesData = userInsurancesResponse.data;

          // Для каждой страховки пользователя получаем детали самой медицинской страховки
          const detailedInsurances = await Promise.all(
            insurancesData.map(async (userInsurance) => {
              const medicineInsuranceDetails = await medicineInsuranceService.getMedicineInsuranceById(userInsurance.medicineInsuranceId);
              return {
                ...userInsurance,
                medicineInsurance: medicineInsuranceDetails.data, // Добавляем детали страховки
              };
            })
          );
          setUserInsurances(detailedInsurances);
        } else {
          setError('Не удалось определить пользователя.');
        }
      } catch (err) {
        setError('Не удалось загрузить информацию о страховках.');
        console.error('Fetch insurances error:', err);
      } finally {
        setLoading(false);
      }
    };

    fetchInsurances();
  }, []);

  if (loading) {
    return <div className="loading">Загрузка страховок...</div>;
  }

  if (error) {
    return <div className="error">{error}</div>;
  }

  return (
    <div className="insurance-container">
      <h1>Мои Страховки</h1>
      {userInsurances.length > 0 ? (
        <div className="insurance-list">
          {userInsurances.map((userInsurance) => (
            <div key={userInsurance.id} className={`insurance-card ${userInsurance.active ? 'active' : 'inactive'}`}>
              <h3>{userInsurance.medicineInsurance?.name || 'Название страховки'}</h3>
              <p>{userInsurance.medicineInsurance?.description || 'Описание страховки'}</p>
              <div className="card-details">
                <span><strong>Статус:</strong> {userInsurance.active ? 'Активна' : 'Неактивна'}</span>
                <span><strong>Начало:</strong> {new Date(userInsurance.dateActive).toLocaleDateString()}</span>
                <span><strong>Окончание:</strong> {new Date(userInsurance.dateExpire).toLocaleDateString()}</span>
              </div>
            </div>
          ))}
        </div>
      ) : (
        <p>У вас пока нет оформленных страховок.</p>
      )}
    </div>
  );
};

export default InsurancePage;
