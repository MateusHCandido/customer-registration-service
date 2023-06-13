package com.mtzz.datas.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponse
{
    private Long customerId;
    private String customerName;
    private String customerCpf;
    private LocalDate registrationDate;
}
