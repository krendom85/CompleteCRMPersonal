package com.krendom85.CorePlatform.domain.Company;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import com.krendom85.CorePlatform.domain.User.UserEntity;
import com.krendom85.CorePlatform.domain.enums.StatesEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "companies")
@Getter
@Setter
public class CompanyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String name;
    
    @Column(nullable = false, unique = true)
    private String ruc; 
    
    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private StatesEnum active = StatesEnum.ACTIVE;
    
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;
    
    @Column(name = "updated_at")
    private Instant updatedAt;
    
    @OneToMany(mappedBy = "company")
    private Set<UserEntity> users = new HashSet<>();
    
    public CompanyEntity() {
        super();
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = Instant.now();
        this.updatedAt = null;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = Instant.now();
    }

    
}