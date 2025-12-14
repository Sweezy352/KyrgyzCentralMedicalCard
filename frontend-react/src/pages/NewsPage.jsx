import React, { useEffect, useState } from 'react';
import newsService from '../services/newsService';
import './NewsPage.css'; // Создадим этот файл стилей

const NewsPage = () => {
  const [news, setNews] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');

  useEffect(() => {
    const fetchNews = async () => {
      try {
        const response = await newsService.getAllNews();
        setNews(response.data);
      } catch (err) {
        setError('Не удалось загрузить новости.');
        console.error('Fetch news error:', err);
      } finally {
        setLoading(false);
      }
    };

    fetchNews();
  }, []);

  if (loading) {
    return <div className="loading">Загрузка новостей...</div>;
  }

  if (error) {
    return <div className="error">{error}</div>;
  }

  return (
    <div className="news-container">
      <h1>Последние Новости</h1>
      {news.length > 0 ? (
        <div className="news-list">
          {news.map((item) => (
            <div key={item.id} className="news-card">
              <h3>{item.name}</h3>
              <p>{item.description}</p>
              <div className="card-footer">
                <span>Дата публикации: {new Date(item.dateCreated).toLocaleDateString()}</span>
              </div>
            </div>
          ))}
        </div>
      ) : (
        <p>Пока нет новостей.</p>
      )}
    </div>
  );
};

export default NewsPage;
