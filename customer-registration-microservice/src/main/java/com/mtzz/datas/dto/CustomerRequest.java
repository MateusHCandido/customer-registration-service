package com.mtzz.datas.dto;


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
    @NotEmpty(message = "The name field is mandatory.")
    @Column(name = "customer_name", nullable = false, length = 150)
    private String customerName;

    @NotNull(message = "The CPF field is mandatory.")
    @CPF(message = "CPF is invalid.")
    private String cpf;


}
