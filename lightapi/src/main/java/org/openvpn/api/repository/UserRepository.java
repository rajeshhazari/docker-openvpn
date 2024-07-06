package org.openvpn.api.repository;

import org.openvpn.api.entity.VpnUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<VpnUser, Long> {

    // Custom query to count admin users
    // Method to count admin users
    long countByRole(String role);
}

