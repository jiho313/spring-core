package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class SingletonTest {

    @Test
    void singletonBeanFind() {
        // 컨테이너 생성 시점에서 초기화 메서드가 실행된다.
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SingletonBean.class);
        System.out.println("find singletonBean1");
        SingletonBean singletonBean1 = ac.getBean(SingletonBean.class);
        System.out.println("find singletonBean2");
        SingletonBean singletonBean2 = ac.getBean(SingletonBean.class);

        // 둘은 같은 객체다.
        System.out.println("singletonBean1 = " + singletonBean1);
        System.out.println("singletonBean2 = " + singletonBean2);
        Assertions.assertThat(singletonBean1).isSameAs(singletonBean2);

        // 싱글톤 빈은 스프링 컨테이너가 종료될 때까지 관리하므로 컨테이너 종료될 때 @PreDestroy와 같은 빈의 종료 메서드가 실행된다.
        ac.close();
    }

    // 기본 값이 "singleton"이라 적지 않아도 된다.
    @Scope("singleton")
    static class SingletonBean {
        @PreDestroy
        public void destroy() {
            System.out.println("SingletonBean.destroy");
        }

        @PostConstruct
        public void init() {
            System.out.println("SingletonBean.init");
        }
    }
}
