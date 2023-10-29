package com.doonutmate.doonut.member.repository;

import com.doonutmate.doonut.member.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
}
