# Маршрути лабораторної 2

Застосунок зібраний і запущений на http://localhost:8080.

## GET http://localhost:8080/hello

Код: `200`

Тіло:

```json
{"message":"Hello, Spring"}
```

## GET http://localhost:8080/users

Код: `200`

Тіло:

```json
[{"id":1,"firstName":"Daniel","lastName":"Brooks","age":18},{"id":2,"firstName":"Elena","lastName":"Navarro","age":19}]
```

## GET http://localhost:8080/

Код: `200`

Тіло:

```
Main page!
```

## GET http://localhost:8080/clients

Код: `200`

Тіло:

```json
[{"id":1,"firstName":"Daniel","lastName":"Brooks","age":18},{"id":2,"firstName":"Elena","lastName":"Navarro","age":19}]
```
