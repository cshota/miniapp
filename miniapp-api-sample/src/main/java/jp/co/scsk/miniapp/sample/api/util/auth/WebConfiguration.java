package jp.co.scsk.miniapp.sample.api.util.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Bean
    Interceptor interceptor() {
        return new Interceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // CORS Interceptor (for all requests)
        registry.addInterceptor(new HandlerInterceptor() {
            @Override
            public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
                                     @NonNull Object handler) {
                response.addHeader("Access-Control-Allow-Origin", "*");
                response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
                response.addHeader("Access-Control-Allow-Headers", "*");
                return true;
            }

            @Override
            public void postHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
                                   @NonNull Object handler, ModelAndView modelAndView) {
            }

            @Override
            public void afterCompletion(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
                                        @NonNull Object handler, Exception ex) {
            }
        });
        // LOGIN Interceptor (Only for sdk and integration)
        registry.addInterceptor(interceptor())
                //filter all patterns
                .addPathPatterns("/**")
                //exclude login patterns
                .excludePathPatterns("/auth/**")
                .excludePathPatterns("/error/**")
                .excludePathPatterns("/index/**")
                .excludePathPatterns("/shop/**")
                // swagger
                .excludePathPatterns("/swagger-ui/**")
                .excludePathPatterns("/v3/api-docs/**")
            ;
    }
}
