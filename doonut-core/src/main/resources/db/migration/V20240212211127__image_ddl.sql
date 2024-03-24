CREATE TABLE IF NOT EXISTS image
(
    id             BIGINT AUTO_INCREMENT NOT NULL COMMENT 'ID',
    member_id      BIGINT NULL COMMENT '멤버 ID',
    image_key      VARCHAR(200)                         NOT NULL COMMENT '이미지 UUID 키값',
    ori_image_name VARCHAR(100) NULL COMMENT '원본 이미지 명',
    image_host_url VARCHAR(500)                         NOT NULL COMMENT '이미지 host url',
    height         INT                                  NOT NULL COMMENT '이미지 높이',
    width          INT                                  NOT NULL COMMENT '이미지 너비',
    capacity       INT                                  NOT NULL COMMENT '이미지 용량',
    deleted        BIT       DEFAULT 0                  NOT NULL COMMENT '삭제 여부',
    created_at     TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '생성 시간',
    updated_at     TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '수정 시간',
    PRIMARY KEY (id)
);
