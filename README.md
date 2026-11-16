# Здачі курсу

PostgreSQL: `elective`, користувач і пароль `postgres`, порт `5432`.

Основна гілка: [`control`](https://github.com/mozyonee/elective-reactive/tree/control).

| Номер | Гілка | Pull request | Завдання |
| ----- | ----- | ------------ | -------- |
| Лабораторна 1 | [`lab-01`](https://github.com/mozyonee/elective-reactive/tree/lab-01) | [#1](https://github.com/mozyonee/elective-reactive/pull/1) (`lab-00` → `lab-01`) | `report.md`, `screenshots/` |
| Лабораторна 2 | [`lab-02`](https://github.com/mozyonee/elective-reactive/tree/lab-02) | [#2](https://github.com/mozyonee/elective-reactive/pull/2) (`lab-01` → `lab-02`) | `server/`, `routes.md` (WebFlux) |
| Лабораторна 3 | [`lab-03`](https://github.com/mozyonee/elective-reactive/tree/lab-03) | [#3](https://github.com/mozyonee/elective-reactive/pull/3) (`lab-02` → `lab-03`) | Flyway, `tables.md` |
| Лабораторна 4 | [`lab-04`](https://github.com/mozyonee/elective-reactive/tree/lab-04) | [#4](https://github.com/mozyonee/elective-reactive/pull/4) (`lab-03` → `lab-04`) | R2DBC, `postman.md`, колекція Postman |
| Лабораторна 5 | [`lab-05`](https://github.com/mozyonee/elective-reactive/tree/lab-05) | [#5](https://github.com/mozyonee/elective-reactive/pull/5) (`lab-04` → `lab-05`) | Security, `security.md` |
| Лабораторна 6 | [`lab-06`](https://github.com/mozyonee/elective-reactive/tree/lab-06) | [#6](https://github.com/mozyonee/elective-reactive/pull/6) (`lab-05` → `lab-06`) | `client/`, `check.md` (Vue) |
| Контрольна | [`control`](https://github.com/mozyonee/elective-reactive/tree/control) | [#7](https://github.com/mozyonee/elective-reactive/pull/7) (`lab-06` → `control`) | `control.md` |

Стартова гілка: [`lab-00`](https://github.com/mozyonee/elective-reactive/tree/lab-00).

## Запуск

```bash
cd server && mvn spring-boot:run          # :8080
cd client && npm install && npm run serve # :8081
```

Вхід у клієнті: `1111` / `1111`.
