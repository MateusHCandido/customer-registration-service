package com.mtzz.domains.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
public class ServiceRegistry
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long serviceId;

    @Column(nullable = false, length = 150)
    private String serviceDescription;

    @Column
    private BigDecimal serviceValue;

    @Column
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate serviceDate;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
