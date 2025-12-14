const logform = document.getElementById('LoginMenu')
const login = document.getElementById('username')
const logpassword = document.getElementById('password')
const logbutton = document.getElementById('login')
const logresult = document.getElementById('resultlog')


logform.addEventListener('submit', (event) => {
    event.preventDefault()
    

    if (!login.value || !logpassword.value) {
        logresult.innerHTML = '<p style="color: red;">❌ Пожалуйста, введите логин и пароль.</p>';
        return;
    }


    fetch(`http://localhost:3000/UserData/${login.value}`)
      .then((response) => {
        console.log('response:',response)

        if (!response.ok) {
            const errortext = response.status === 404
                ? '❌ Аккаунт не найден.' 
                : '❌ Что-то пошло не так на сервере :('
            
            throw new Error(errortext)
        }
        
        return response.json()
      })
      .then(json => {
        console.log('Полученные данные:', json)

        const storedPassword = json.password 
        const enteredPassword = logpassword.value 


        if (enteredPassword == storedPassword) {

            logresult.innerHTML = `<p style="color: green;">✅ Успешный вход! Добро пожаловать, ${json.name}.</p>`;
            const MainAccountLogin = login
            console.log(MainAccountLogin)
            

            // window.location.replace('/da.html'); 
            
        } else {

            logresult.innerHTML = '<p style="color: red;">❌ Неверный пароль.</p>';
        }
      })
      .catch((error) => {
        logresult.innerHTML = `<p style="color: red;">${error.message}</p>`
      })
})














const regform = document.getElementById('RegMenu');
const regname = document.getElementById('rrealname')
const register = document.getElementById('rusername');
const regpassword = document.getElementById('rpassword');
const regpasswordrepeat = document.getElementById('rpasswordrepeat');
const regbutton = document.getElementById('register');
const regresult = document.getElementById('resultreg');

regform.addEventListener('submit', (event) => {
    event.preventDefault(); 

    const newId = register.value.trim();
    const newPassword = regpassword.value;
    const newName = regname.value

    // --- ИСПРАВЛЕНИЕ #1: ПРОВЕРКА ПАРОЛЕЙ ПЕРЕД ЗАПРОСОМ К СЕРВЕРУ ---
    if (newPassword !== regpasswordrepeat.value) {
        regresult.innerHTML = '<p style="color: red;">❌ Пароли не совпадают.</p>';
        return;
    }
    
    if (!newId || !newPassword) {
         regresult.innerHTML = '<p style="color: red;">❌ Заполните все поля.</p>';
         return;
    }
    // ------------------------------------------------------------------

    regresult.innerHTML = '<p>Проверка доступности логина...</p>';


    fetch(`http://localhost:3000/UserData/${newId}`)
        .then(async (response) => { // Используем async для await

            // 1. Проверяем, существует ли пользователь (если 200 OK)
            if (response.ok) {
                const json = await response.json(); // Парсим JSON, если ответ OK
                throw new Error(`Логин "${json.id}" уже занят. Попробуйте другой.`);
            }

            // 2. Если ответ 404, логин свободен, возвращаем null (НЕ ПАРСИМ JSON)
            if (response.status === 404) {
                return null; 
            }
            
            // 3. Обработка других ошибок сервера (500 и т.д.)
            // В этом случае ответ может быть не JSON, поэтому мы читаем его как текст
            const errorText = await response.text(); 
            throw new Error(`Ошибка сервера при проверке ID (${response.status}): ${errorText.substring(0, 50)}...`);
        })

        .then(data => {

            if (data === null) {
                // Логин свободен, отправляем данные на регистрацию
                const RegDataToSend = {
                    id: newId,
                    password: newPassword,
                    name: newName
                };

                return fetch(`http://localhost:3000/UserData`, {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify(RegDataToSend)
                })
                .then(async (response) => {
                    if (!response.ok) {
                        const errorText = await response.text();
                        throw new Error(`Ошибка при регистрации: ${errorText.substring(0, 50)}...`);
                    }
                    return response.json(); // Ответ POST должен быть JSON
                });
            }
        })
        .then((json) => {
            console.log('Регистрация успешно завершена:', json);
            regresult.innerHTML = `<p style="color: green;">✅ Успех! Пользователь ${json.id} зарегистрирован.</p>`;
            // window.location.replace('/da.html')
        })
        .catch((error) => {
            console.error('Ошибка в процессе регистрации:', error.message);
            regresult.innerHTML = `<p style="color: red;">❌ ${error.message}</p>`;
        });
}); 

// Код для переключения меню (ниже)
// ...




























const SetReg = document.getElementById('RegBtn')
const SetForgotPassword = document.getElementById('ForgotBtn')
const SetLogin = document.getElementById('LoginBtn')
const SetVerify = document.getElementById('SendSMS')
const SetNewPassport = document.getElementById('ChangePassword')

const LoginPage = document.getElementById('LoginMenu')
const RegisterPage = document.getElementById('RegMenu')
const ForgotPasswordPage = document.getElementById('ForgotPassword')
const VerificationPage = document.getElementById('Verify')
const RecoverPasswordPage = document.getElementById('NewPassword')

SetReg.addEventListener('click' , function () {
    LoginPage.style.display = 'none'
    RegisterPage.style.display = 'flex'
})

SetForgotPassword.addEventListener('click' , function () {
    LoginPage.style.display = 'none'
    RegisterPage.style.display = 'none'
    ForgotPasswordPage.style.display = 'flex'
})

SetLogin.addEventListener('click', function () {
    LoginPage.style.display = 'flex'
    RegisterPage.style.display = 'none'
    ForgotPasswordPage.style.display = 'none'
})

SetVerify.addEventListener('click', function (event) {
    event.preventDefault()
    const VPEmailInput = document.getElementById('femail')
    const VPEmail = VPEmailInput.value.trim()

    if (!VPEmail) {
        alert('Пожалуйста, введите email');
        VPEmailInput.focus(); // Ставим курсор обратно в поле
        return; // ← ВАЖНО! Останавливаем выполнение функции
    }

    if (!VPEmail.includes('@') || !VPEmail.includes('.')) {
        alert('Введите корректный email адрес');
        VPEmailInput.focus();
        return;
    }

    ForgotPasswordPage.style.display = 'none'
    VerificationPage.style.display = 'flex'
})

SetNewPassport.addEventListener('click', function (event) {
    event.preventDefault()
    VerificationPage.style.display = 'none'
    RecoverPasswordPage.style.display = 'flex'
})