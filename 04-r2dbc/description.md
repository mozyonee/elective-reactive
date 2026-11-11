# Лабораторна робота №4. R2DBC і CRUD

## Посилання

Moodle (web): https://ido-m.kneu.edu.ua/moodle/ . Курс: https://ido-m.kneu.edu.ua/moodle/course/view.php?id=5623 . Усі завдання: https://ido-m.kneu.edu.ua/moodle/mod/assign/index.php?id=5623 .

- Здача (це завдання): https://ido-m.kneu.edu.ua/moodle/mod/assign/view.php?id=251264

Ти виконуєш лише це завдання. Файли здачі клади в `execution/` поруч із цим файлом. На Moodle роботу не відправляй. Якщо крок не вийшов двічі поспіль, зупинись і опиши перешкоду.

Курс: Технології та фреймворки реактивного програмування (INIS46106U). Мета: той самий застосунок читає і пише PostgreSQL через R2DBC. Зразкова сутність `Message` і п’ять сутностей «Факультатив» мають CRUD.

Код: скопіюй вміст `../03-flyway/execution/` у свій `execution/` і продовж там. Зразок для звірки, пакет у ньому інший: https://github.com/yurijteacher/gr_404_reactive . Здача: колекція Postman `execution/elective.postman_collection.json` і `execution/postman.md` з кодом відповіді кожного запиту.

## Крок 1. Message

Залежності і `application.properties` уже задані лабораторною 03. Таблиця `message` уже створена міграцією `V1__Initial_db.sql`.

У `entity` додай `Message`: `@Data`, `@NoArgsConstructor`, поле `@Id Long id`, поле `String data`, конструктор `Message(String data)`.

Пакет `repository`: `MessageRepository extends ReactiveCrudRepository<Message, Long>`.

Пакет `service`: `MessageService` з конструктором, який приймає `MessageRepository`. Методи: `Flux<Message> list()` через `findAll`, `Mono<Message> addOne(Message message)` через `save`.

`controller.MainController`: `@RestController`, `@RequestMapping("/controller")`. `GET /controller` повертає `messageService.list()`. `POST /controller` приймає `@RequestBody Message` і повертає `messageService.addOne`.

## Крок 2. Репозиторії предметної області

На класи `Teacher`, `Course`, `Student`, `Enrollment`, `GradeRecord` додай `@Id` на поле `id` і анотацію Spring Data `@Table` з іменем таблиці. Поля зовнішніх ключів назви так, щоб R2DBC зіставив їх із колонками `teacher_id`, `student_id`, `course_id`, `enrollment_id`.

Для кожного класу створи інтерфейс `ReactiveCrudRepository` у пакеті `repository`.

## Крок 3. CRUD

Для кожної з п’яти сутностей зроби сервіс і REST-контролер. Префікси: `/teachers`, `/courses`, `/students`, `/enrollments`, `/grades`.

На кожному префіксі:

- `GET` без id — список
- `GET /{id}` — один запис, `404`, якщо немає
- `POST` — створення, id призначає база
- `PUT /{id}` — заміна полів, `404`, якщо немає
- `DELETE /{id}` — видалення, `404`, якщо немає

`POST /courses` відхиляє запит, якщо `teacherId` немає в `teachers`. `POST /enrollments` відхиляє запит, якщо немає студента або курсу. `POST /grades` відхиляє запит, якщо немає реєстрації або оцінка поза 0–100.

## Крок 4. Postman

Імпортуй у Postman і виконай у такому порядку:

1. `POST /controller` з тілом `{"data":"hello"}`.
2. `GET /controller` — у списку є цей запис.
3. `POST /teachers` з `firstName=Olena`, `lastName=Kravets`.
4. `POST /courses` з `title=WebFlux` і `teacherId` створеного викладача.
5. `POST /students` з `firstName=Vadym`, `lastName=Abakumov`.
6. `POST /enrollments` з id цього студента і цього курсу.
7. `POST /grades` з id реєстрації і `grade=90`.
8. `GET` списку для кожного префікса.
9. `PUT /grades/{id}` змінює оцінку на `95`.
10. `DELETE` створених записів у зворотному порядку: оцінка, реєстрація, студент, курс, викладач.

Колекцію збережи в `execution/elective.postman_collection.json`. У `execution/postman.md` для кожного запиту запиши метод, URL і код відповіді.

## Перевірка

Усі десять груп запитів з кроку 4 повертають очікуваний код: створення `201` або `200`, читання `200`, відсутній id — `404`. Після видалення списки зразкових рядків порожні. Зразкові маршрути лабораторної 02 і далі відповідають.
