package com.mtzz.domains.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Customer
{
    @Id
    @Column(name = "customer_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    @NotEmpty(message = "the customer name field is mandatory")
    @Column(name = "customer_name", nullable = false, length = 150)
    private String customerName;

    @Column(nullable = false, length = 11)
    @NotNull(message = "the customer cpf is mandatory")
    @CPF(message = "the customer cpf is invalid. verify field and try again")
    private String cpf;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "registration_date", updatable = false)
    private LocalDate registrationDate;


    @PrePersist
    public void prePersist()
    {
        setRegistrationDate(LocalDate.now());
    }
}
