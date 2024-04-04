package com.pixelpunch.vaultify.web.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pixelpunch.vaultify.core.model.Collections;
import com.pixelpunch.vaultify.core.model.User;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CipherDto {
    private Long id;
    @JsonIgnore
    private User owner;
    @Column(columnDefinition = "TEXT")
    private String data;
    private boolean favorite = false;
    private Collections collection;
    private boolean rePrompt = false;
    private Date created;
    private Date lastModified;
    private Date lastServerSync;
}
