CREATE TABLE minimum_stock (
                               id BIGINT AUTO_INCREMENT PRIMARY KEY,
                               product_id BIGINT,
                               min_quantity INT NOT NULL,
                               FOREIGN KEY (product_id) REFERENCES product(id)
);

CREATE TABLE stock_moviment (
                                id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                product_id BIGINT,
                                quantity INT NOT NULL CHECK (quantity > 0),
                                date_moviment DATE,
                                type_moviment VARCHAR(10) NOT NULL CHECK (type_moviment IN ('ENTRADA', 'SAIDA')),
                                FOREIGN KEY (product_id) REFERENCES product(id)
);

INSERT INTO stock_moviment (product_id, quantity, date_moviment, type_moviment)
VALUES
    (1, 100, '2024-09-01', 'ENTRADA'),
    (2, 50, '2024-09-02', 'ENTRADA'),
    (3, 75, '2024-09-03', 'SAIDA'),
    (4, 30, '2024-09-04', 'SAIDA'),
    (5, 20, '2024-09-05', 'SAIDA');
