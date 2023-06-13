package com.mtzz.datas.exceptions;

public class CustomerNotFoundException extends RuntimeException
{
    public CustomerNotFoundException(Long customerId)
    {
        super("Customer with id (" + customerId + ") not found");
    }
}
