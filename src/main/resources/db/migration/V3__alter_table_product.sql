ALTER TABLE product 
ADD COLUMN stock_movement_id BIGINT;

ALTER TABLE product
ADD CONSTRAINT fk_stock_movement
    FOREIGN KEY (stock_movement_id) 
    REFERENCES stock_movement(id);
