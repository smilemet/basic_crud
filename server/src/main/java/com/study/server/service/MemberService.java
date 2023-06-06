package com.study.server.service;

import com.study.server.classes.CheckResult;
import com.study.server.entity.Member;
import com.study.server.classes.AddMemberRequest;

import java.util.List;

public interface MemberService {
    CheckResult<Member> addMember(AddMemberRequest memberRequest);
    CheckResult<Member> updateMember(Long id, Member member);
    CheckResult<Member> getMemberById(Long id);
    CheckResult<List<Member>> getAllMembers();
    void deleteMemberById(Long id);
}
