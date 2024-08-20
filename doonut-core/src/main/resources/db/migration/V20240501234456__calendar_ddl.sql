CREATE TABLE IF NOT EXISTS calendar
(
    id                BIGINT AUTO_INCREMENT               NOT NULL COMMENT 'ID',
    member_id         BIGINT                              NOT NULL COMMENT '멤버 ID',
    calendar_name     VARCHAR(100)                        NOT NULL COMMENT '캘린더 명',
    total_count       INT       DEFAULT 0                 NOT NULL COMMENT '총 기록 횟수',
    first_uploaded_at TIMESTAMP                           NULL COMMENT '최초 업로드 날짜',
    last_uploaded_at  TIMESTAMP                           NULL COMMENT '마지막 업로드 날짜',
    deleted           BIT       DEFAULT 0                 NOT NULL COMMENT '삭제 여부',
    created_at        TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '생성 시간',
    updated_at        TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '수정 시간',
    PRIMARY KEY (id)
);
