package org.openvpn.api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import org.openvpn.api.entity.VpnUser;

import java.time.LocalDateTime;

@Entity
@Table (name = "user_audit_log")
public class UserAuditLog {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn (name = "email", nullable = false)
    private VpnUser user;

    @Column (nullable = false)
    private String action;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    // Getters and setters
}