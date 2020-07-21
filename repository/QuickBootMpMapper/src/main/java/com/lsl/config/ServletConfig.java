package com.lsl.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gome.spring.compents.servlet.DynServlet;

@Configuration
public class ServletConfig {

    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
        return new ServletRegistrationBean(new DynServlet(), "/dyn/admin/*");
    }

}
