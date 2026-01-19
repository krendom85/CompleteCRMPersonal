package com.krendom85.CorePlatform.repository.Company;

import org.springframework.data.jpa.repository.JpaRepository;

import com.krendom85.CorePlatform.domain.Company.CompanyEntity;

public interface CompanyRepository extends JpaRepository<CompanyEntity, Long> {
    boolean existsByName(String name);
    boolean existsByRuc(String ruc);
}
