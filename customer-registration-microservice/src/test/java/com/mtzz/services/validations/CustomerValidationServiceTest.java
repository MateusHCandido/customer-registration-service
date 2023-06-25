package com.mtzz.services.validations;

import com.mtzz.datas.repositories.impl.CustomerImpl;
import com.mtzz.datas.repositories.impl.UserCredentialsImpl;
import com.mtzz.domains.models.Customer;
import com.mtzz.domains.repositories.CustomerRepository;
import com.mtzz.services.exceptions.CPFAlreadyRegisteredException;
import com.mtzz.services.exceptions.InvalidNumberCountException;
import com.mtzz.services.exceptions.SpecialCharactersOrNumbersException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class CustomerValidationServiceTest extends CustomerValidationService
{
    @TestConfiguration
    static class CustomerValidationServiceTestConfiguration
    {
        @Bean
        public CustomerValidationService customerValidationService()
        {
            return new CustomerValidationService();
        }
    }

    @Autowired
    private CustomerValidationService customerValidationService;

    @MockBean
    private CustomerRepository customerRepository;

    @MockBean
    private CustomerImpl customerImpl;

    @MockBean
    private UserCredentialsImpl userCredentialsImpl;


    @Test
    public void shouldReturnTrueWhenItDoesNotFindACPFOccurrenceInDB()
    {
        String cpf = "123.456.789-10";

        boolean occurrenceOfCPF = hasNoOccurrenceOf(cpf);

        assertTrue(occurrenceOfCPF);
    }

    @Test(expected = CPFAlreadyRegisteredException.class)
    public void shouldReturnExceptionWhenItFindACPFOccurrenceInDB()
    {
        Customer customer = new Customer(1L, "Mateus Test", "123.456.789-10", LocalDate.now());
        String customerCPF = "123.456.789-10";
        customerRepository.save(customer);


        when(customerImpl.existsByCpf(customerCPF)).thenReturn(true);

        boolean occurrenceOfCPF = hasNoOccurrenceOf(customerCPF);
    }


    @Test
    public void shouldReturnFalseWhenItHasNoSpecialCharactersInName()
    {
        String name = "Mateus Test Testando";

        boolean nameValidation = checkOnlyExistenceOfLettersIn(name);

        assertFalse(nameValidation);
    }

    @Test(expected = SpecialCharactersOrNumbersException.class)
    public void shouldThrowExceptionWhenHaveSpecialCharactersInName()
    {
        String name = "M@teus Test Testando";

        boolean nameValidation = checkOnlyExistenceOfLettersIn(name);
    }

    @Test(expected = SpecialCharactersOrNumbersException.class)
    public void shouldThrowExceptionWhenItHasNumericalCharacters()
    {
        String name = "M4t3us T3st3";

        boolean nameValidation = checkOnlyExistenceOfLettersIn(name);
    }
}
