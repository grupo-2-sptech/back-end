package collectiva.org.collecta.config;

import collectiva.org.collecta.domain.campanha.interceptor.CampanhaViewInterceptor;
import collectiva.org.collecta.domain.campanha.service.CampanhaService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
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
}
