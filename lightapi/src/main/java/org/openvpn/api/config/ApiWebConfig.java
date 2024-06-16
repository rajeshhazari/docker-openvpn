package org.openvpn.api.config;

import org.openvpn.api.filter.AdminUserRedirectFilter;
import org.openvpn.api.repository.UserRepository;
import org.openvpn.api.service.DateFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiWebConfig {

    @Autowired
    UserRepository userRepository;
    @Bean
    public FilterRegistrationBean<AdminUserRedirectFilter> adminUserRedirectFilter() {
        FilterRegistrationBean<AdminUserRedirectFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new AdminUserRedirectFilter(userRepository));
        registrationBean.addUrlPatterns("/*"); // Apply filter to all URLs
        return registrationBean;
    }

    @Bean
    public DateFormatter dateFormatter() {
        return new DateFormatter();
    }
}
