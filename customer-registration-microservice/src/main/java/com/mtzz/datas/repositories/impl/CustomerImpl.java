package com.mtzz.datas.repositories.impl;

import com.mtzz.datas.exceptions.CustomerNotFoundException;
import com.mtzz.domains.models.Customer;
import com.mtzz.domains.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
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
                .orElseThrow(() -> new CustomerNotFoundException());
    }

    public void deleteCustomerDate(Customer customer)
    {
        customerRepository.delete(customer);
    }

    public boolean existsByCpf(String cpf)
    {
        return customerRepository.existsByCpf(cpf);
    }

    public Customer findByCpf(String cpf)
    {
        Customer customer = customerRepository.findByCpf(cpf);

        if(customer.getCpf().equals(cpf))
        {
            throw new CustomerNotFoundException();
        }

        return customerRepository.findByCpf(cpf);
    }

    public List<Customer> findAllCustomer()
    {
        return customerRepository.findAll();
    }

    public List<Customer> findAllByName(String customerName)
    {
        return customerRepository.findAllByName(customerName);
    }

}
