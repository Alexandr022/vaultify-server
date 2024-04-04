package com.pixelpunch.vaultify.core.component;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.core.env.Environment;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Objects;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestIP {
    @Component
    class RequestIPArgumentResolver implements HandlerMethodArgumentResolver {

        private final String ipHeader;

        @Autowired
        public RequestIPArgumentResolver(Environment environment) {
            this.ipHeader = environment.getProperty("http.ip.header");
        }

        @Override
        public boolean supportsParameter(MethodParameter parameter) {
            return parameter.hasParameterAnnotation(RequestIP.class);
        }

        @Override
        public String resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                      NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
            HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
            Objects.requireNonNull(request, "HttpServletRequest is null");

            return "remoteAddr".equals(ipHeader) ? request.getRemoteAddr() : request.getHeader(ipHeader);
        }
    }
}
