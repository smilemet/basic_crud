package com.study.server.service.impl;

import com.study.server.classes.CheckResult;
import com.study.server.entity.Member;
import com.study.server.repository.MemberRepository;
import com.study.server.classes.AddMemberRequest;
import com.study.server.service.MemberService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    /**
     * (create) 새 멤버 저장하기
     *
     * @param memberRequest
     * @return
     */
    @Override
    public CheckResult<Member> addMember(AddMemberRequest memberRequest) {
        Member newMember = new Member();

        newMember.setEmail(memberRequest.getEmail());
        newMember.setCreatedAt(LocalDateTime.now());
        newMember.setUpdatedAt(LocalDateTime.now());

        // TODO: salt 생성 로직 + salt로 password 암호화 로직 추가
        String encryptedPassword = memberRequest.getPassword();
        newMember.setPassword(encryptedPassword);
        newMember.setSalt("sfadsfasfsf");

        Member savedMember = memberRepository.save(newMember);

        if (savedMember == null) {
            return new CheckResult<Member>(false, "failed to save the member", null);
        }
        return new CheckResult<Member>(true, "", newMember);
    }

    /**
     * (update) 멤버 정보 수정하기
     *
     * @param member
     * @return
     */
    @Override
    public CheckResult<Member> updateMember(Long id, Member member) {
        Member existingMember = memberRepository.findMemberById(id);

        if (existingMember == null) {
            return new CheckResult<Member>(false, "Non-existent member", null);
        }

        existingMember.setEmail(member.getEmail());
        existingMember.setPassword(member.getPassword());
        existingMember.setSalt(member.getSalt()); // TODO: 수정할 필요 있는지 확인!
        existingMember.setUpdatedAt(member.getUpdatedAt());

        Member updatedMember = memberRepository.save(existingMember);

        if (updatedMember == null) {
            return new CheckResult<Member>(false, "failed to update the member", null);
        }
        return new CheckResult<Member>(true, "", existingMember);
    }

    /**
     * (read) 모든 멤버 가져오기
     *
     * @return
     */
    @Override
    public CheckResult<List<Member>> getAllMembers() {
        List<Member> memberList = memberRepository.findAll();

        if (memberList.isEmpty()) {
            return new CheckResult<List<Member>>(false, "no member", null);
        }
        return new CheckResult<List<Member>>(true, "", memberList);
    }

    /**
     * (read) 이메일로 멤버 가져오기
     *
     * @param id
     * @return
     */
    @Override
    public CheckResult<Member> getMemberById(Long id) {
        Member targetMember = memberRepository.findMemberById(id);

        if (targetMember == null) {
            return new CheckResult<Member>(false, "no member", null);
        }
        return new CheckResult<Member>(true, "", targetMember);
    }

    /**
     * (delete) 이메일로 멤버 삭제하기
     *
     * @param id
     * @return
     */
    @Override
    public void deleteMemberById(Long id) {
        memberRepository.deleteById(id);
    }
}
