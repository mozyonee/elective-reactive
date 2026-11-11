# Факультатив — реактивний стек (INIS46106U)

Один монорепозиторій. Теги `lab-01` … `lab-07` — **накопичувальний** стан після кожної лабораторної (не окремі папки лаб).

| Етап | Вміст |
| ---- | ----- |
| `lab-01` | `environment/` — звіт і скріни середовища |
| `lab-02` | + `server/` — Spring WebFlux, `server/routes.md` |
| `lab-03` | + Flyway, `server/tables.md` |
| `lab-04` | + R2DBC/CRUD, `server/postman.md`, Postman-колекція |
| `lab-05` | + Security, `server/security.md` |
| `lab-06` | + `client/` — Vue 3, `client/check.md`, скріни UI |
| `lab-07` | + `control.md` (контрольна) |

PostgreSQL: база `elective`, `postgres` / `postgres`, порт `5432`.

## Запуск (теги `lab-05` … `lab-07`)

```bash
cd server && mvn spring-boot:run          # :8080
cd client && npm install && npm run serve # :8081
```

Клієнт: логін `1111` / `1111`.

## Порівняння між лабами

| Перехід | Compare |
| ------- | ------- |
| 1 → 2 | [lab-01...lab-02](https://github.com/mozyonee/inis46106u-reactive-elective/compare/lab-01...lab-02) |
| 2 → 3 | [lab-02...lab-03](https://github.com/mozyonee/inis46106u-reactive-elective/compare/lab-02...lab-03) |
| 3 → 4 | [lab-03...lab-04](https://github.com/mozyonee/inis46106u-reactive-elective/compare/lab-03...lab-04) |
| 4 → 5 | [lab-04...lab-05](https://github.com/mozyonee/inis46106u-reactive-elective/compare/lab-04...lab-05) |
| 5 → 6 | [lab-05...lab-06](https://github.com/mozyonee/inis46106u-reactive-elective/compare/lab-05...lab-06) |
| 6 → 7 | [lab-06...lab-07](https://github.com/mozyonee/inis46106u-reactive-elective/compare/lab-06...lab-07) |
| 1 → 7 | [lab-01...lab-07](https://github.com/mozyonee/inis46106u-reactive-elective/compare/lab-01...lab-07) |

Перегляд на тезі: `https://github.com/mozyonee/inis46106u-reactive-elective/tree/lab-03`
