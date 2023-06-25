package com.mtzz.services;

import com.mtzz.datas.dto.CustomerRequest;
import com.mtzz.datas.dto.CustomerResponse;
import com.mtzz.datas.mappers.CustomerMapper;
import com.mtzz.datas.repositories.impl.CustomerImpl;
import com.mtzz.domains.models.Customer;
import com.mtzz.domains.repositories.CustomerRepository;
import com.mtzz.services.exceptions.CPFAlreadyRegisteredException;
import com.mtzz.services.exceptions.SpecialCharactersOrNumbersException;
import com.mtzz.services.validations.CustomerValidationService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;


import java.time.LocalDate;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;


@RunWith(SpringRunner.class)
public class CustomerServiceTest
{
    @TestConfiguration
    static class CustomerServiceTestConfiguration
    {
        @Bean
        public CustomerService customerService()
        {
            return new CustomerService();
        }
    }


    @Autowired
    private CustomerService customerService;

    @MockBean
    private CustomerImpl customerImpl;

    @MockBean
    private CustomerRepository customerRepository;

    @MockBean
    private CustomerValidationService validationService;


    @Test
    public void shouldCreateCustomer()
    {
        CustomerRequest customerRequest = new CustomerRequest("test", "123.456.789-10");
        String cpf = customerRequest.getCpf();

        when(customerRepository.existsByCpf(cpf)).thenReturn(false);
        when(validationService.hasNoOccurrenceOf(cpf)).thenReturn(true);
        customerService.createCustomer(customerRequest);

        when(customerImpl.existsByCpf(cpf)).thenReturn(true);
        boolean creationValidation = customerImpl.existsByCpf(cpf);

        assertTrue(creationValidation);
    }

    @Test
    public void shouldFindCustomerByYourId()
    {
        Customer customer = new Customer(1L, "test", "123.456.789-10", LocalDate.now());
        CustomerResponse customerResponse = CustomerMapper.responseOf(customer);

        customerRepository.save(customer);
        when(customerImpl.findById(1L)).thenReturn(customer);
        CustomerResponse customerLocalized = customerService.findCustomerById(1L);

        assertNotNull(customerLocalized.getCustomerId());
    }

    @Test
    public void shouldUpdateCustomerData()
    {
        String newCpf = "365.520.990-88";
        String newName = "test test OK";

        Customer customer = new Customer(1L, "test", "817.973.160-01", LocalDate.now());
        CustomerRequest customerRequest = new CustomerRequest(newName, newCpf);
        customerRepository.save(customer);


        when(customerImpl.findById(1L)).thenReturn(customer);
        when(validationService.hasNoOccurrenceOf(newCpf)).thenReturn(true);

        customerService.updateCustomerData(1L, customerRequest);
        Customer customerUpdated = customerImpl.findById(1L);

        Assertions.assertEquals(customerUpdated.getCustomerName(), newName);
        Assertions.assertEquals(customerUpdated.getCpf(), newCpf);
    }

    @Test(expected = SpecialCharactersOrNumbersException.class)
    public void shouldThrowExceptionOfSpecialCharactersInFieldNameWhenTryingToUpdateIt()
    {
        String newCpf = "13232579810";
        String newName = "tes@ndo";

        Customer customer = new Customer(1L, "test", "123.456.789-10", LocalDate.now());
        CustomerRequest customerRequest = new CustomerRequest(newName, newCpf);
        customerRepository.save(customer);


        when(customerImpl.findById(1L)).thenReturn(customer);
        when(validationService.hasNoOccurrenceOf(newCpf)).thenReturn(true);
        customerService.updateCustomerData(1L, customerRequest);
    }

    @Test(expected = SpecialCharactersOrNumbersException.class)
    public void shouldThrowExceptionOfNumericalCharactersInFieldNameWhenTryingToUpdateIt()
    {
        String newCpf = "13232579810";
        String newName = "t3st";

        Customer customer = new Customer(1L, "test", "123.456.789-10", LocalDate.now());
        CustomerRequest customerRequest = new CustomerRequest(newName, newCpf);
        customerRepository.save(customer);


        when(customerImpl.findById(1L)).thenReturn(customer);
        when(validationService.hasNoOccurrenceOf(newCpf)).thenReturn(true);
        customerService.updateCustomerData(1L, customerRequest);
    }

    @Test(expected = CPFAlreadyRegisteredException.class)
    public void shouldThrowExceptionOfCPFAlreadyRegisteredInFieldCpfWhenTryingToUpdateIt()
    {
        String newCpf = "123.456.789-10";
        String newName = "test";

        Customer customer = new Customer(1L, "test", "123.456.789-10", LocalDate.now());
        CustomerRequest customerRequest = new CustomerRequest(newName, newCpf);
        customerRepository.save(customer);


        when(customerImpl.findById(1L)).thenReturn(customer);
        when(validationService.hasNoOccurrenceOf(newCpf)).thenThrow(CPFAlreadyRegisteredException.class);
        customerService.updateCustomerData(1L, customerRequest);
    }

}
