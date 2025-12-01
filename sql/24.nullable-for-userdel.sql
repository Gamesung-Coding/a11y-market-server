-- order_items의 product_id 컬럼을 NULL 허용으로 변경
ALTER TABLE order_items MODIFY
   product_id RAW(16) NULL;