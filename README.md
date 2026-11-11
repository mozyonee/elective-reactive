# Факультатив (INIS46106U)

Лабораторні роботи: система обліку факультативів (Spring WebFlux, R2DBC, PostgreSQL, Vue 3).

PostgreSQL: `elective`, користувач і пароль `postgres`, порт `5432`.

| Номер | Завдання |
| ----- | -------- |
| [1](https://github.com/mozyonee/elective-reactive/compare/lab-00...lab-01) | `report.md`, `screenshots/` |
| [2](https://github.com/mozyonee/elective-reactive/compare/lab-01...lab-02) | `server/`, `routes.md` (WebFlux) |
| [3](https://github.com/mozyonee/elective-reactive/compare/lab-02...lab-03) | Flyway, `tables.md` |
| [4](https://github.com/mozyonee/elective-reactive/compare/lab-03...lab-04) | R2DBC, `postman.md`, колекція Postman |
| [5](https://github.com/mozyonee/elective-reactive/compare/lab-04...lab-05) | Security, `security.md` |
| [6](https://github.com/mozyonee/elective-reactive/compare/lab-05...lab-06) | `client/`, `check.md` (Vue) |
| [7](https://github.com/mozyonee/elective-reactive/compare/lab-06...lab-07) | `control.md` |

## Запуск

```bash
cd server && mvn spring-boot:run          # :8080
cd client && npm install && npm run serve # :8081
```

Вхід у клієнті: `1111` / `1111`.
