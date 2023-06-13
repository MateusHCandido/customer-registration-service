package com.mtzz.services;

import com.mtzz.datas.dto.CustomerRequest;
import com.mtzz.datas.dto.CustomerResponse;
import com.mtzz.datas.mappers.CustomerMapper;
import com.mtzz.datas.repositories.impl.CustomerImpl;
import com.mtzz.domains.models.Customer;
import com.mtzz.services.validations.CustomerValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.mtzz.services.validations.CustomerValidationService.*;


@Service
public class CustomerService
{

    @Autowired
    private CustomerImpl customerImpl;

    @Autowired
    private CustomerValidationService customerValidationService;


    public void createCustomer(CustomerRequest customerRequest)
    {
        Customer customer = new Customer();

        String customerName = customerRequest.getCustomerName();
        String customerCpf = customerRequest.getCpf();

        if(!checkOnlyExistenceOfLettersIn(customerName))
        {
            if(customerValidationService.hasNoOccurrenceOf(customerCpf) && validateCpfNumbers(customerCpf))
            {
                customer = CustomerMapper.receiveFrom(customerRequest);
                customerImpl.saveCustomer(customer);
            }
        }
    }

    public CustomerResponse findCustomerById(Long customerId)
    {
        Customer customer = customerImpl.findById(customerId);

        return CustomerMapper.responseOf(customer);
    }

    public CustomerResponse updateCustomerData(Long customerId, CustomerRequest customerRequest)
    {
        Customer customer = customerImpl.findById(customerId);

        String customerName = customerRequest.getCustomerName();
        String customerCpf = customerRequest.getCpf();

        if(!checkOnlyExistenceOfLettersIn(customerName))
        {
            if(customerValidationService.hasNoOccurrenceOf(customerCpf) && validateCpfNumbers(customerCpf))
            {
                customer = CustomerMapper.receiveFrom(customerRequest);
                customerImpl.saveCustomer(customer);
            }
        }

        return CustomerMapper.responseOf(customer);
    }

    public void deleteCustomer(Long customerId)
    {
        Customer customer= customerImpl.findById(customerId);

        customerImpl.deleteCustomerDate(customer);
    }
}
