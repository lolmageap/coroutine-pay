CREATE TABLE IF NOT EXISTS product (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    price BIGINT NOT NULL
)

CREATE TABLE IF NOT EXISTS "order" (
    id SERIAL PRIMARY KEY,
    userId BIGINT NOT NULL,
    description VARCHAR(255),
    amount BIGINT DEFAULT 0,
    pgOrderId VARCHAR(255),
    pgKey VARCHAR(255),
    pgStatus VARCHAR(50) DEFAULT 'CREATE',
    pgRetryCount INTEGER DEFAULT 0
);

CREATE TABLE IF NOT EXISTS "product_in_order" (
   orderId BIGINT NOT NULL,
   productId BIGINT NOT NULL,
   price BIGINT NOT NULL,
   quantity INTEGER NOT NULL,
   seq BIGINT PRIMARY KEY
);