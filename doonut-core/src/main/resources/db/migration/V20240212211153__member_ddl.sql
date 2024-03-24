CREATE TABLE IF NOT EXISTS member
(
    id         BIGINT AUTO_INCREMENT NOT NULL COMMENT 'ID',
    name       VARCHAR(100)                         NOT NULL COMMENT '멤버 명',
    email      VARCHAR(200)                         NOT NULL COMMENT '멤버 이메일',
    oauth_id   VARCHAR(100)                         NOT NULL COMMENT '소셜 로그인 ID',
    oauth_type VARCHAR(100)                         NOT NULL COMMENT '소셜 로그인 타입',
    deleted    BIT       DEFAULT 0                  NOT NULL COMMENT '삭제 여부',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '생성 시간',
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '수정 시간',
    PRIMARY KEY (id)
);
