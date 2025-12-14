document.addEventListener('DOMContentLoaded', () => {
    const baseUrl = 'http://localhost:8080/api/auth';
    
    const registerForm = document.getElementById('RegMenu');
    const regname = document.getElementById('rrealname');
    const reglogin = document.getElementById('rusername');
    const regpassword = document.getElementById('rpassword');
    const bloodgroup = document.getElementById('bloodgroup')
    const bloodrh = document.getElementById('bloodrh')
    const gender = document.getElementById('gender')
    const birthdate = document.getElementById('birthdate')


    // Обработчик регистрации
    registerForm.addEventListener('submit', async (event) => {
        const data = {
            inn: reglogin.value,
            fio: regname.value,
            password: regpassword.value,
            blood: bloodgroup.value,
            rh: bloodrh.value,
            gender: gender.value,
            birthday: birthdate.value,
        }
        console.log(data)

        if (regname.value != ''  && reglogin.value != '' && regpassword.value != '' && bloodgroup.value != '' && bloodrh.value != '' && gender.value != '' && birthdate.value != '') {
            try {
                const response = await fetch(`${baseUrl}/register`, {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify(data)
                });
    
                if (!response.ok) {
                    const errorData = await response.json().catch(() => ({ message: 'Не удалось получить тело ошибки' }));
                    throw new Error(`Статус: ${response.status}. ${errorData.message || 'Ошибка сервера'}`);
                }
    
       
                registerForm.reset();
    
            } catch (error) {
        }
        } else {
            const regresult = document.getElementById('regresult')
            regresult.textContent = 'Ошибка! Заполните поля!'
            regresult.style.color = 'red'
            regresult.style.fontSize = '30px'
        }
    });

    
    const loginForm = document.getElementById('LoginMenu');
    const login = document.getElementById('username');
    const logpassword = document.getElementById('password');
    

    loginForm.addEventListener('submit', async (event) => {
        event.preventDefault();

        const data = {
            inn: login.value,
            password: logpassword.value

        }
    
    
        try {
            const response = await fetch(`${baseUrl}/login`, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(data)
            });
    
            if (!response.ok) {
                const errorData = await response.json().catch(() => ({ message: 'Не удалось получить тело ошибки' }));
                throw new Error(`Статус: ${response.status}. ${errorData.message || 'Неверный ИНН или пароль'}`);
            }
    
            const token = await response.text();
    
            

            if (response.status == '200') {
                localStorage.setItem('authToken', token)

                window.location.href = '/pages/mainpage.html'
            }
        } catch (error) {

        }

    });
})





































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