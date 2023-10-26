package hello.core.scan.filter;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.context.annotation.ComponentScan.*;

public class ComponentFilterAppConfigTest {

    @Test
    void filterScan() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);
        BeanA beanA = ac.getBean("beanA", BeanA.class);
        assertThat(beanA).isNotNull();
        
        // BeanB는 스캔 필터에서 exclude시켰기 때문에 스캔 대상에서 제회된다.
        assertThrows(
                NoSuchBeanDefinitionException.class,
                () -> ac.getBean("beanB", BeanB.class)
        );
     /*   assertThrows(
                NoSuchBeanDefinitionException.class,
                () -> ac.getBean("beanA", BeanA.class)
        );*/
    }

    @Configuration
    @ComponentScan(
            // type = FilterType.ANNOTATION이 기본 값 생략 가능
            // 실무에서는 @Component로도 충분하기 때문에 includeFilters 보다는 excludeFilters를 간혹 사용한다.
            includeFilters = @Filter(type = FilterType.ANNOTATION, classes = MyIncludeComponent.class),
            excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = MyExIncludeComponent.class)
    )
    static class ComponentFilterAppConfig {
    }


}
