CREATE TABLE address (
   address_id       VARCHAR2(36) PRIMARY KEY NOT NULL,
   user_id          VARCHAR2(36) NOT NULL,
   receiver_name    VARCHAR2(30) NOT NULL,
   receiver_phone   VARCHAR2(13) NOT NULL,
   receiver_zipcode INT NOT NULL,
   receiver_addr1   VARCHAR2(100) NOT NULL,
   receiver_addr2   VARCHAR2(200),
   created_at       TIMESTAMP NOT NULL,
   CONSTRAINT fk_address_user FOREIGN KEY ( user_id )
      REFERENCES users ( user_id )
);