# Технології та фреймворки реактивного програмування (INIS46106U)

Кожна папка — одне завдання. `description.md` — порядок виконання. `execution/` — здача. На Moodle роботу не відправляти.

Зведена копія коду й звітів: [main/](main/README.md) (`server/`, `client/`, `docs/`).

Предметна область лабораторних 2–6 і контрольної: система «Факультатив». База PostgreSQL `elective`, користувач `postgres`, пароль `postgres`, порт `5432`.

## Завдання

- [01 Середовище розробки](01-середовище/description.md)
- [02 Spring WebFlux](02-spring-webflux/description.md)
- [03 Структура БД і Flyway](03-flyway/description.md)
- [04 R2DBC і CRUD](04-r2dbc/description.md)
- [05 Spring Security](05-security/description.md)
- [06 Клієнт Vue](06-vue/description.md)
- [07 Підсумкова контрольна, варіант 1](07-контрольна/description.md)

## Версії за лабами (git-теги)

Репозиторій позначено тегами `lab-01` … `lab-07`. Кожен тег — стан після відповідної лабораторної. Перегляд коду на тезі: `https://github.com/mozyonee/inis46106u-reactive-elective/tree/lab-03`

Що додалось між етапами (порівняння diff):

| Перехід | GitHub Compare |
| ------- | -------------- |
| лаб 1 → 2 | [lab-01...lab-02](https://github.com/mozyonee/inis46106u-reactive-elective/compare/lab-01...lab-02) |
| лаб 2 → 3 | [lab-02...lab-03](https://github.com/mozyonee/inis46106u-reactive-elective/compare/lab-02...lab-03) |
| лаб 3 → 4 | [lab-03...lab-04](https://github.com/mozyonee/inis46106u-reactive-elective/compare/lab-03...lab-04) |
| лаб 4 → 5 | [lab-04...lab-05](https://github.com/mozyonee/inis46106u-reactive-elective/compare/lab-04...lab-05) |
| лаб 5 → 6 | [lab-05...lab-06](https://github.com/mozyonee/inis46106u-reactive-elective/compare/lab-05...lab-06) |
| лаб 6 → 7 | [lab-06...lab-07](https://github.com/mozyonee/inis46106u-reactive-elective/compare/lab-06...lab-07) |
| лаб 1 → 7 (повністю) | [lab-01...lab-07](https://github.com/mozyonee/inis46106u-reactive-elective/compare/lab-01...lab-07) |

## Сутності

- `Teacher`: id, firstName, lastName. Таблиця `teachers`.
- `Course`: id, title, teacherId. Таблиця `courses`.
- `Student`: id, firstName, lastName. Таблиця `students`.
- `Enrollment`: id, studentId, courseId. Таблиця `enrollments`.
- `GradeRecord`: id, enrollmentId, grade (ціле 0–100). Таблиця `grade_records`.

Класи `Client`, `Greeting` і `Message` зі зразків лабораторних лишаються в проєкті поруч із цими сутностями.
