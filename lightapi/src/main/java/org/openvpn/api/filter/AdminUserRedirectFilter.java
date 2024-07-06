package org.openvpn.api.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.openvpn.api.repository.UserRepository;

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
