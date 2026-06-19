-- Удаление функционала страховок, новостей и продуктов.
-- Порядок важен из-за внешних ключей:
--   user_insurances -> medicine_insurances
--   news_picture_files -> news
--   products -> company (company остаётся)

DROP TABLE IF EXISTS user_insurances;
DROP TABLE IF EXISTS medicine_insurances;
DROP TABLE IF EXISTS news_picture_files;
DROP TABLE IF EXISTS news;
DROP TABLE IF EXISTS products;
