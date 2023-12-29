package org.choongang.configs;

import lombok.RequiredArgsConstructor;
import org.choongang.commons.interceptors.CommonInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


    //인터셉터만 설정
    @Configuration
    @RequiredArgsConstructor
    public class InterceptorConfig implements WebMvcConfigurer {
        private final CommonInterceptor commonInterceptor;

        @Override
        public void addInterceptors(InterceptorRegistry registry) {
            registry.addInterceptor(commonInterceptor);//특정주소를 입력할때는 없어도 되지만 모든이면 없어도 됨.



        }


}
