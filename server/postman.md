# Запити Postman, лабораторна 4

Колекція `elective.postman_collection.json` виконана проти http://localhost:8080 у порядку з завдання.

| Метод | URL | Код |
| --- | --- | --- |
| POST | http://localhost:8080/controller | 200 |
| GET | http://localhost:8080/controller | 200 |
| POST | http://localhost:8080/teachers | 201 |
| POST | http://localhost:8080/courses | 201 |
| POST | http://localhost:8080/students | 201 |
| POST | http://localhost:8080/enrollments | 201 |
| POST | http://localhost:8080/grades | 201 |
| GET | http://localhost:8080/teachers | 200 |
| GET | http://localhost:8080/courses | 200 |
| GET | http://localhost:8080/students | 200 |
| GET | http://localhost:8080/enrollments | 200 |
| GET | http://localhost:8080/grades | 200 |
| PUT | http://localhost:8080/grades/1 | 200 |
| DELETE | http://localhost:8080/grades/1 | 204 |
| DELETE | http://localhost:8080/enrollments/1 | 204 |
| DELETE | http://localhost:8080/students/1 | 204 |
| DELETE | http://localhost:8080/courses/1 | 204 |
| DELETE | http://localhost:8080/teachers/1 | 204 |

`POST /controller` тіло `{"data":"hello"}`. `GET /controller` містить цей запис. Викладач `Olena Kravets`, курс `WebFlux`, студент `Vadym Abakumov`, реєстрація і оцінка `90`, після `PUT` оцінка `95`.

Відсутній id: `GET /teachers/999999` — `404`.

Після видалення у зворотному порядку списки порожні: `GET` кожного префікса повертає `200` і `[]`.

Маршрути лабораторної 2 на місці: `GET /hello` — `200`, `GET /` — `200`, `GET /users` — `200`, `GET /clients` — `200`.
