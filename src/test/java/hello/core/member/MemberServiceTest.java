package hello.core.member;

import hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

    MemberService memberService;

    @BeforeEach
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }

    @Test
    void join() {
        //given - 테스트에 필요한 자원
        Member member = new Member(1L, "memberA", Grade.VIP);

        // when - 어떤 동작을 한 것인가
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        // then - 기대하는 결과
        Assertions.assertThat(member).isEqualTo(findMember);
    }
}
