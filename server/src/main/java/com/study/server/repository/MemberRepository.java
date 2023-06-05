package com.study.server.repository;

import com.study.server.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Member save(Member member);

    List<Member> findAll();

    Member findMemberById(Long id);

    void deleteById(Long id);
}
