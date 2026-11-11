# Факультатив (INIS46106U)

Облік факультативів: Spring WebFlux, R2DBC, PostgreSQL, Vue 3.

Теги `lab-01` … `lab-07` — код після відповідної лабораторної.

| Тег | Зміст |
| --- | ----- |
| `lab-01` | `environment/` — звіт і скріни |
| `lab-02` | `server/` — WebFlux, `routes.md` |
| `lab-03` | Flyway, `tables.md` |
| `lab-04` | R2DBC, `postman.md`, колекція Postman |
| `lab-05` | Security, `security.md` |
| `lab-06` | `client/` — Vue, `check.md` |
| `lab-07` | `control.md` |

PostgreSQL: `elective`, користувач і пароль `postgres`, порт `5432`.

## Запуск

```bash
cd server && mvn spring-boot:run          # :8080
cd client && npm install && npm run serve # :8081
```

Вхід у клієнті: `1111` / `1111`.

## Зміни між лабами

| Перехід | Compare |
| ------- | ------- |
| 1 → 2 | [lab-01...lab-02](https://github.com/mozyonee/elective-reactive/compare/lab-01...lab-02) |
| 2 → 3 | [lab-02...lab-03](https://github.com/mozyonee/elective-reactive/compare/lab-02...lab-03) |
| 3 → 4 | [lab-03...lab-04](https://github.com/mozyonee/elective-reactive/compare/lab-03...lab-04) |
| 4 → 5 | [lab-04...lab-05](https://github.com/mozyonee/elective-reactive/compare/lab-04...lab-05) |
| 5 → 6 | [lab-05...lab-06](https://github.com/mozyonee/elective-reactive/compare/lab-05...lab-06) |
| 6 → 7 | [lab-06...lab-07](https://github.com/mozyonee/elective-reactive/compare/lab-06...lab-07) |
| 1 → 7 | [lab-01...lab-07](https://github.com/mozyonee/elective-reactive/compare/lab-01...lab-07) |
