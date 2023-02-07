package spring_study.concertInfo.web.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import spring_study.concertInfo.domain.dto.MemberDTO;
import spring_study.concertInfo.jwt.TokenInfo;
import spring_study.concertInfo.web.service.MemberService;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }
    @PostMapping("/login")
    public TokenInfo login(@RequestBody MemberDTO memberDTO) {
        String memberId = memberDTO.getMemberId();
        String password = memberDTO.getPassword();
        TokenInfo tokenInfo = memberService.login(memberId, password);
        return tokenInfo;
    }

    @GetMapping("/signIn")
    public String signIn() {
        return "signin";
    }
    @PostMapping("/signIn")
    public String signIn(@RequestBody MemberDTO memberDTO) {
        memberService.signIn(memberDTO);
        return "login";
    }

    @PostMapping("/test")
    public String test() {
        return "success";
    }
}
