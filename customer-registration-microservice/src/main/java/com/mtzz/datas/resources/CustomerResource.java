package com.mtzz.datas.resources;

import com.mtzz.datas.dto.CustomerRequest;
import com.mtzz.datas.dto.CustomerResponse;
import com.mtzz.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "api/customers/")
public class CustomerResource
{
    @Autowired
    private CustomerService customerService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveCustomer(@RequestBody CustomerRequest customerRequest)
    {
        customerService.createCustomer(customerRequest);
    }

    @GetMapping(path = "find/id/{customerId}")
    public CustomerResponse findCustomerById(@PathVariable Long customerId)
    {
        return customerService.findCustomerById(customerId);
    }

    @DeleteMapping(path = "delete/id/{customerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable Long customerId)
    {
        customerService.deleteCustomer(customerId);
    }

    @PutMapping(path = "update/id/{customerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCustomer(@PathVariable Long customerId, @RequestBody CustomerRequest customerRequest)
    {
        customerService.updateCustomerData(customerId, customerRequest);
    }


}
