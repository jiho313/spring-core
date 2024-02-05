package hello.core.scope;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PrototypeTest {

    @Test
    void prototypeBeanFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        System.out.println("find prototypeBean1");
        // 프로토타입 스코프 빈은 스프링 컨테이너에서 빈을 조회할 때마다 생성되고 초기화 메서드도 실행된다.
        // 이 시점에서 새로운 빈을 생성하여 반환한다.
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        System.out.println("find prototypeBean2");
        // 이때 둘은 다른 객체이다.
        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);

        // 생성할 때 마다 새로운 객체를 생성한다.
        System.out.println("prototypeBean1 = " + prototypeBean1);
        System.out.println("prototypeBean2 = " + prototypeBean2);
        assertThat(prototypeBean1).isNotSameAs(prototypeBean2);

        // 프로토타입 빈은 스프링 컨테이너가 생성, 의존관계 주입, 초기화 까지만 관여하기에,
        // 스프링 컨테이너가 종료될 때 @PreDestroy와 같은 종료 메서드가 전혀 실행되지 않는다.
        // 때문에 직접 메서드를 요청해주어 리소스를 회수해준다.
        prototypeBean1.destroy();
        prototypeBean2.destroy();
        ac.close();
    }

    @Scope("prototype")
    static class PrototypeBean {
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
