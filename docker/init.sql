create sequence public.account_id_seq;
alter sequence public.account_id_seq owner to sa;

create sequence public.balance_id_seq;
alter sequence public.balance_id_seq owner to sa;

create sequence public.transaction_id_seq;
alter sequence public.transaction_id_seq owner to sa;

create table public.account
(
    id          bigint default nextval('account_id_seq'::regclass) not null
        constraint account_pk
            primary key,
    customer_id bigint                                               not null,
    country     varchar                                              not null
);

alter table public.account
    owner to sa;

create table public.balance
(
    id               bigint default nextval('balance_id_seq'::regclass) not null
        constraint balance_pk
            primary key,
    account_id       bigint                                               not null
        constraint balance_account_id_fk
            references public.account,
    available_amount numeric,
    currency         varchar
);

alter table public.balance
    owner to sa;

create table public.transaction
(
    id                        bigserial
        constraint transaction_pk
            primary key,
    account_id                bigint     not null
        constraint transaction_account_id_fk
            references public.account,
    amount                    numeric    not null,
    currency                  varchar    not null,
    direction                 varchar(3) not null,
    description               text       not null,
    balance_after_transaction numeric    not null
);

alter sequence public.account_id_seq owned by public.account.id;
alter sequence public.balance_id_seq owned by public.balance.id;
alter sequence public.transaction_id_seq owned by public.transaction.id;

alter table public.transaction
    owner to sa;

INSERT INTO public.account (id, customer_id, country) VALUES (DEFAULT, 118899, 'Estonia');
INSERT INTO public.account (id, customer_id, country) VALUES (DEFAULT, 223411, 'Estonia');
INSERT INTO public.account (id, customer_id, country) VALUES (DEFAULT, 324354, 'Lithuania');
INSERT INTO public.account (id, customer_id, country) VALUES (DEFAULT, 494584, 'Latvia');
INSERT INTO public.account (id, customer_id, country) VALUES (DEFAULT, 542673, 'Finland');
INSERT INTO public.account (id, customer_id, country) VALUES (DEFAULT, 693547, 'Sweden');


INSERT INTO public.balance (id, account_id, available_amount, currency) VALUES (DEFAULT, 1, 19248.86, 'EUR');
INSERT INTO public.balance (id, account_id, available_amount, currency) VALUES (DEFAULT, 1, 500.14, 'SEK');
INSERT INTO public.balance (id, account_id, available_amount, currency) VALUES (DEFAULT, 1, 2000.49, 'GBP');
INSERT INTO public.balance (id, account_id, available_amount, currency) VALUES (DEFAULT, 2, 45446.81, 'USD');
INSERT INTO public.balance (id, account_id, available_amount, currency) VALUES (DEFAULT, 2, 33573.41, 'EUR');
INSERT INTO public.balance (id, account_id, available_amount, currency) VALUES (DEFAULT, 3, 15907.13, 'SEK');
INSERT INTO public.balance (id, account_id, available_amount, currency) VALUES (DEFAULT, 4, 44460.67, 'GBP');
INSERT INTO public.balance (id, account_id, available_amount, currency) VALUES (DEFAULT, 5, 10316.09, 'EUR');
INSERT INTO public.balance (id, account_id, available_amount, currency) VALUES (DEFAULT, 5, 17718.59, 'USD');
INSERT INTO public.balance (id, account_id, available_amount, currency) VALUES (DEFAULT, 5, 44482.71, 'SEK');
INSERT INTO public.balance (id, account_id, available_amount, currency) VALUES (DEFAULT, 5, 38523.85, 'GBP');
INSERT INTO public.balance (id, account_id, available_amount, currency) VALUES (DEFAULT, 6, 2365.2, 'EUR');
INSERT INTO public.balance (id, account_id, available_amount, currency) VALUES (DEFAULT, 6, 21201.88, 'GBP');

insert into public.transaction (id, account_id, amount, currency, direction, description, balance_after_transaction) values (DEFAULT, 1, 2051.45, 'EUR', 'OUT', '19P97uvaQz1Lh9YD2mP7Uosg11r6jLFj4N', 15665.31);
insert into public.transaction (id, account_id, amount, currency, direction, description, balance_after_transaction) values (DEFAULT, 5, 4861.22, 'SEK', 'OUT', '1JhRZdLjtSWbGQoeeELrCEX11oH7JxFcqn', 42741.65);
insert into public.transaction (id, account_id, amount, currency, direction, description, balance_after_transaction) values (DEFAULT, 2, 6919.08, 'USD', 'OUT', '19TeRbvyVWAcfXBuWzadhLzdxMvWHSeVfF', 46964.93);
insert into public.transaction (id, account_id, amount, currency, direction, description, balance_after_transaction) values (DEFAULT, 5, 1741.06, 'SEK', 'IN', '1JnGoaY98A4aWAVvcKADGS2i17EQB3jjBj', 44482.71);
insert into public.transaction (id, account_id, amount, currency, direction, description, balance_after_transaction) values (DEFAULT, 6, 7529.01, 'GBP', 'IN', '1Dk9ycXaUSsqMTDZx9f1Dq8YMZSu1vxonN', 21201.88);
insert into public.transaction (id, account_id, amount, currency, direction, description, balance_after_transaction) values (DEFAULT, 5, 9660.27, 'EUR', 'OUT', '1Gxazk8sHm4tKXwxV83KFQQAgN6xKuR9Rj', 10316.09);
insert into public.transaction (id, account_id, amount, currency, direction, description, balance_after_transaction) values (DEFAULT, 2, 6098.34, 'EUR', 'OUT', '14cZhKvawFsat9Gis5KouQVB2vpvF7xMJo', 33573.41);
insert into public.transaction (id, account_id, amount, currency, direction, description, balance_after_transaction) values (DEFAULT, 3, 7479.81, 'SEK', 'IN', '1DzDC8hVgKuWryKD5p2GeRUC2cshCotaCf', 15907.13);
insert into public.transaction (id, account_id, amount, currency, direction, description, balance_after_transaction) values (DEFAULT, 2, 1518.12, 'USD', 'OUT', '1NmKZHHCXXYCcsPNedQZEmAt8FHETh9RSK', 45446.81);
insert into public.transaction (id, account_id, amount, currency, direction, description, balance_after_transaction) values (DEFAULT, 1, 3583.55, 'EUR', 'IN', '1HGy3ioNETj8bU3BufGaSiZpPBLSp8pQco', 19248.86);









