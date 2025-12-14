// Базовый URL вашего API
const baseUrl = 'http://localhost:8080/api/auth';

// 1. Регистрация нового пользователя
const registerUser = (userData) => {
    fetch(`http://localhost:8080/api/auth/register`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(userData)
    })
    .then(response => {
        if (response.status === 201) {
            console.log('Registration successful!');
            return response.text(); // или .json() если сервер возвращает тело
        } else {
            // Попытка прочитать тело ошибки для получения дополнительной информации
            return response.json().then(errorBody => {
                throw new Error(`Registration failed with status: ${response.status}. Body: ${JSON.stringify(errorBody)}`);
            });
        }
    })
    .then(data => console.log('Response:', data))
    .catch(error => console.error('Error during registration:', error));
};

// 2. Вход в систему (аутентификация)
const loginUser = (credentials) => {
    fetch(`${baseUrl}/login`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(credentials)
    })
    .then(response => {
        if (response.ok) {
            return response.text(); // Ожидаем токен в виде простого текста
        } else {
            return response.json().then(errorBody => {
                throw new Error(`Login failed with status: ${response.status}. Body: ${JSON.stringify(errorBody)}`);
            });
        }
    })
    .then(token => {
        console.log('Login successful! Token:', token);
        localStorage.setItem('authToken', token);
    })
    .catch(error => console.error('Error during login:', error));
};

// 3. Получение данных текущего аутентифицированного пользователя
const getCurrentUser = (authToken) => {

    authToken = localStorage.getItem('authToken')

    fetch(`${baseUrl}/get-current`, {
        method: 'GET',
        headers: {
            'Authorization': `Bearer ${authToken}`, // Передаем токен
            'Content-Type': 'application/json'
        }
    })
    .then(response => {
        if (response.ok) {
            return response.json();
        } else {
            return response.json().then(errorBody => {
                throw new Error(`Failed to get current user. Status: ${response.status}. Body: ${JSON.stringify(errorBody)}`);
            });
        }
    })
    .then(data => console.log('Current User:', data))
    .catch(error => console.error('Error fetching current user:', error));
};


// --- Примеры использования ---

// 1. Пример регистрации
const newUser = {
    inn: "12345678901235", // Уникальный ИНН
    fio: "Новый Пользователь",
    password: "password123",
};
// registerUser(newUser);


// 2. Пример входа в систему
const loginCredentials = {
    inn: "12345678901235",
    password: "password123"
};
// loginUser(loginCredentials);


// 3. Пример получения текущего пользователя
// Сначала выполните loginUser, чтобы получить токен,
// затем вставьте его сюда.
const tokenFromLogin = 'ваш_полученный_токен';
// getCurrentUser(tokenFromLogin);
