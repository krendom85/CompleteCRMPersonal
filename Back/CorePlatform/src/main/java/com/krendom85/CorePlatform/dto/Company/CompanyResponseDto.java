package com.krendom85.CorePlatform.dto.Company;

import java.time.Instant;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyResponseDto {
    private Long id;
    private String name;
    private String ruc;
    private String address;
    private String active;
    private Instant createdAt;
    private Instant updatedAt;
}