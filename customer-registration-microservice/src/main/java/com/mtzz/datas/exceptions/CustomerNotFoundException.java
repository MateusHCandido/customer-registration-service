package com.mtzz.datas.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor
public class CustomerNotFoundException extends RuntimeException
{
    private String reason = "Customer not found";
    private HttpStatus status = HttpStatus.BAD_REQUEST;

}
