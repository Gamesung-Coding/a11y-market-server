-- order_items의 product_id 컬럼을 NULL 허용으로 변경
ALTER TABLE order_items MODIFY
   product_id RAW(16) NULL;


ALTER TABLE order_items DROP CONSTRAINT fk_orderitem_product;

ALTER TABLE order_items
   ADD CONSTRAINT fk_orderitem_product
      FOREIGN KEY ( product_id )
         REFERENCES products ( product_id )
            ON DELETE SET NULL;