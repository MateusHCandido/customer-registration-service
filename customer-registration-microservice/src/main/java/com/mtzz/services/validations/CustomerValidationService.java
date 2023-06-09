package com.mtzz.services.validations;

import com.mtzz.datas.repositories.impl.CustomerImpl;
import com.mtzz.services.exceptions.CPFAlreadyRegisteredException;
import com.mtzz.services.exceptions.InvalidNumberCountException;
import com.mtzz.services.exceptions.SpecialCharactersOrNumbersException;
import com.mtzz.services.utils.CPFUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerValidationService
{

    @Autowired
    private CustomerImpl customerImpl;


    public static boolean checkOnlyExistenceOfLettersIn(String name)
    {
        String specialCharacters = "^[a-zA-Z ]*$";

        if(name.matches(specialCharacters))
        {
            return false;
        }
        throw new SpecialCharactersOrNumbersException();
    }

    public boolean hasNoOccurrenceOf(String cpf)
    {
        cpf = CPFUtil.formatCpfNumber(cpf);

        if(!customerImpl.existsByCpf(cpf))
        {
            return true;
        }
        throw new CPFAlreadyRegisteredException();
    }

}
