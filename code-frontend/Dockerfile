# Используем базовый образ Node.js
FROM node:18

# Устанавливаем рабочий каталог
WORKDIR /app

# Копируем package.json и package-lock.json
COPY package*.json ./

# Устанавливаем зависимости
RUN npm install

# Копируем остальные файлы проекта
COPY . .

# Собираем приложение (если это необходимо)
# RUN npm run build

# Устанавливаем serve для запуска статических файлов
RUN npm install -g serve

# Указываем порт
EXPOSE 3000

# Команда для запуска
CMD ["serve", "-s", "."]
