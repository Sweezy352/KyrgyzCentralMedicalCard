document.addEventListener('DOMContentLoaded', () => {
    const baseUrl = 'http://localhost:8080'
    const toinfo = document.getElementById('toinfo')
    const tohistory = document.getElementById('tohistory')
    const toalergic = document.getElementById('toalergic')
    const torecept = document.getElementById('torecept')
    let curpage = 'info'

    const infopage = document.getElementById('info')
    const historypage = document.getElementById('history')
    const alergicpage = document.getElementById('alergic')
    const receptpage = document.getElementById('recept')

    toinfo.addEventListener('click', () => {
        if (curpage != 'info') {
            toinfo.style.textDecoration = 'underline'
            tohistory.style.textDecoration = 'none'
            toalergic.style.textDecoration = 'none'
            torecept.style.textDecoration = 'none'

            infopage.style.display = 'flex'
            historypage.style.display = 'none'
            alergicpage.style.display = 'none'
            receptpage.style.display = 'none'

            curpage = 'info'
        }
    })

    tohistory.addEventListener('click', () => {
        if (curpage != 'history') {
            toinfo.style.textDecoration = 'none'
            tohistory.style.textDecoration = 'underline'
            toalergic.style.textDecoration = 'none'
            torecept.style.textDecoration = 'none'

            infopage.style.display = 'none'
            historypage.style.display = 'flex'
            alergicpage.style.display = 'none'
            receptpage.style.display = 'none'
            curpage = 'history'
        }
    })

    toalergic.addEventListener('click', () => {
        if (curpage != 'alergic') {
            toinfo.style.textDecoration = 'none'
            tohistory.style.textDecoration = 'none'
            toalergic.style.textDecoration = 'underline'
            torecept.style.textDecoration = 'none'

            infopage.style.display = 'none'
            historypage.style.display = 'none'
            alergicpage.style.display = 'grid'
            receptpage.style.display = 'none'
            curpage = 'alergic'
        }
    })

    torecept.addEventListener('click', () => {
        if (curpage != 'recept') {
            toinfo.style.textDecoration = 'none'
            tohistory.style.textDecoration = 'none'
            toalergic.style.textDecoration = 'none'
            torecept.style.textDecoration = 'underline'

            infopage.style.display = 'none'
            historypage.style.display = 'none'
            alergicpage.style.display = 'none'
            receptpage.style.display = 'flex'
            curpage = 'recept'
        }
    })

    const SaverMenuBtn = document.getElementById('checksaver')
    const SaverMenuKrestik = document.getElementById('KrestVObr')
    const SaverMenu = document.getElementById('ObrSaver')

    SaverMenuBtn.addEventListener('click', () => {
        SaverMenu.style.display = 'flex'
    })

    SaverMenuKrestik.addEventListener('click', () => {
        SaverMenu.style.display = 'none'
    })

    const ENOMenuBtn = document.getElementById('changeextranumber')
    const ENOMenuKrestik = document.getElementById('ENOKrestik')
    const ENOMenu = document.getElementById('ENO')

    ENOMenuBtn.addEventListener('click', () => {
        ENOMenu.style.display = 'flex'
    })

    ENOMenuKrestik.addEventListener('click', () => {
        ENOMenu.style.display = 'none'
    })

    const BDMenuBtn = document.getElementById('changebirthday')
    const BDMenuKrestik = document.getElementById('BDKrestik')
    const BDMenu = document.getElementById('BD')

    BDMenuBtn.addEventListener('click', () => {
        BDMenu.style.display = 'flex'
    })

    BDMenuKrestik.addEventListener('click', () => {
        BDMenu.style.display = 'none'
    })

    const BGMenuBtn = document.getElementById('changeblood')
    const BGMenuKrestik = document.getElementById('BGKrestik')
    const BGMenu = document.getElementById('BG')

    BGMenuBtn.addEventListener('click', () => {
        BGMenu.style.display = 'flex'
    })

    BGMenuKrestik.addEventListener('click', () => {
        BGMenu.style.display = 'none'
    })


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
            const jsonPayload = decodeURIComponent(window.atob(base64).split('').map(function(c) {
                return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
            }).join(''));
    
            return JSON.parse(jsonPayload);
        } catch (error) {
            console.error("Ошибка при декодировании токена:", error);
            return null;
        }
    }
    


    const profilename = document.getElementById('profilename')
    const profileid = document.getElementById('profileid')
    const infopagename = document.getElementById('infopagename')
    const infopagegender = document.getElementById('infopagegender')
    const infopagebirthday = document.getElementById('infopagebirthday')
    const bloodgroup = document.getElementById('bloodgroup')
    const bloodrh = document.getElementById('bloodrh')
    const extranumberphone = document.getElementById('extranumberphone')
    const extranumbername = document.getElementById('extranumbername')
    
    const token = localStorage.getItem('authToken')
    const id = decodeJwtToken(token)

    async function getUser() {
        const response = await fetch(`${baseUrl}/api/users/get-by-id/${id.id}`,{
            method: 'GET',
            headers: {
                'Authorization': `Bearer ${token}`,
                'Content-Type': 'application/json'
            },
        })
        
        const userData = await response.json()
        console.log(userData)

        profilename.innerHTML = userData.fio
        profileid.innerHTML = `ИНН: ${userData.inn}`
        infopagename.innerHTML = userData.fio
        infopagegender.innerHTML = userData.gender
        bloodgroup.innerHTML = userData.blood
        bloodrh.innerHTML = userData.rh
        infopagebirthday.innerHTML = userData.birthday
        if (!userData.en) {
            extranumberphone.innerHTML = 'Неизвестное номер'
        } else {
            extranumberphone.innerHTML = userData.en
        } 
        if (!userData.enName) {
            extranumbername.innerHTML = 'Неизвестное имя'
        } else {
            extranumbername.innerHTML = userData.enName
        } 
    }
    
    getUser()

    const EnForm = document.getElementById('ENOMain');

    

    EnForm.addEventListener('submit', async (event) => {
        event.preventDefault();
        

        async function UpdateUser() {
            const token = localStorage.getItem('authToken')
            const id = decodeJwtToken(token)

            const response = await fetch(`${baseUrl}/api/users/get-by-id/${id.id}`,{
                method: 'GET',
                headers: {
                    'Authorization': `Bearer ${token}`,
                    'Content-Type': 'application/json'
                },
            })
            
            const userData = await response.json()
            const getEnName = document.getElementById('ENOName');
            const getEn = document.getElementById('ENOPhone');

            const data = {
                id: userData.id,
                inn: userData.inn,
                fio: userData.fio,
                gender: userData.gender,
                birthday: userData.birthday,
                en: getEn.value,
                enName: getEnName.value,
                blood: userData.blood,
                rh: userData.rh
            }

            console.log(userData)
            console.log(token)
            ENOMenu.style.display = 'flex'
            ENOMenu.style.display = 'none'


            fetch(`${baseUrl}/api/users/update`, {
                method: 'POST',
                headers: {
                    'Authorization': `Bearer ${token}`,
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(data)
                
            })
            
        }

        
        UpdateUser()
        getUser()
    });
    const BDMain = document.getElementById('BDMain')

    BDMain.addEventListener('submit', async (event) => {
        event.preventDefault();
        

        async function UpdateUser() {
            const token = localStorage.getItem('authToken')
            const id = decodeJwtToken(token)

            const response = await fetch(`${baseUrl}/api/users/get-by-id/${id.id}`,{
                method: 'GET',
                headers: {
                    'Authorization': `Bearer ${token}`,
                    'Content-Type': 'application/json'
                },
            })
            
            const userData = await response.json()
            const getDate = document.getElementById('BDDate');

            const data = {
                id: userData.id,
                inn: userData.inn,
                fio: userData.fio,
                gender: userData.gender,
                birthday: getDate.value,
                en: userData.en,
                enName: userData.enName,
                blood: userData.blood,
                rh: userData.rh
            }

            console.log(userData)
            console.log(token)
            BDMenu.style.display = 'none'


            fetch(`${baseUrl}/api/users/update`, {
                method: 'POST',
                headers: {
                    'Authorization': `Bearer ${token}`,
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(data)
                
            })
            
        }

        
        UpdateUser()
        getUser()
    });
    const BGMain = document.getElementById('BGMain')

    BGMain.addEventListener('submit', async (event) => {
        event.preventDefault();
        

        async function UpdateUser() {
            const token = localStorage.getItem('authToken')
            const id = decodeJwtToken(token)

            const response = await fetch(`${baseUrl}/api/users/get-by-id/${id.id}`,{
                method: 'GET',
                headers: {
                    'Authorization': `Bearer ${token}`,
                    'Content-Type': 'application/json'
                },
            })
            
            const userData = await response.json()
            const getBG = document.getElementById('getBG');
            const getRH = document.getElementById('getRH');

            const data = {
                id: userData.id,
                inn: userData.inn,
                fio: userData.fio,
                gender: userData.gender,
                birthday: userData.birthday,
                en: userData.en,
                enName: userData.enName,
                blood: getBG.value,
                rh: getRH.value
            }

            console.log(userData)
            console.log(token)
            BGMenu.style.display = 'none'


            fetch(`${baseUrl}/api/users/update`, {
                method: 'POST',
                headers: {
                    'Authorization': `Bearer ${token}`,
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(data)
                
            })
            
        }

        
        UpdateUser()
        getUser()
    });
    getUser()
    



    const SaverName = document.getElementById('savertype')
    const ObrSaverName = document.getElementById('ObrSaverName')
    const saverenddata = document.getElementById('saverenddata')
    const OSD1 = document.getElementById('OSD1')
    const OSD2 = document.getElementById('OSD2')
    const OSD3 = document.getElementById('OSD3')
    const OSD4 = document.getElementById('OSD4')
    const OSD5 = document.getElementById('OSD5')
    const OSD6 = document.getElementById('OSD6')

    async function hui() {
        const response1 = await fetch(`${baseUrl}/api/user-insurance/get-by-user`,{
            method: 'GET',
            headers: {
                'Authorization': `Bearer ${token}`,
                'Content-Type': 'application/json'
            }
        })
            
            const insurancedata = await response1.json()
            const ExpData = insurancedata.dateExpire
            saverenddata.innerHTML = `Дата окончания: ${ExpData}`
            console.log(insurancedata)


            const a = await fetch(`${baseUrl}/api/medicine-insurance/get-by-id/${insurancedata.medicineInsuranceId}`, {
                method: 'GET',
                headers: {
                    'Authorization': `Bearer ${token}`,
                    'Content-Type': 'application/json'}
            })
                const indata = await a.json()
                const NameData = indata.insuranceName
                const TableData = indata.description
                console.log(indata)
                const ReadyTable = JSON.parse(TableData)
                SaverName.innerHTML = NameData
                ObrSaverName.innerHTML = NameData
                console.log(indata)
                if (NameData == 'ОМС') {
                    OSD1.innerHTML = ReadyTable.d1
                    OSD2.innerHTML = ReadyTable.d2
                    OSD3.innerHTML = ReadyTable.d3
                    OSD4.innerHTML = ReadyTable.d4
                } else {
                    OSD1.innerHTML = ReadyTable.d1
                    OSD2.innerHTML = ReadyTable.d2
                    OSD3.innerHTML = ReadyTable.d3
                    OSD4.innerHTML = ReadyTable.d4
                    OSD5.innerHTML = ReadyTable.d5
                    OSD6.innerHTML = ReadyTable.d6
                    }
                
            


    }

    hui()
})