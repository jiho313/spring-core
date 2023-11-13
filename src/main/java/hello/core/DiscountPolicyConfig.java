package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
  기술 지원 로직에서 수동으로 등록한 설정정보의 장점: 한눈에 빈의 이름과 어떤 빈들이 주입될지 파악할 수 있다.
  빈 자동 등록을 사용하고 싶으면 파악하기 좋게 DiscountPolicy의 구현 빈들만 따로 모아서 특정 패키지에 모아두자.
*/
@Configuration
public class DiscountPolicyConfig {

    @Bean
    public DiscountPolicy rateDiscountPolicy() {
        return new RateDiscountPolicy();
    }
    @Bean
    public DiscountPolicy fixDiscountPolicy() {
        return new FixDiscountPolicy();
    }
}
