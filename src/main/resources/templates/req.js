//GET запрос на одного юзера
fetch('/admin/users/1').then(response => response.json().then(console.log))

//GET запрос на всех юзеров
fetch('/admin/users/').then(response => response.json().then(console.log))

//POST создаём юзера
fetch(
    '/admin/users/',
    {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(
            {
                "id": 2,
                "firstName": "Mish",
                "lastName": "A",
                "workExp": 2,
                "birthDate": "1997-11-17",
                "login": "misha",
                "password": "admin",
                "roles": [{"id": 2, "name": "ROLE_ADMIN", "authority": "ROLE_ADMIN"}, {
                    "id": 1,
                    "name": "ROLE_USER",
                    "authority": "ROLE_USER"
                }]
            }
        )
    }
).then(result => result.json().then(console.log))

// PUT апдейт существующего
fetch(
    '/admin/users/18',
    {
        method: 'PUT',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(
            {
                "id": 18,
                "firstName": "Misha",
                "lastName": "Antropov",
                "workExp": 4,
                "birthDate": "1996-11-17",
                "login": "mishak",
                "password": "pass",
                "roles": [{
                    "id": 1,
                    "name": "ROLE_USER",
                    "authority": "ROLE_USER"
                },
                    {
                        "id": 2,
                        "name": "ROLE_ADMIN",
                        "authority": "ROLE_ADMIN"
                    }
                ]
            }
        )
    }
).then(result => result.json().then(console.log));

