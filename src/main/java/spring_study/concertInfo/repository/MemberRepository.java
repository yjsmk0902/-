package spring_study.concertInfo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring_study.concertInfo.domain.entity.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByMemberId(String username);
}
