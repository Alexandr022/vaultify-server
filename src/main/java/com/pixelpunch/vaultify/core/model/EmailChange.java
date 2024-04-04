package com.pixelpunch.vaultify.core.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "email_change")
public class EmailChange {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, columnDefinition = "TEXT")
    private String newEmail;

    private String code;
    private Date codeExpiresAt;

    @Column(columnDefinition = "TEXT")
    private String newCiphers;

    private String newPublicKey;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User owner;
}
