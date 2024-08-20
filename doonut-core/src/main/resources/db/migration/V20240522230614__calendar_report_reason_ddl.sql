CREATE TABLE IF NOT EXISTS calendar_report_reason
(
    id          BIGINT AUTO_INCREMENT               NOT NULL COMMENT 'ID',
    reporter_id BIGINT                              NOT NULL COMMENT '신고자 ID',
    calendar_id BIGINT                              NOT NULL COMMENT '신고된 캘린더 ID',
    reason      VARCHAR(200)                        NOT NULL COMMENT '신고 이유',
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '생성 시간',
    updated_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '수정 시간',
    PRIMARY KEY (id)
);