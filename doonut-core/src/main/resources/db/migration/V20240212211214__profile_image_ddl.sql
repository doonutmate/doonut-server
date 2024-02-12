CREATE TABLE profile_image
(
    id         BIGINT AUTO_INCREMENT NOT NULL COMMENT 'ID',
    member_id  BIGINT NULL COMMENT '멤버 ID',
    image_type VARCHAR(100)                         NOT NULL COMMENT '이미지 타입',
    image_url  VARCHAR(500)                         NOT NULL COMMENT '이미지 url',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '생성 시간',
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '수정 시간',
    PRIMARY KEY (id)
)