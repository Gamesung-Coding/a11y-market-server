CREATE TABLE user_a11y_settings (
   user_id            RAW(16) PRIMARY KEY,
   contrast_level     NUMBER(1) DEFAULT 0 NOT NULL,
   text_size_level    NUMBER(1) DEFAULT 0 NOT NULL,
   text_spacing_level NUMBER(1) DEFAULT 0 NOT NULL,
   line_height_level  NUMBER(1) DEFAULT 0 NOT NULL,
   text_align         VARCHAR2(10) DEFAULT 'left' NOT NULL,
   screen_reader      NUMBER(1) DEFAULT 0 NOT NULL,
   smart_contrast     NUMBER(1) DEFAULT 0 NOT NULL,
   highlight_links    NUMBER(1) DEFAULT 0 NOT NULL,
   cursor_highlight   NUMBER(1) DEFAULT 0 NOT NULL,
   updated_at         TIMESTAMP DEFAULT systimestamp NOT NULL,
   CONSTRAINT fk_user_a11y_settings FOREIGN KEY ( user_id )
      REFERENCES users ( user_id )
         ON DELETE CASCADE
);