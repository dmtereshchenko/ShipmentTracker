CREATE TABLE IF NOT EXISTS chain (
    id          BIGINT                      GENERATED       BY DEFAULT AS IDENTITY NOT NULL,
    name        VARCHAR(10)                 NOT NULL,
    CONSTRAINT  uq_chains_id                UNIQUE(id)
);

CREATE TABLE IF NOT EXISTS customer (
    id          BIGINT                      PRIMARY KEY     NOT NULL,
    name        VARCHAR(255)                NOT NULL,
    chain_id    BIGINT                      NOT NULL        REFERENCES chain (id),
    CONSTRAINT  uq_customers_id             UNIQUE (id)
);

CREATE TABLE IF NOT EXISTS category (
    id          INT                         PRIMARY KEY     NOT NULL,
    name        VARCHAR(255)                NOT NUll
);

CREATE TABLE IF NOT EXISTS product (
    id          BIGINT                      PRIMARY KEY     NOT NULL,
    name        VARCHAR(255)                NOT NULL,
    category_id INT                         NOT NULL        REFERENCES category (id)
);

CREATE TABLE IF NOT EXISTS price (
    id          BIGINT                      GENERATED       BY DEFAULT AS IDENTITY NOT NULL,
    chain_id    BIGINT                      NOT NULL        REFERENCES chain (id),
    product_id  BIGINT                      NOT NULL        REFERENCES product (id),
    price       FLOAT                       NOT NULL
);

CREATE TABLE IF NOT EXISTS actual (
    id          BIGINT                      GENERATED       BY DEFAULT AS IDENTITY NOT NULL,
    date        TIMESTAMP                   NOT NULL,
    product_id  BIGINT                      NOT NULL        REFERENCES product (id),
    customer_id BIGINT                      NOT NULL        REFERENCES customer (id),
    chain_id    BIGINT                      NOT NULL        REFERENCES chain (id),
    volume      BIGINT                      NOT NULL,
    sales_value FLOAT                       NOT NULL,
    promo       BOOLEAN                     NOT NULL,
    CONSTRAINT  uq_actuals_id               UNIQUE (id)
);