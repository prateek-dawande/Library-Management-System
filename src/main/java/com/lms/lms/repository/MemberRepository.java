package com.lms.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.lms.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long>{
    
}
