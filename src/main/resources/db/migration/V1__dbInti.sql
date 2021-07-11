create
    sequence hibernate_sequence start
    with 1 increment by 1;

CREATE TABLE users
(
    id       serial,
    name     VARCHAR NOT NULL,
    username VARCHAR NOT NULL,
    password VARCHAR NOT NULL,
    type     int4    NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE products
(
    id    serial,
    name  VARCHAR NOT NULL,
    price float4  NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE tables
(
    id        serial,
    open      bool NOT NULL default 'true',
    table_number int,
    waiter_id int,
    PRIMARY KEY (id),
    CONSTRAINT tables_user_fk_1 FOREIGN KEY (waiter_id) REFERENCES users (id)
);


CREATE TABLE orders
(
    id       serial,
    status   int NOT NULL DEFAULT '1',
    table_id int NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT orders_tables_fk_1 FOREIGN KEY (table_id) REFERENCES tables (id)
);

CREATE TABLE product_in_order
(
    id         serial,
    amount     int NOT NULL DEFAULT '1',
    status     int NOT NULL DEFAULT '1',
    order_id   int NOT NULL,
    product_id int NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT product_in_order_orders_fk_1 FOREIGN KEY (order_id) REFERENCES orders (id),
    CONSTRAINT product_in_order_products_fk_2 FOREIGN KEY (product_id) REFERENCES products (id)
);
