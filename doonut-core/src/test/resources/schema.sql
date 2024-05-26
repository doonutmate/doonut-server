CREATE TABLE IF NOT EXISTS example_entity
(
    id   BIGINT AUTO_INCREMENT NOT NULL COMMENT 'ID',
    name VARCHAR(100) NOT NULL COMMENT 'name',
    PRIMARY KEY (id)
);

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

CREATE TABLE IF NOT EXISTS profile_image
(
    id         BIGINT AUTO_INCREMENT               NOT NULL COMMENT 'ID',
    member_id  BIGINT                              NULL COMMENT '멤버 ID',
    image_type VARCHAR(100)                        NOT NULL COMMENT '이미지 타입',
    image_url  VARCHAR(500)                        NOT NULL COMMENT '이미지 url',
    deleted    BIT       DEFAULT 0                 NOT NULL COMMENT '삭제 여부',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '생성 시간',
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '수정 시간',
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS challenge
(
    id             BIGINT AUTO_INCREMENT                NOT NULL COMMENT 'ID',
    member_id      BIGINT                               NOT NULL COMMENT '멤버 ID',
    image_url      VARCHAR(500)                         NOT NULL COMMENT '이미지 url',
    deleted        BIT       DEFAULT 0                  NOT NULL COMMENT '삭제 여부',
    created_at     TIMESTAMP DEFAULT CURRENT_TIMESTAMP  NOT NULL COMMENT '생성 시간',
    updated_at     TIMESTAMP DEFAULT CURRENT_TIMESTAMP  NOT NULL COMMENT '수정 시간',
    primary key (id)
);

CREATE TABLE IF NOT EXISTS member_delete_reason
(
    id         BIGINT AUTO_INCREMENT NOT NULL COMMENT 'ID',
    member_id  BIGINT                              NOT NULL COMMENT '멤버 ID',
    reason     VARCHAR(500)                        NOT NULL COMMENT '탈퇴 이유',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '생성 시간',
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '수정 시간',
    PRIMARY KEY (id)
);

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
