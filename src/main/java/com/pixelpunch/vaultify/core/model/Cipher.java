package com.pixelpunch.vaultify.core.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cipher")
public class Cipher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    @JsonIgnore
    private User owner;
    @Column(columnDefinition = "TEXT")
    private String data;
    private boolean favorite = false;
    @ManyToOne
    private Collections collection;
    private boolean rePrompt = false;
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModified;
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastServerSync;
}
