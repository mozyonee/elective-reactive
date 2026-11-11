# Лабораторна робота №2. Spring WebFlux

## Посилання

Moodle (web): https://ido-m.kneu.edu.ua/moodle/ . Курс: https://ido-m.kneu.edu.ua/moodle/course/view.php?id=5623 . Усі завдання: https://ido-m.kneu.edu.ua/moodle/mod/assign/index.php?id=5623 .

- Здача (це завдання): https://ido-m.kneu.edu.ua/moodle/mod/assign/view.php?id=251261

Ти виконуєш лише це завдання. Файли здачі клади в `execution/` поруч із цим файлом. На Moodle роботу не відправляй. Якщо крок не вийшов двічі поспіль, зупинись і опиши перешкоду.

Курс: Технології та фреймворки реактивного програмування (INIS46106U). Мета: реактивний застосунок Spring WebFlux зі зразковими маршрутами і класами предметної області «Факультатив».

Код: сам `execution/`, там лежить `pom.xml`. Здача: проєкт у `execution/` і `execution/routes.md` з тілами відповідей чотирьох зразкових URL.

Зразок викладача, лише для звірки маршрутів: https://github.com/yurijteacher/gr_403_reactive . Пакет у зразку `ua.com.reactive.gr_403_reactive`. У цьому проєкті пакет інший: `ua.com.reactive.elective`.

## Крок 1. Проєкт

Створи проєкт Spring Boot 3 у `execution/` через https://start.spring.io з Maven.

- groupId: `ua.com.reactive`
- artifactId: `elective`
- Java: версія з лабораторної 01
- залежності стартера: Spring WebFlux (`spring-boot-starter-webflux`), Freemarker (`spring-boot-starter-freemarker`), Lombok

Пакети під коренем `ua.com.reactive.elective`: `entity`, `controller`, `handler`, `router`.

## Крок 2. Зразкові сутності

У `entity` створи `Client`: поля `Long id`, `String firstName`, `String lastName`, `int age`. Анотації Lombok: `@Getter`, `@Setter`, `@NoArgsConstructor`, `@AllArgsConstructor`.

Поруч створи `Greeting`: поле `String message`. Анотації: `@Data`, `@NoArgsConstructor`, `@AllArgsConstructor`.

## Крок 3. GreetingHandler

Клас `handler.GreetingHandler`, анотація `@Component`, три методи:

1. `hello` відповідає `200` і JSON `{"message":"Hello, Spring"}`.
2. `home` відповідає `200` і текстом `Main page!`.
3. `getClients` читає query-параметр `start` (якщо немає — `"0"`), будує `Flux` із трьох клієнтів `(1, Daniel, Brooks, 18)`, `(2, Elena, Navarro, 19)`, `(3, Sofia, Berg, 20)`, пропускає `start` елементів, бере 2 і віддає JSON.

## Крок 4. GreetingRouter

Клас `router.GreetingRouter`, `@Configuration(proxyBeanMethods = false)`. Бін `RouterFunction` зв’язує:

- `GET /hello` з `Accept: application/json` → `hello`
- `GET /` → `home`
- `GET /users` → `getClients`

## Крок 5. MyController

Клас `controller.MyController`, `@RestController`. `GET /clients` повертає `Flux<Client>`: ті самі три клієнти, `skip(0)`, `take(2)`.

## Крок 6. Сутності «Факультатив»

У `entity` додай класи зі спільними полями з `README.md` у корені курсу: `Teacher`, `Course`, `Student`, `Enrollment`, `GradeRecord`. Поки без бази і без контролерів. У кожного `@Getter`, `@Setter`, `@NoArgsConstructor`, `@AllArgsConstructor`.

## Крок 7. Перевірка маршрутів

Запусти застосунок. Запиши в `execution/routes.md` код відповіді і тіло для:

- http://localhost:8080/hello
- http://localhost:8080/users
- http://localhost:8080/
- http://localhost:8080/clients

`/hello` віддає JSON привітання. `/` віддає `Main page!`. `/users` і `/clients` віддають двох клієнтів: Daniel і Elena.

## Перевірка

Проєкт збирається, чотири URL відповідають як у кроці 7, п’ять класів предметної області лежать в `entity`.
