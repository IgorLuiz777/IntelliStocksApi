CREATE TABLE type_product (
                              id BIGINT AUTO_INCREMENT PRIMARY KEY,
                              name VARCHAR(255) NOT NULL
);

CREATE TABLE product (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         name VARCHAR(255) NOT NULL,
                         price DECIMAL(10, 2) NOT NULL,
                         type_product_id BIGINT,
                         desc VARCHAR(255),
                         model VARCHAR(255) NOT NULL,
                         brand VARCHAR(255) NOT NULL,
                         quantity INTEGER NOT NULL,
                         FOREIGN KEY (type_product_id) REFERENCES type_product(id)
);


INSERT INTO type_product (name) VALUES ('Electronics');
INSERT INTO type_product (name) VALUES ('Furniture');

INSERT INTO product (name, price, type_product_id, desc, model, brand, quantity)
VALUES ('Laptop', 999.99, 1, 'High performance laptop', 'XPS 15', 'Dell',
        10);

INSERT INTO product (name, price, type_product_id, desc, model, brand, quantity)
VALUES ('Chair', 199.99, 2, 'Comfortable office chair', 'Aeron',
        'Herman Miller', 5);

-- Adicionando mais produtos à tabela product
INSERT INTO product (name, price, type_product_id, desc, model, brand, quantity)
VALUES
    ('Smartphone', 799.99, 1, 'Latest model with advanced features', 'Galaxy S21', 'Samsung', 15),
    ('Headphones', 149.99, 1, 'Noise-cancelling over-ear headphones', 'WH-1000XM4', 'Sony', 25),
    ('Dining Table', 499.99, 2, 'Modern dining table with glass top', 'DT-2024', 'IKEA', 8),
    ('Sofa', 899.99, 2, 'Comfortable and stylish sofa', 'SofaMax', 'West Elm', 3),
    ('Monitor', 299.99, 1, '27-inch 4K Ultra HD monitor', 'UltraView 27', 'LG', 12),
    ('Office Desk', 249.99, 2, 'Ergonomic office desk with adjustable height', 'DeskPro', 'Herman Miller', 7);
