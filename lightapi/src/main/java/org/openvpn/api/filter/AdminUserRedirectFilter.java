package org.openvpn.api.filter;

import org.openvpn.api.entity.VpnUser;
import org.openvpn.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdminUserRedirectFilter implements Filter {

    private final UserRepository userRepository;

    public AdminUserRedirectFilter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization logic if needed
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        if (userRepository.countByRole("ADMIN") == 0) {
            //httpResponse.sendRedirect(httpRequest.getContextPath() + "/admin/create");
            ((HttpServletResponse) response).setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY); // Assuming User is your entity class
            //httpResponse.sendRedirect("init");
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        // Cleanup logic if needed
    }
}
