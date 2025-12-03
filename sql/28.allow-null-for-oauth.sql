ALTER TABLE users MODIFY
   user_name VARCHAR(30) NULL;

ALTER TABLE users MODIFY
   user_role VARCHAR(30) NULL;

ALTER TABLE users DROP CONSTRAINT chk_user_contact;