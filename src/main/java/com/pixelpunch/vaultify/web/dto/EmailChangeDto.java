package com.pixelpunch.vaultify.web.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pixelpunch.vaultify.core.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmailChangeDto {
    private Long id;
    private String newEmail;
    private String code;
    private Date codeExpiresAt;
    private String newCiphers;
    private String newPublicKey;
    @JsonIgnore
    private User user;
}
