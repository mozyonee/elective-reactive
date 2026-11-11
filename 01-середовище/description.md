# Лабораторна робота №1. Середовище розробки

## Посилання

Moodle (web): https://ido-m.kneu.edu.ua/moodle/ . Курс: https://ido-m.kneu.edu.ua/moodle/course/view.php?id=5623 . Усі завдання: https://ido-m.kneu.edu.ua/moodle/mod/assign/index.php?id=5623 .

- Здача (це завдання): https://ido-m.kneu.edu.ua/moodle/mod/assign/view.php?id=251260

Ти виконуєш лише це завдання. Файли здачі клади в `execution/` поруч із цим файлом. На Moodle роботу не відправляй. Якщо крок не вийшов двічі поспіль, зупинись і опиши перешкоду.

Курс: Технології та фреймворки реактивного програмування (INIS46106U). Мета: на комп’ютері студента стоять інструменти для реактивного застосунку, і для кожного є доказ встановлення.

Здача: `execution/report.md`. Командний вивід впиши текстом. Знімки не роби: лишай `![опис](screenshots/ім'я.png)`. Студент підставить файли і сам конвертує Markdown.

## Крок 1. JDK

JDK уже встановлено. Перевір `java -version`: має бути 17 або новіший. Якщо версія нижча, постав новіший з https://www.oracle.com/java/technologies/downloads/ .

У `report.md` запиши вивід `java -version` і плейсхолдер `![термінал java -version](screenshots/jdk.png)`.

## Крок 2. PostgreSQL і pgAdmin

PostgreSQL уже встановлено. Переконайся, що сервер запущений на порту `5432` з користувачем `postgres` і паролем `postgres`. Створи порожню базу `elective` (через `createdb elective` або pgAdmin).

pgAdmin 4, якщо його ще немає: https://www.pgadmin.org/download/ .

У `report.md` запиши вивід `psql --version`. Плейсхолдери: `![сервер PostgreSQL запущений](screenshots/postgres.png)` і `![pgAdmin, база elective](screenshots/pgadmin.png)`.

## Крок 3. IntelliJ IDEA

Не встановлюй. У `report.md` напиши, що середовище розробки — Cursor і VS Code.

## Крок 4. Git

Постав Git: https://git-scm.com/book/uk/v2 . У `report.md` запиши вивід `git --version` і плейсхолдер `![git --version](screenshots/git.png)`.

## Крок 5. GitHub

Обліковий запис GitHub уже є. Репозиторій не створюй і нічого не пуш. Плейсхолдер: `![профіль GitHub](screenshots/github.png)`.

## Крок 6. Visual Studio Code

VS Code уже встановлено. У `report.md` запиши вивід `code --version` і плейсхолдер `![VS Code](screenshots/vscode.png)`.

## Крок 7. Node.js

Постав поточний LTS Node.js: https://nodejs.org/uk/download . У `report.md` запиши вивід `node --version` і `npm --version` і плейсхолдер `![node --version](screenshots/node.png)`.

## Крок 8. Postman

Постав Postman: https://www.postman.com/downloads/ . Плейсхолдер: `![вікно Postman](screenshots/postman.png)`.

## Перевірка

`report.md` містить версії Java, psql, Git, VS Code і Node і вісім плейсхолдерів: `jdk`, `postgres`, `pgadmin`, `git`, `github`, `vscode`, `node`, `postman`. Файлів зображень агент не створює. База `elective` існує і приймає підключення користувача `postgres`.
