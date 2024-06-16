package org.openvpn.api.controller;

import org.openvpn.api.entity.VpnUser;
import org.openvpn.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping ("/api/users")

public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<VpnUser> getAllUsers() {
        return userRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<VpnUser> createUser(@RequestBody VpnUser user) {
        VpnUser savedVpnUser = userRepository.save(user);
        return new ResponseEntity<>(savedVpnUser, HttpStatus.CREATED);
    }

    @PutMapping ("/{id}")
    public ResponseEntity<VpnUser> updateUser(@PathVariable Long id, @RequestBody VpnUser user) throws ResourceNotFoundException {
        VpnUser existingVpnUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("VpnUser not found with id ::" + id));
        existingVpnUser.setUsername(user.getUsername());
        existingVpnUser.setValidFrom(user.getValidFrom());
        existingVpnUser.setValidTo(user.getValidTo());
        // Set other properties similarly

        VpnUser updatedVpnUser = userRepository.save(existingVpnUser);
        return new ResponseEntity<>(updatedVpnUser, HttpStatus.OK);
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
