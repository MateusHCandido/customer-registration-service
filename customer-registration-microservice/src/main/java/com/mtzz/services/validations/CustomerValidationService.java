package com.mtzz.services.validations;

import com.mtzz.datas.repositories.impl.CustomerImpl;
import com.mtzz.services.exceptions.CPFAlreadyRegisteredException;
import com.mtzz.services.exceptions.RepeatedNumberException;
import com.mtzz.services.exceptions.InvalidNumberCountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerValidationService
{

    @Autowired
    private CustomerImpl customerImpl;


    public boolean hasNoOccurrenceOf(String cpf)
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

    public String formatCpfNumber(String cpfSent)
    {
        cpfSent = cpfSent.replaceAll("[^0-9]", "");

        return String.format("%s%s%s.%s%s%s.%s%s%s-%s%s",
                cpfSent.charAt(0), cpfSent.charAt(1), cpfSent.charAt(2),
                cpfSent.charAt(3), cpfSent.charAt(4), cpfSent.charAt(5),
                cpfSent.charAt(6), cpfSent.charAt(7), cpfSent.charAt(8),
                cpfSent.charAt(9), cpfSent.charAt(10));
    }
}
