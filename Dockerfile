# Используем надежный базовый образ с Java 17
FROM openjdk:21

# Устанавливаем рабочий каталог внутри контейнера
WORKDIR /app

# Копируем скомпилированный JAR-файл в контейнер под стандартным именем app.jar
# Маска (*) позволяет не зависеть от версии в имени файла
COPY KyrgyzCentralMedicalCard/target/KyrgyzstanCentralMedicalCard-0.0.1-SNAPSHOT.jar  KyrgyzstanCentralMedicalCard.jar

# Указываем, что контейнер будет слушать порт 8080
EXPOSE 8080

# Команда для запуска приложения при старте контейнера
ENTRYPOINT ["java", "-jar", "app.jar"]
