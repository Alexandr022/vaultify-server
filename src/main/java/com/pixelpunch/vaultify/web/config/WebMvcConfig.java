package com.pixelpunch.vaultify.web.config;

import com.pixelpunch.vaultify.core.component.RequestIP.RequestIPArgumentResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {

    private final Environment environment;

    public WebMvcConfig(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new RequestIPArgumentResolver(environment));
    }
}




