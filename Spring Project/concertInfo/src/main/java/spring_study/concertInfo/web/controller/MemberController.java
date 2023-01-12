package spring_study.concertInfo.web.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import spring_study.concertInfo.domain.dto.MemberLoginRequestDTO;
import spring_study.concertInfo.jwt.TokenInfo;
import spring_study.concertInfo.web.service.MemberService;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/login")
    public TokenInfo login(@RequestBody MemberLoginRequestDTO memberLoginRequestDTO) {
        String memberId = memberLoginRequestDTO.getMemberId();
        String password = memberLoginRequestDTO.getPassword();
        TokenInfo tokenInfo = memberService.login(memberId, password);
        return tokenInfo;
    }

    @PostMapping("/test")
    public String test() {
        return "success";
    }
}
