# Перевірка Spring Security, лабораторна 5

Міграція `V3__security.sql` у `flyway_schema_history`: version `3`, description `security`, success `true`.

## 1. Без сесії

| Метод | URL | Код | Результат |
| --- | --- | --- | --- |
| GET | http://localhost:8080/admin | 401 | список користувачів не віддається |
| GET | http://localhost:8080/hello | 200 | `{"message":"Hello, Spring"}` |

`GET /` — `200`, тіло `Main page!`. `GET /clients` — `200`, клієнти Daniel Brooks і Elena Navarro.

## 2. Сесія 1111 / 1111

`POST /login` з `username=1111` і `password=1111` (form-urlencoded) відповідає `302` на `/` і ставить сесійну cookie.

| Метод | URL | Код | Результат |
| --- | --- | --- | --- |
| GET | http://localhost:8080/admin | 200 | JSON-масив із трьох користувачів: Daniel, Elena, Sofia, пароль `1234` |
| GET | http://localhost:8080/users | 200 | `User Page` |

## 3. Сесія 2222 / 2222

`POST /login` з `username=2222` і `password=2222` — `302` на `/`.

| Метод | URL | Код | Результат |
| --- | --- | --- | --- |
| GET | http://localhost:8080/users | 200 | `User Page` |
| GET | http://localhost:8080/admin | 403 | список не віддається |

## 4. Реєстрація

| Метод | URL | Код | Результат |
| --- | --- | --- | --- |
| POST | http://localhost:8080/registration | 200 | створено користувача `newbie` |
| POST | http://localhost:8080/registration | 409 | повтор імені `newbie` відхилено |

Після `POST /login` з `username=newbie` і `password=newbie`:

| Метод | URL | Код |
| --- | --- | --- |
| GET | http://localhost:8080/teachers | 200 |
| POST | http://localhost:8080/teachers | 403 |
