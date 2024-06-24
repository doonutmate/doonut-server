ALTER TABLE member
    ADD COLUMN service_alarm BIT DEFAULT 1 NOT NULL COMMENT '서비스 알람' AFTER oauth_type;

ALTER TABLE member
    ADD COLUMN late_night_alarm BIT DEFAULT 1 NOT NULL COMMENT '심야시간 알람' AFTER service_alarm;

ALTER TABLE member
    ADD COLUMN marketing_receive_consent BIT DEFAULT 1 NOT NULL COMMENT '마케팅 수신 동의' AFTER late_night_alarm;

ALTER TABLE member
    ADD COLUMN marketing_receive_consent_updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '마케팅 수신 동의 업데이트 시간'
        AFTER marketing_receive_consent;
