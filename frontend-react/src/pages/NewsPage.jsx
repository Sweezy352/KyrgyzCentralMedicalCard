import React, { useEffect, useState } from 'react';
import newsService from '../services/newsService';

const NewsPage = () => {
  const [news, setNews] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');

  useEffect(() => {
    newsService.getAllNews()
      .then(res => setNews(res.data))
      .catch(() => setError('Не удалось загрузить новости.'))
      .finally(() => setLoading(false));
  }, []);

  if (loading) return <div className="loading-state"><div className="loading-spinner" />Загрузка...</div>;
  if (error) return <div className="alert alert-error">{error}</div>;

  return (
    <div>
      <h1 className="page-title">Новости</h1>
      {news.length === 0 ? (
        <div className="empty-state">Новостей пока нет.</div>
      ) : (
        <div className="card-list">
          {news.map(item => (
            <div key={item.id} className="card">
              <div className="card-header"><h3>{item.name}</h3></div>
              <div className="card-body"><p>{item.description}</p></div>
              <div className="card-footer">
                <span>{new Date(item.dateCreated).toLocaleDateString('ru-RU')}</span>
              </div>
            </div>
          ))}
        </div>
      )}
    </div>
  );
};

export default NewsPage;
