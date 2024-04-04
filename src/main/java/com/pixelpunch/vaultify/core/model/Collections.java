package com.pixelpunch.vaultify.core.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name = "collections")
public class Collections {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Max(32)
    private String name;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date created = new Date();

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModified = new Date();

    @JsonIgnore
    @ManyToMany(mappedBy = "collections")
    private List<User> owner;
}
