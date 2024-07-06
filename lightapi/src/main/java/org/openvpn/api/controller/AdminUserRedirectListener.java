package org.openvpn.api.controller;

import org.openvpn.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class AdminUserRedirectListener implements ApplicationListener<ContextRefreshedEvent> {

    private final UserRepository userRepository;

    @Autowired
    public AdminUserRedirectListener(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        long adminUserCount = userRepository.countByRole("ADMIN");
        if (adminUserCount == 0) {
            //redirectUserToCreateAdmin();
        }
    }

    /*private void redirectUserToCreateAdmin() {
        HttpServletResponse response = ((HttpServletResponse) ServletUriComponentsBuilder.fromCurrentRequest().build().toUri());
        UriComponentsBuilder builder = ServletUriComponentsBuilder.fromCurrentContextPath().path("/admin/create");
        try {
            response.sendRedirect(builder.toUriString());
        } catch (IOException e) {
            // Handle redirect exception
            e.printStackTrace();
        }
    }*/
}
