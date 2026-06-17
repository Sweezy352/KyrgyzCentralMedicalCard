import React, { useEffect, useState } from 'react';
import insuranceService from '../services/insuranceService';
import medicineInsuranceService from '../services/medicineInsuranceService';
import userService from '../services/userService';

const InsurancePage = () => {
  const [insurances, setInsurances] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');

  useEffect(() => {
    const load = async () => {
      try {
        const userRes = await userService.getCurrentUser();
        const insRes = await insuranceService.getUserInsurancesByUserId(userRes.data.id);
        const detailed = await Promise.all(
          insRes.data.map(async (ui) => {
            const med = await medicineInsuranceService.getMedicineInsuranceById(ui.medicineInsuranceId);
            return { ...ui, medicineInsurance: med.data };
          })
        );
        setInsurances(detailed);
      } catch {
        setError('Не удалось загрузить страховки.');
      } finally {
        setLoading(false);
      }
    };
    load();
  }, []);

  if (loading) return <div className="loading-state"><div className="loading-spinner" />Загрузка...</div>;
  if (error) return <div className="alert alert-error">{error}</div>;

  return (
    <div>
      <h1 className="page-title">Мои Страховки</h1>
      {insurances.length === 0 ? (
        <div className="empty-state">Страховок нет.</div>
      ) : (
        <div className="card-list">
          {insurances.map(ui => (
            <div key={ui.id} className="card">
              <div className="card-header">
                <h3>{ui.medicineInsurance?.insuranceName || 'Страховка'}</h3>
                <span className={`badge ${ui.active ? 'badge-green' : 'badge-gray'}`}>
                  {ui.active ? 'Активна' : 'Истекла'}
                </span>
              </div>
              <div className="card-body">
                <p>Стоимость: <strong>{ui.medicineInsurance?.price} сом</strong></p>
              </div>
              <div className="card-footer">
                <span>Начало: {new Date(ui.dateActive).toLocaleDateString('ru-RU')}</span>
                <span>Конец: {new Date(ui.dateExpire).toLocaleDateString('ru-RU')}</span>
              </div>
            </div>
          ))}
        </div>
      )}
    </div>
  );
};

export default InsurancePage;
