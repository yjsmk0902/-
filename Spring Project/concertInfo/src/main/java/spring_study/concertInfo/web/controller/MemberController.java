package spring_study.concertInfo.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring_study.concertInfo.domain.dto.MemberLoginRequestDTO;
import spring_study.concertInfo.jwt.TokenInfo;
import spring_study.concertInfo.web.service.MemberService;

@Slf4j
@RestController
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
