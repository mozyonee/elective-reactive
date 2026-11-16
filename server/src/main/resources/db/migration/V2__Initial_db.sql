create table teachers (
    id bigint generated always as identity primary key,
    first_name varchar(100) not null,
    last_name varchar(100) not null
);

create table courses (
    id bigint generated always as identity primary key,
    title varchar(200) not null,
    teacher_id bigint not null references teachers(id)
);

create table students (
    id bigint generated always as identity primary key,
    first_name varchar(100) not null,
    last_name varchar(100) not null
);

create table enrollments (
    id bigint generated always as identity primary key,
    student_id bigint not null references students(id),
    course_id bigint not null references courses(id)
);

create table grade_records (
    id bigint generated always as identity primary key,
    enrollment_id bigint not null references enrollments(id),
    grade integer not null
);
