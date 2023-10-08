package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.order.OrderService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextInfoTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 빈 출력하기")
    void findAllBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            Object bean = ac.getBean(beanDefinitionName);
            System.out.println("name= " + beanDefinitionName + "/ object = " + bean);
        }
    }

    @Test
    @DisplayName("애플리케이션 빈 출력하기")
    void findApplicationBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);

            // getRole()을 통해 스프링에서 사용하는 Bean을 구분할 수 있다.
            // ROLE_APPLICATION : 일반적으로 사용자가 정의한 빈
            // ROLE_INFRASTRUCTURE : 스프링이 내부에서 사용하는 빈
            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                Object bean = ac.getBean(beanDefinitionName);
                System.out.println("name= " + beanDefinitionName + " / object = " + bean);
            }
        }
    }

    @Test
    @DisplayName("빈 이름으로 찾아 출력해보기")
    void findBeanByName() {
        OrderService findBean1 = ac.getBean("orderService", OrderService.class);
        DiscountPolicy findBean2 = ac.getBean("discountPolicy", DiscountPolicy.class);
        System.out.println("findBean1 = " + findBean1);
        System.out.println("findBean2 = " + findBean2);
    }
}
