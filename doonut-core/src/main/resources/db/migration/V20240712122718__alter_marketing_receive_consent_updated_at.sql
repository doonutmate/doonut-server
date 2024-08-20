ALTER TABLE member
    MODIFY COLUMN marketing_receive_consent_updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NULL COMMENT '마케팅 수신 동의 업데이트 시간'
        AFTER marketing_receive_consent;