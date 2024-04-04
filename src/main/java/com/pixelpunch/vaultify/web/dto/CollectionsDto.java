package com.pixelpunch.vaultify.web.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CollectionsDto {
    private Long id;
    private String name;
    private Date created;
    private Date lastModified;
    @JsonIgnore
    private List<UserDto> owner;
}
