package com.study.server.controller;

import com.study.server.classes.CheckResult;
import com.study.server.entity.Member;
import com.study.server.classes.AddMemberRequest;
import com.study.server.service.MemberService;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping
    public ResponseEntity<List<Member>> getAllMembers() {
        CheckResult<List<Member>> result = memberService.getAllMembers();
        if (!result.isSucceed()) {
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok(result.getData());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Member> getMemberByEmail(@PathVariable("id") Long id) {
        CheckResult<Member> result = memberService.getMemberById(id);

        if (!result.isSucceed()) {
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok(result.getData());
    }

    @PostMapping
    public ResponseEntity<Member> createMember(@RequestBody AddMemberRequest memberRequest) {
        CheckResult<Member> result = memberService.addMember(memberRequest);

        if (!result.isSucceed()) {
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok(result.getData());
    }

    @PostMapping("/{id}")
    public ResponseEntity<Member> updateMember(@PathParam("id") Long id, @RequestBody Member member) {
        CheckResult<Member> result = memberService.updateMember(id, member);

        if (!result.isSucceed()) {
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok(result.getData());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMemberById(@PathVariable Long id) {
        memberService.deleteMemberById(id);
        return ResponseEntity.ok().build();
    }
}
