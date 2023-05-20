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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    @Column(nullable = false, length = 150)
    @NotEmpty(message = "{mandatory.name.field}")
    private String customerName;

    @Column(nullable = false, length = 11)
    @NotNull(message = "{mandatory.cpf.field}")
    @CPF(message = "{invalid.cpf.field}")
    private String cpf;

    @Column(name = "registration_date", updatable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate registrationDate;


    @PrePersist
    public void prePersist()
    {
        setRegistrationDate(LocalDate.now());
    }
}
