package org.openvpn.api.controller;

import org.openvpn.api.entity.VpnUser;
import org.openvpn.api.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AppInitController {

    private final UserRepository userRepository;

    public AppInitController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping( "/admin/create" )
    public String showCreateAdminForm(Model model) {
        model.addAttribute("admin", new VpnUser()); // Assuming User is your entity class
        return "init";
    }

    @GetMapping( "/admin/init" )
    public String init(Model model) {
        model.addAttribute("admin", new VpnUser()); // Assuming User is your entity class
        return "init";
    }

    @PostMapping ("/admin/save")
    public String saveAdminUser(@ModelAttribute ("user") VpnUser user, RedirectAttributes redirectAttributes) {
        // Set admin role or any other necessary properties
        // For example: user.setRole("ADMIN");
        userRepository.save(user);
        redirectAttributes.addFlashAttribute("successMessage", "Admin user created successfully!");
        return "user-mgmt";
    }

    @GetMapping(" /view/users")
    public String viewUsers(Model model) {


        model.addAttribute("user", null);
        model.addAttribute("pageTitle", "Create new Adminuser");

        return "init";
    }

    // Add code to handle form submission and save the admin user
}
