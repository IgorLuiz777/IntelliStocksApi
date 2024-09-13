CREATE TABLE minimum_stock (
                               id BIGINT AUTO_INCREMENT PRIMARY KEY,
                               product_id BIGINT,
                               min_quantity INT NOT NULL,
                               FOREIGN KEY (product_id) REFERENCES product(id)
);

CREATE TABLE stock_movement (
                                id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                product_id BIGINT,
                                quantity INT NOT NULL CHECK (quantity > 0),
                                date_movement DATE,
                                type_movement VARCHAR(10) NOT NULL CHECK (type_movement IN ('INPUT', 'OUTPUT')),
                                FOREIGN KEY (product_id) REFERENCES product(id)
);

INSERT INTO stock_movement (product_id, quantity, date_movement, type_movement)
VALUES
    (1, 100, '2024-09-01', 'INPUT'),
    (2, 50, '2024-09-02', 'INPUT'),
    (3, 75, '2024-09-03', 'OUTPUT'),
    (4, 30, '2024-09-04', 'OUTPUT'),
    (5, 20, '2024-09-05', 'OUTPUT');
