# Факультатив (INIS46106U)

Облік факультативів: Spring WebFlux, R2DBC, PostgreSQL, Vue 3.

PostgreSQL: `elective`, користувач і пароль `postgres`, порт `5432`.

| Перехід | Що додається | Compare |
| ------- | ------------ | ------- |
| 0 → 1 | `report.md`, `screenshots/` | [lab-00...lab-01](https://github.com/mozyonee/elective-reactive/compare/lab-00...lab-01) |
| 1 → 2 | `server/`, `routes.md` | [lab-01...lab-02](https://github.com/mozyonee/elective-reactive/compare/lab-01...lab-02) |
| 2 → 3 | Flyway, `tables.md` | [lab-02...lab-03](https://github.com/mozyonee/elective-reactive/compare/lab-02...lab-03) |
| 3 → 4 | R2DBC, `postman.md`, Postman-колекція | [lab-03...lab-04](https://github.com/mozyonee/elective-reactive/compare/lab-03...lab-04) |
| 4 → 5 | Security, `security.md` | [lab-04...lab-05](https://github.com/mozyonee/elective-reactive/compare/lab-04...lab-05) |
| 5 → 6 | `client/`, `check.md` | [lab-05...lab-06](https://github.com/mozyonee/elective-reactive/compare/lab-05...lab-06) |
| 6 → 7 | `control.md` | [lab-06...lab-07](https://github.com/mozyonee/elective-reactive/compare/lab-06...lab-07) |

## Запуск

```bash
cd server && mvn spring-boot:run          # :8080
cd client && npm install && npm run serve # :8081
```

Вхід у клієнті: `1111` / `1111`.
