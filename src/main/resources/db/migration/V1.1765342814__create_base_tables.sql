CREATE TABLE IF NOT EXISTS users(
    id bigserial primary key,
    inn varchar unique not null,
    fio varchar not null,
    password varchar not null,
    gender varchar,
    birthday DATE,
    en varchar,
    en_name varchar,
    blood varchar,
    rh varchar
);

CREATE TABLE IF NOT EXISTS company(
                                      id bigserial primary key,
                                      company_name varchar not null unique,
                                      description varchar not null,
                                      user_id bigint references users(id),
                                      date_created DATE default now()

);

ALTER TABLE users ADD COLUMN company_id bigint references company(id);

CREATE TABLE IF NOT EXISTS roles(
    id bigserial primary key,
    role_name varchar not null unique
);

INSERT INTO roles(role_name)
VALUES ('ADMIN'),
       ('USER'),
       ('DOCTOR'),
       ('NURSE'),
       ('PHARMACIST');

CREATE TABLE IF NOT EXISTS m2m_users_roles(
    user_id bigint references users(id),
    role_id bigint references roles(id)
);

CREATE TABLE IF NOT EXISTS news(
    id bigserial primary key,
    name varchar not null,
    description varchar not null,
    date_created DATE default now(),
    user_id bigint references users(id)
);

CREATE TABLE IF NOT EXISTS news_picture_files(
    id bigserial primary key,
    original_file_name varchar not null unique,
    mime_type varchar not null,
    news_id bigint references news(id)
);

CREATE TABLE IF NOT EXISTS histories(
    id bigserial primary key,
    name varchar not null,
    description varchar not null,
    user_id bigint references users(id),
    user_doc bigint references users(id),
    date_created DATE default now(),
    date_updated DATE
);

ALTER TABLE histories ADD COLUMN company_id bigint references company(id);

CREATE TABLE IF NOT EXISTS diagnoses(
    id bigserial primary key,
    name varchar not null,
    description varchar not null,
    user_id bigint references users(id),
    user_doc bigint references users(id),
    date_created DATE default now(),
    date_updated DATE default now(),
    status varchar default 'ACTUAL'
);

CREATE TABLE IF NOT EXISTS pdf_file_diagnoses(
    id bigserial primary key,
    original_file_name varchar not null unique,
    mime_type varchar not null,
    path varchar not null,
    diagnosis_id bigint references diagnoses(id)
);

CREATE TABLE IF NOT EXISTS medicine_insurances(
    id bigserial primary key,
    insurance_name varchar not null,
    description jsonb,
    price DECIMAL(19, 2) not null
);

CREATE TABLE IF NOT EXISTS user_insurances(
    id bigserial primary key,
    medicine_insurance bigint references medicine_insurances(id),
    user_id bigint references users(id),
    date_active DATE default now(),
    date_expire DATE default now() + interval '1 month'
);
