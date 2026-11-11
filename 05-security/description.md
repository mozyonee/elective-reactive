# Лабораторна робота №5. Spring Security WebFlux

## Посилання

Moodle (web): https://ido-m.kneu.edu.ua/moodle/ . Курс: https://ido-m.kneu.edu.ua/moodle/course/view.php?id=5623 . Усі завдання: https://ido-m.kneu.edu.ua/moodle/mod/assign/index.php?id=5623 .

- Здача (це завдання): https://ido-m.kneu.edu.ua/moodle/mod/assign/view.php?id=251265

Ти виконуєш лише це завдання. Файли здачі клади в `execution/` поруч із цим файлом. На Moodle роботу не відправляй. Якщо крок не вийшов двічі поспіль, зупинись і опиши перешкоду.

Курс: Технології та фреймворки реактивного програмування (INIS46106U). Мета: до застосунку з лабораторної 04 підключена реактивна автентифікація. Користувач `1111` заходить на `/admin`, користувач `2222` — ні. Новий користувач реєструється сам.

Код: скопіюй вміст `../04-r2dbc/execution/` у свій `execution/` і продовж там. Зразки для звірки, пакети в них інші: https://github.com/yurijteacher/gr401_5_reactive і https://github.com/yurijteacher/gr_404_reactive . Здача: `execution/security.md` з результатами перевірок кроку 7.

Паролі в цій роботі зберігаються відкритим текстом через `NoOpPasswordEncoder`, бо зразкові користувачі в міграції записані як `1111` і `2222`. Інший кодувальник не підставляй.

## Крок 1. Залежність

Додай `org.springframework.boot:spring-boot-starter-security` і тестову `org.springframework.security:spring-security-test` зі scope `test`. Решта залежностей і `application.properties` лишаються як після лабораторної 04.

## Крок 2. Класи користувача

У `entity`:

- `User`, таблиця `users`, реалізує `UserDetails`. Поля: `@Id Long id`, `String username`, `String password`, `Set<GrantedAuthority> authorities`. Конструктор `(Long id, String username, String password)` створює порожній `HashSet` для `authorities`. `getAuthorities` повертає це поле. Решта методів `UserDetails` повертають `true`.
- `Role`, таблиця `roles`, реалізує `GrantedAuthority`. Поля: `@Id Long id`, `String name`. `getAuthority` повертає `name`.
- `RolesHasUsers`, таблиця `roles_has_users`. Поля: `@Id Long id`, `Long roleId`, `Long userId`. Колонки в базі: `role_id`, `user_id`.

## Крок 3. Репозиторії

- `RoleRepository extends ReactiveCrudRepository<Role, Long>` і `@Query("SELECT id, name FROM roles WHERE id = :id") Mono<Role> findById(Long id)`.
- `UserRepository extends ReactiveCrudRepository<User, Long>` і `@Query("SELECT id, username, password FROM users WHERE username = :username") Mono<User> findByUsername(String username)`.
- `RolesHasUsersRepository extends ReactiveCrudRepository<RolesHasUsers, Long>` і `@Query("SELECT role_id FROM roles_has_users WHERE user_id = :userId") Flux<Long> findRoleIdsByUserId(Long userId)`.

## Крок 4. UserService

`service.UserService` реалізує `ReactiveUserDetailsService`. Залежності: три репозиторії з кроку 3.

`getAuthorities(Long userId)` бере id ролей користувача, завантажує `Role` і приводить їх до `GrantedAuthority`.

`findByUsername` знаходить користувача, збирає його ролі в `HashSet` і повертає цей `User` як `UserDetails`.

## Крок 5. Міграція

Скрипти Flyway предметної області не перенось. Новий файл `src/main/resources/db/migration/V3__security.sql`:

```sql
create table users (
    id bigint generated always as identity primary key,
    username varchar(100),
    password varchar(100)
);
create table roles (
    id bigint generated always as identity primary key,
    name varchar(100)
);
create table roles_has_users (
    id bigint generated always as identity primary key,
    user_id bigint references users(id),
    role_id bigint references roles(id)
);
insert into users (username, password) values ('1111', '1111');
insert into users (username, password) values ('2222', '2222');
insert into roles (name) values ('ROLE_User');
insert into roles (name) values ('ROLE_Admin');
insert into roles_has_users (user_id, role_id) values (1, 1);
insert into roles_has_users (user_id, role_id) values (1, 2);
insert into roles_has_users (user_id, role_id) values (2, 1);
```

Користувач `1111` має ролі `ROLE_User` і `ROLE_Admin`. Користувач `2222` має лише `ROLE_User`.

## Крок 6. WebSecurityConfig

Клас конфігурації з `@EnableWebFluxSecurity` і `@EnableReactiveMethodSecurity`.

- Бін `PasswordEncoder` повертає `NoOpPasswordEncoder.getInstance()`.
- Бін `ReactiveAuthenticationManager` — це `UserDetailsRepositoryReactiveAuthenticationManager` на `UserService` з цим кодувальником.
- Ланцюжок: CSRF вимкнений. `GET /`, `POST /registration`, `GET /hello`, `GET /clients` відкриті. `GET /admin` вимагає роль `Admin`. `GET /users` вимагає роль `User`. Запис у `/teachers`, `/courses`, `/students`, `/enrollments`, `/grades` (`POST`, `PUT`, `DELETE`) вимагає роль `Admin`. Читання цих префіксів вимагає автентифікації. Решта обмінів вимагає автентифікації.
- Увімкни `formLogin` зі стандартними параметрами і підстав менеджер автентифікації з біна.

Оновлюй `GreetingHandler` і `GreetingRouter` так:

- `GET /` лишається відповіддю `Main page!` з лабораторної 02.
- `GET /hello` і `GET /clients` лишаються з лабораторної 02.
- `GET /users` тепер віддає JSON-текст `User Page`. Список клієнтів лишається на `GET /clients`.
- `GET /admin` віддає JSON-масив із трьох користувачів `(1, Daniel, 1234)`, `(2, Elena, 1234)`, `(3, Sofia, 1234)`.

`POST /registration` приймає JSON `{"username","password"}`, зберігає користувача через `UserRepository` і додає йому роль `ROLE_User`. Якщо ім’я вже є, відповідь `409`.

## Крок 7. Перевірка

Запиши в `execution/security.md`:

1. Без сесії `GET /admin` не віддає список. `GET /hello` віддає привітання.
2. Форма входу `POST /login` з `username=1111` і `password=1111`. Далі `GET /admin` з цією сесією віддає трьох користувачів, `GET /users` віддає `User Page`.
3. Окремо сесія `2222` / `2222`: `GET /users` успішний, `GET /admin` заборонений.
4. `POST /registration` з новим іменем створює користувача. Повтор того самого імені дає `409`. Новий користувач входить і читає `GET /teachers`, а `POST /teachers` для нього заборонений.

## Перевірка

Чотири пункти кроку 7 записані з кодами відповідей. Міграція `V3` у `flyway_schema_history` має статус success. Маршрути лабораторної 02 і CRUD лабораторної 04 на місці.
