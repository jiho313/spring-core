package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        // 탐색할 패키지의 시작 위치를 지정한다.
//         basePackages = "hello.core",
        // 지정한 클래스의 패키지를 탐색 시작 위치로 지정한다.
//         basePackageClasses = AutoAppConfig.class,
        // 지정하지 않으면 @ComponentScan가 붙은 설정 정보 클래스의 패키지가 시작위치가 된다.
        // 제외할 컴포넌트 타입 설정하기
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

}
