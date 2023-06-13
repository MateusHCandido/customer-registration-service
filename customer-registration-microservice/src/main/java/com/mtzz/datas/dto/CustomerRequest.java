package com.mtzz.datas.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRequest
{
    @NotEmpty(message = "the customer name field is mandatory")
    @Column(name = "customer_name", nullable = false, length = 150)
    private String customerName;

    @NotNull(message = "the customer cpf is mandatory")
    @CPF(message = "the customer cpf is invalid. verify field and try again")
    private String cpf;


}
