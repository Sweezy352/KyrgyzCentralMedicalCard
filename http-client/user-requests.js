// Предполагается, что у вас есть токен авторизации (например, JWT),
// который нужно передавать в заголовке Authorization.
const authToken = 'Bearer ваш_токен'; // Замените на ваш реальный токен

// Базовый URL вашего API
const baseUrl = 'http://localhost:8080/api/users';

// 1. Получение всех пользователей
const getAllUsers = () => {
    fetch(`${baseUrl}/get-all-users`, {
        method: 'GET',
        headers: {
            'Authorization': authToken,
            'Content-Type': 'application/json'
        }
    })
    .then(response => response.json())
    .then(data => console.log('All Users:', data))
    .catch(error => console.error('Error fetching all users:', error));
};

// 2. Получение пользователя по ID
const getUserById = (asdas) => {

    const token = localStorage.getItem('authToken')
    fetch(`${baseUrl}/get-by-id/${id}`, {
        method: 'GET',
        headers: {
            'Authorization': authToken,
            'Content-Type': 'application/json'
        }
    })
    .then(response => response.json())
    .then(data => console.log(`User with ID ${id}:`, data))
    .catch(error => console.error(`Error fetching user with ID ${id}:`, error));
};

// 3. Получение пользователя по ФИО
const getUserByFio = (fio) => {
    if (!fio) {
        console.error('FIO is required for getUserByFio');
        return;
    }
    const url = new URL(`${baseUrl}/get-by-fio`);
    url.searchParams.append('fio', fio);

    fetch(url, {
        method: 'GET',
        headers: {
            'Authorization': authToken,
            'Content-Type': 'application/json'
        }
    })
    .then(response => response.json())
    .then(data => console.log(`User with FIO "${fio}":`, data))
    .catch(error => console.error(`Error fetching user with FIO "${fio}":`, error));
};

// 4. Получение пользователя по ИНН
const getUserByInn = (inn) => {
    if (!inn) {
        console.error('INN is required for getUserByInn');
        return;
    }
    const url = new URL(`${baseUrl}/get-by-inn`);
    url.searchParams.append('inn', inn);

    fetch(url, {
        method: 'GET',
        headers: {
            'Authorization': authToken,
            'Content-Type': 'application/json'
        }
    })
    .then(response => response.json())
    .then(data => console.log(`User with INN "${inn}":`, data))
    .catch(error => console.error(`Error fetching user with INN "${inn}":`, error));
};

// --- Примеры использования ---
// Замените значения на реальные данные для тестирования

// getAllUsers();
// getUserById(1); // Замените 1 на существующий ID пользователя
// getUserByFio('Иванов Иван Иванович'); // Замените на существующее ФИО
// getUserByInn('12345678901234'); // Замените на существующий ИНН
