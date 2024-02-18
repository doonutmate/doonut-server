CREATE TABLE challenge
(
    id             BIGINT AUTO_INCREMENT NOT NULL COMMENT 'ID',
    member_id      BIGINT NULL COMMENT '멤버 ID',
    image_url      VARCHAR(500)                         NOT NULL COMMENT '이미지 url',
    deleted        BIT       DEFAULT 0                  NOT NULL COMMENT '삭제 여부',
    created_at     TIMESTAMP DEFAULT CURRENT_TIMESTAMP  NOT NULL COMMENT '생성 시간',
    updated_at     TIMESTAMP DEFAULT CURRENT_TIMESTAMP  NOT NULL COMMENT '수정 시간',
    primary key (id)
)