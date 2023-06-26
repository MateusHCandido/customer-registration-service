package com.mtzz.services;

import com.mtzz.datas.dto.CustomerRequest;
import com.mtzz.datas.dto.CustomerResponse;
import com.mtzz.datas.exceptions.CustomerNotFoundException;
import com.mtzz.datas.mappers.CustomerMapper;
import com.mtzz.datas.repositories.impl.CustomerImpl;
import com.mtzz.domains.models.Customer;
import com.mtzz.domains.repositories.CustomerRepository;
import com.mtzz.services.exceptions.CPFAlreadyRegisteredException;
import com.mtzz.services.exceptions.EmptyListException;
import com.mtzz.services.exceptions.SpecialCharactersOrNumbersException;
import com.mtzz.services.validations.CustomerValidationService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
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


    @Test
    public void shouldPresentAllRegisteredCustomer()
    {
        Customer John = new Customer(1L, "John Doe", "123.456.789-01", LocalDate.of(2021, 8, 15));
        Customer Jane = new Customer(2L, "Jane Smith", "987.654.321-09", LocalDate.of(2022, 3, 20));
        Customer Alice = new Customer(3L, "Alice Johnson", "456.789.123-45", LocalDate.of(2022, 12, 5));
        customerRepository.saveAll(Arrays.asList(John, Jane, Alice));
        List<Customer> customers = Arrays.asList(John, Jane, Alice);

        when(customerImpl.findAllCustomer()).thenReturn(customers);
        when(customerService.findAllCustomer()).thenReturn(customers);

        List<Customer> registeredCustomers = customerImpl.findAllCustomer();

        assertEquals(registeredCustomers.size(), 3);
    }

    @Test(expected = EmptyListException.class)
    public void shouldThrowEmptyListException()
    {
        List<Customer> customerEmptyList = new ArrayList<>();

        when(customerService.findAllCustomer()).thenReturn(customerEmptyList);

        List<Customer> customers = (List<Customer>) customerService.findAllCustomer();
        assertTrue(customers.isEmpty());
    }

    @Test
    public void shouldPresentAllCustomersRegisteredWithEqualName()
    {
        Customer customer1 = new Customer(1L, "João", "123.456.789-0", LocalDate.now());
        Customer customer2 = new Customer(2L, "João", "098.765.432-1", LocalDate.now());
        Customer customer3 = new Customer(3L, "João", "567.890.123-4", LocalDate.now());
        Customer customer4 = new Customer(4L, "Maria", "111.222.333-44", LocalDate.now());
        Customer customer5 = new Customer(5L, "Pedro", "555.666.777-88", LocalDate.now());
        String name = "João";
        customerRepository.saveAll(Arrays.asList(customer1, customer2, customer3, customer4, customer5));

        List<Customer> registeredCustomers = Arrays.asList(customer1, customer2, customer3);

        when(customerImpl.findAllByName(name)).thenReturn(registeredCustomers);
        when(customerService.findAllByName(name)).thenReturn(registeredCustomers);

        List<Customer> customerWithEqualNames = customerService.findAllByName(name);

        for (Customer customer: customerWithEqualNames)
        {
            assertEquals(customer.getCustomerName(), "João");
        }
    }

    @Test(expected = EmptyListException.class)
    public void shouldThrowEmptyListExceptionWhenCallMethodFindAllByName()
    {
        List<Customer> customerEmptyList = new ArrayList<>();
        String name = "Jõao";

        when(customerService.findAllCustomer()).thenReturn(customerEmptyList);

        List<Customer> customers = (List<Customer>) customerService.findAllByName(name);
        assertTrue(customers.isEmpty());
    }

    @Test
    @DisplayName("Whe the CPF is searched, a customer will be located i which it will be returned.")
    public void shouldReturnTrueWhenCallMethodFindByCpf()
    {
        Customer maria = new Customer(4L, "Maria", "111.222.333-44", LocalDate.now());
        Customer pedro = new Customer(5L, "Pedro", "555.666.777-88", LocalDate.now());
        String pedroCpf = pedro.getCpf();

        customerRepository.saveAll(Arrays.asList(maria, pedro));

        when(customerImpl.findByCpf(pedroCpf)).thenReturn(pedro);
        when(customerService.findByCpf(pedroCpf)).thenReturn(pedro);

        Customer customer = customerService.findByCpf(pedroCpf);

        assertEquals(customer.getCpf(), pedroCpf);
    }

    @Test(expected = CustomerNotFoundException.class)
    public void shouldThrowCustomerNotFoundExceptionWhenCallMethodFindByCpf()
    {
        String cpf = "999.999.999-99";
        Customer maria = new Customer(4L, "Maria", "111.222.333-44", LocalDate.now());
        Customer pedro = new Customer(5L, "Pedro", "555.666.777-80", LocalDate.now());
        customerRepository.saveAll(Arrays.asList(maria, pedro));

        when(customerService.findByCpf(cpf)).thenThrow(CustomerNotFoundException.class);

        Customer customer = customerService.findByCpf(cpf);
    }
}
