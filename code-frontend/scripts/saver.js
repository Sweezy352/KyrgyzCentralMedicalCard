document.addEventListener('DOMContentLoaded', () => {
    const baseUrl = 'http://localhost:8080'
    function decodeJwtToken(token) {
        if (!token) {
            return null;
        }

        try {
            // JWT состоит из трех частей: header.payload.signature
            // Нам нужна вторая часть (payload)
            const base64Url = token.split('.')[1];

            // Декодирование из формата Base64 URL-safe в обычный Base64
            const base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');

            // Декодирование Base64 строки в JSON объект
            // window.atob() декодирует Base64 в строку, затем JSON.parse() преобразует строку в объект
            const jsonPayload = decodeURIComponent(window.atob(base64).split('').map(function (c) {
                return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
            }).join(''));

            return JSON.parse(jsonPayload);
        } catch (error) {
            console.error("Ошибка при декодировании токена:", error);
            return null;
        }
    }




    async function getX() {
        const token = localStorage.getItem('authToken')

        async function hrenX() {
            const token = localStorage.getItem('authToken')

            const response = await fetch(`${baseUrl}/api/user-insurance/add-medicine-insurance/1`, {
                method: 'POST',
                headers: {
                    'Authorization': `Bearer ${token}`,
                    'Content-Type': 'application/json'
                }
            })
        }

        hrenX()


    }

    gethealdmc = document.getElementById('gethealdmc')

    gethealdmc.addEventListener('click', async (event) => {
        event.preventDefault()
        getX()
    })












    async function getY() {
        const token = localStorage.getItem('authToken')



        async function hrenY() {
            const token = localStorage.getItem('authToken')

            const response = await fetch(`${baseUrl}/api/user-insurance/add-medicine-insurance/2`, {
                method: 'POST',
                headers: {
                    'Authorization': `Bearer ${token}`,
                    'Content-Type': 'application/json'
                }
            })

            const test = await response.json()
            console.log(test)

        }

        hrenY()


    }

    gethealomc = document.getElementById('gethealomc')

    gethealomc.addEventListener('click', async (event) => {
        event.preventDefault()
        getY()
    })

})