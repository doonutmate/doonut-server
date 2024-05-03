CREATE TABLE IF NOT EXISTS member_delete_reason
(
    id         BIGINT AUTO_INCREMENT NOT NULL COMMENT 'ID',
    member_id  BIGINT                              NOT NULL COMMENT '멤버 ID',
    reason     VARCHAR(500)                        NOT NULL COMMENT '탈퇴 이유',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '생성 시간',
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '수정 시간',
    PRIMARY KEY (id)
);
