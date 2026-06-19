ALTER TABLE receipts ADD COLUMN number varchar unique not null;

CREATE TABLE allergies(
    id bigserial primary key,
    name varchar not null,
    description varchar not null,
    date_created timestamp with time zone default now(),
    date_updated timestamp with time zone,
    status boolean default true,
    user_id bigint references users(id),
    doctor_id bigint references users(id)
);