package com.mtzz.datas.exceptions.ApiErrors;

import lombok.Getter;



import java.util.List;

import static java.util.Arrays.asList;

public class ApiErrors {

    @Getter
    private List<String> errors;

    public ApiErrors(List<String> errors)
    {
        this.errors = errors;
    }

    public ApiErrors(String message)
    {
        this.errors = asList(message);
    }
}
