package com.czbank.easylife.config;

import com.czbank.easylife.config.intercepors.RedisSessionInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebSecurityConfig implements WebMvcConfigurer {
    @Bean
    public RedisSessionInterceptor getSessionInterceptor()
    {
        return new RedisSessionInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry)
    {
        //所有已api开头的访问都要进入RedisSessionInterceptor拦截器进行登录验证，并排除login接口(全路径)。必须写成链式，分别设置的话会创建多个拦截器。
        //必须写成getSessionInterceptor()，否则SessionInterceptor中的@Autowired会无效
        registry.addInterceptor(getSessionInterceptor()).addPathPatterns("/**").excludePathPatterns("/user/**");
    }
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//
//        registry.addMapping("/**")
//                .allowedOrigins("*")
//                .allowCredentials(true)
//                //.allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE", "PUT", "PATCH")
//                .allowedMethods("POST", "GET", "DELETE", "PUT", "PATCH")
//                .maxAge(3600 * 24);
//    }

}

