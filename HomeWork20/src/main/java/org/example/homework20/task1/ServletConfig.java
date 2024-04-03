package org.example.homework20.task1;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServletConfig {
    @Bean
    public ServletRegistrationBean<ProxyServlet> proxyServletRegistration() {
        ServletRegistrationBean<ProxyServlet> registrationBean = new ServletRegistrationBean<>(new ProxyServlet(), "/proxy");
        return registrationBean;
    }
}