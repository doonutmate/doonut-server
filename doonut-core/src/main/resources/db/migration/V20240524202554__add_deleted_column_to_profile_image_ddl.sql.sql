ALTER TABLE profile_image
    ADD COLUMN deleted BIT DEFAULT 0 NOT NULL COMMENT '삭제 여부';
