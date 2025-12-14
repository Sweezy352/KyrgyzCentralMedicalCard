const LoginForm = document.getElementById('logform')
const UserName = document.getElementById('username')
const PassWord = document.getElementById('password')
const LoginBtn = document.getElementById('login')
const ToRegBtn = document.getElementById('toreg')
const Status = document.getElementById('status')

const RegForm = document.getElementById('regform')
const RegUserName = document.getElementById('rusername')
const RegPassWord = document.getElementById('rpassword')
const RegBtn = document.getElementById('register')
const ToLogBtn = document.getElementById('tolog')
const RegStatus = document.getElementById('rstatus')

ToRegBtn.addEventListener('click', function() {
    LoginForm.style.display = 'none'
    RegForm.style.display = 'flex'
})

ToLogBtn.addEventListener('click', function() {
    LoginForm.style.display = 'flex'
    RegForm.style.display = 'none'
})

LoginForm.addEventListener('submit', function(event) {
    event.preventDefault()
})

RegForm.addEventListener('submit', function(event) {
    event.preventDefault()
})

RegBtn.addEventListener('click', function() {

    const DataToSend = {
        username: UserName,
        password: PassWord
    }  

    console.log(UserName.value)

    fetch('http://127.0.0.1:5500/second.html?' , {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json; charset=UTF-8',
        },
        body: JSON.stringify(DataToSend)
    })

    .then(Response => {
        if (Response.ok) {
            return Response.json()
        }
        throw new Error('Ошибка сети')
    })

    .then(data => {
        if (data && data.token) {
            localStorage.setItem('userToken', data.token)
            console.log('Токен сохранён:', data.token)
        }

        console.log('Успех:', data)
        Status.textContent = 'Данные успешно отправлены'
        Status.style.color = 'green'
        form.reset()
    })

    .catch(error => {
        console.error('Ошибка',error)
        Status.textContent = 'Ошибка при отправке'
        Status.style.color = 'red'
    })


})

LoginBtn.addEventListener('click', function() {

    const DataToSend = {
        username: UserName,
        password: PassWord
    }  

    console.log(UserName.value)

    fetch('http://127.0.0.1:5500/second.html?' , {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json; charset=UTF-8',
        },
        body: JSON.stringify(DataToSend)
    })

    .then(Response => {
        if (Response.ok) {
            return Response.json()
        }
        throw new Error('Ошибка сети')
    })

    .then(data => {
        if (data && data.token) {
            localStorage.setItem('userToken', data.token)
            console.log('Токен сохранён:', data.token)
        }

        console.log('Успех:', data)
        Status.textContent = 'Данные успешно отправлены'
        Status.style.color = 'green'
        form.reset()
    })

    .catch(error => {
        console.error('Ошибка',error)
        Status.textContent = 'Ошибка при отправке'
        Status.style.color = 'red'
    })


})