# Таблиці після міграції

`psql` до бази `elective`, команда `\dt`:

```
                  List of tables
 Schema |         Name          | Type  |  Owner   
--------+-----------------------+-------+----------
 public | courses               | table | postgres
 public | enrollments           | table | postgres
 public | flyway_schema_history | table | postgres
 public | grade_records         | table | postgres
 public | message               | table | postgres
 public | students              | table | postgres
 public | teachers              | table | postgres
(7 rows)
```

Flyway застосував `V1__Initial_db.sql` і `V2__Initial_db.sql`. Повторний запуск не виконує їх знову.

`courses.teacher_id` посилається на `teachers(id)`. `enrollments.student_id` посилається на `students(id)`, `enrollments.course_id` — на `courses(id)`. `grade_records.enrollment_id` посилається на `enrollments(id)`.
