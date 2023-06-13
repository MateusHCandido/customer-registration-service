package com.mtzz.datas.mappers;

import com.mtzz.datas.dto.CustomerRequest;
import com.mtzz.datas.dto.CustomerResponse;
import com.mtzz.domains.models.Customer;

import static com.mtzz.services.utils.CPFUtil.formatCpfNumber;

public class CustomerMapper
{

    public static Customer receiveFrom(CustomerRequest customerRequest)
    {
        Customer customer = new Customer();

        String customerCpf = customerRequest.getCpf();
        customerCpf = formatCpfNumber(customerCpf);

        customer.setCustomerName(customerRequest.getCustomerName());
        customer.setCpf(customerCpf);

        return customer;
    }

    public static CustomerResponse responseOf(Customer customer)
    {
        CustomerResponse customerResponse = new CustomerResponse();
        String customerCpf = customer.getCpf().substring(0, 3) + ".***.***-**";

        customerResponse.setCustomerId(customer.getCustomerId());
        customerResponse.setCustomerName(customer.getCustomerName());
        customerResponse.setCustomerCpf(customerCpf);
        customerResponse.setRegistrationDate(customer.getRegistrationDate());

        return customerResponse;
    }
}
