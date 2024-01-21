CREATE TABLE IF NOT EXISTS product (
    id SERIAL PRIMARY KEY,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    name VARCHAR(255) NOT NULL,
    price BIGINT NOT NULL
)

CREATE TABLE IF NOT EXISTS "order" (
    id SERIAL PRIMARY KEY,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    user_id BIGINT NOT NULL,
    description VARCHAR(255),
    amount BIGINT DEFAULT 0,
    pg_order_id VARCHAR(255),
    pg_key VARCHAR(255),
    pg_status VARCHAR(50) DEFAULT 'CREATE',
    pg_retry_count INTEGER DEFAULT 0
);

CREATE TABLE IF NOT EXISTS "product_in_order" (
   order_id BIGINT NOT NULL,
   product_id BIGINT NOT NULL,
   created_at TIMESTAMP,
   updated_at TIMESTAMP,
   price BIGINT NOT NULL,
   quantity INTEGER NOT NULL,
   seq BIGINT PRIMARY KEY
);