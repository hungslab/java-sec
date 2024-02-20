package cn.hungslabsec.interceptor;

import javax.annotation.Resource;

import cn.hungslabsec.interceptor.AuthInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
/**
 * @author Hungs
 * @date 2024/2/6
 * @Description Description of the file.
 */
@Configuration
public class InterceptorConfig  implements WebMvcConfigurer {
    @Resource
    AuthInterceptor authInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor).addPathPatterns("/**")
                .excludePathPatterns("/api/user/login", "/api/user/regist");
    }
}