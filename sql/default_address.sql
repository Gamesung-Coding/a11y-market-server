CREATE TABLE default_address (
   user_id    VARCHAR2(36) PRIMARY KEY NOT NULL,
   address_id VARCHAR2(36) NOT NULL,
   CONSTRAINT fk_default_user FOREIGN KEY ( user_id )
      REFERENCES users ( user_id ),
   CONSTRAINT fk_default_address FOREIGN KEY ( address_id )
      REFERENCES address ( address_id )
);