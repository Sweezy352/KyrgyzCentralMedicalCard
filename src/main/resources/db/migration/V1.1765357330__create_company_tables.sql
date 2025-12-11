CREATE TABLE IF NOT EXISTS company(
    id bigserial primary key,
    company_name varchar not null unique,
    description varchar not null,
    user_id bigint references users(id),
    date_created DATE default now()
);

CREATE TABLE IF NOT EXISTS products(
    id bigserial primary key,
    product_name varchar not null,
    amount bigint default 0,
    date_created timestamp with time zone default now(),
    date_updated timestamp with time zone,
    company_id bigint references company(id)
);

