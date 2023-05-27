package com.mtzz.datas.repositories.impl;

import com.mtzz.datas.exceptions.CustomerNotFoundException;
import com.mtzz.domains.models.Customer;
import com.mtzz.domains.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerImpl
{
    @Autowired
    private CustomerRepository customerRepository;


    public void saveCustomer(Customer customer)
    {
        customerRepository.save(customer);
    }

    public Customer findById(Long customerId)
    {
        return customerRepository
                .findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(customerId));
    }

    public void deleteCustomerDate(Customer customer)
    {
        customerRepository.delete(customer);
    }

    public Boolean existsByCpf(String cpf)
    {
        return customerRepository.existsByCpf(cpf);
    }

    public Customer findByCpf(String cpf)
    {
        return customerRepository.findByCpf(cpf);
    }
}
