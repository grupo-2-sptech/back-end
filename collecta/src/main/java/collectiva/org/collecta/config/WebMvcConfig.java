package collectiva.org.collecta.config;

import collectiva.org.collecta.domain.campanha.interceptor.CampanhaViewInterceptor;
import collectiva.org.collecta.domain.campanha.service.CampanhaService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {

    private final CampanhaService campanhaService;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        CampanhaViewInterceptor campanhaViewInterceptor = new CampanhaViewInterceptor(campanhaService);
        registry.addInterceptor(campanhaViewInterceptor);
    }

    @Configuration
    @EnableWebMvc
    public class WebConfig implements WebMvcConfigurer {

        @Override
        public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/**")
                    .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS", "HEAD", "TRACE", "CONNECT");
        }
    }
}
