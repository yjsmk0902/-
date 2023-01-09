package spring_study.concertInfo.domain.dto;

import lombok.Data;

@Data
public class MemberLoginRequestDTO {
    private String memberId;
    private String password;
}
