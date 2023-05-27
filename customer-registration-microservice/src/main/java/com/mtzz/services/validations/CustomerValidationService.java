package com.mtzz.services.validations;

import com.mtzz.datas.repositories.impl.CustomerImpl;
import com.mtzz.services.exceptions.CPFAlreadyRegisteredException;
import com.mtzz.services.exceptions.RepeatedNumberException;
import com.mtzz.services.exceptions.InvalidNumberCountException;
import com.mtzz.services.exceptions.SpecialCharactersOrNumbersException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerValidationService
{

    @Autowired
    private static CustomerImpl customerImpl;

    public static boolean checkOnlyExistenceOfLettersIn(String name)
    {
        String specialCharacters = "^[a-zA-Z ]*$";

        if(name.matches(specialCharacters))
        {
            return false;
        }
        throw new SpecialCharactersOrNumbersException();
    }

    public static boolean hasNoOccurrenceOf(String cpf)
    {
        if(!customerImpl.existsByCpf(cpf))
        {
            return true;
        }
        throw new CPFAlreadyRegisteredException();
    }

    public static boolean validateCpfNumbers(String cpfSent)
    {
        cpfSent = cpfSent.replaceAll("[^0-9]", "");

        if (cpfSent.length() != 11) {
            throw new InvalidNumberCountException();
        }

        if(cpfSent.matches("(\\d)\\1{10}"))
        {
            throw new RepeatedNumberException();
        }

        return true;
    }

}
