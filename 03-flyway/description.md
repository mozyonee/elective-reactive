# Лабораторна робота №3. Структура бази і Flyway

## Посилання

Moodle (web): https://ido-m.kneu.edu.ua/moodle/ . Курс: https://ido-m.kneu.edu.ua/moodle/course/view.php?id=5623 . Усі завдання: https://ido-m.kneu.edu.ua/moodle/mod/assign/index.php?id=5623 .

- Здача (це завдання): https://ido-m.kneu.edu.ua/moodle/mod/assign/view.php?id=251262

Ти виконуєш лише це завдання. Файли здачі клади в `execution/` поруч із цим файлом. На Moodle роботу не відправляй. Якщо крок не вийшов двічі поспіль, зупинись і опиши перешкоду.

Курс: Технології та фреймворки реактивного програмування (INIS46106U). Мета: до застосунку з лабораторної 02 підключені R2DBC, PostgreSQL і Flyway, міграції створюють зразкову таблицю `message` і таблиці системи «Факультатив».

Код: скопіюй вміст `../02-spring-webflux/execution/` у свій `execution/` і продовж там. База `elective`, користувач `postgres`, пароль `postgres`, порт 5432. Здача: міграції в `execution/src/main/resources/db/migration/` і `execution/tables.md` зі списком таблиць після міграції.

## Крок 1. Залежності

Додай у `pom.xml` до вже наявних WebFlux, Freemarker і Lombok:

- `org.springframework.boot:spring-boot-starter-data-r2dbc`
- `org.springframework:spring-jdbc`
- `org.flywaydb:flyway-core`
- `org.flywaydb:flyway-database-postgresql`
- `org.postgresql:postgresql` зі scope `runtime`
- `org.postgresql:r2dbc-postgresql` зі scope `runtime`
- `org.springframework.boot:spring-boot-starter-test` зі scope `test`
- `io.projectreactor:reactor-test` зі scope `test`

Lombok лишається `optional`.

## Крок 2. Підключення

У `src/main/resources/application.properties`:

```
spring.freemarker.suffix=.ftl
spring.r2dbc.url=r2dbc:postgresql://localhost:5432/elective
spring.r2dbc.username=postgres
spring.r2dbc.password=postgres
spring.flyway.url=jdbc:postgresql://localhost:5432/elective
spring.flyway.username=postgres
spring.flyway.password=postgres
```

## Крок 3. V1

Файл `src/main/resources/db/migration/V1__Initial_db.sql`:

```sql
create table message (
    id bigserial primary key,
    data varchar(1024)
);
```

## Крок 4. V2

Файл `src/main/resources/db/migration/V2__Initial_db.sql` створює таблиці предметної області. Імена і зв’язки:

- `teachers(id bigint generated always as identity primary key, first_name varchar(100) not null, last_name varchar(100) not null)`
- `courses(id bigint generated always as identity primary key, title varchar(200) not null, teacher_id bigint not null references teachers(id))`
- `students(id bigint generated always as identity primary key, first_name varchar(100) not null, last_name varchar(100) not null)`
- `enrollments(id bigint generated always as identity primary key, student_id bigint not null references students(id), course_id bigint not null references courses(id))`
- `grade_records(id bigint generated always as identity primary key, enrollment_id bigint not null references enrollments(id), grade integer not null)`

## Крок 5. Прогін

Запусти застосунок так, щоб Flyway виконав обидві міграції. У `execution/tables.md` запиши результат `\dt` для бази `elective`. Мають бути `message`, `teachers`, `courses`, `students`, `enrollments`, `grade_records` і `flyway_schema_history`.

## Перевірка

Повторний запуск не зривається на вже застосованих міграціях. `courses.teacher_id` посилається на `teachers`, `enrollments` посилається на `students` і `courses`, `grade_records.enrollment_id` посилається на `enrollments`.
