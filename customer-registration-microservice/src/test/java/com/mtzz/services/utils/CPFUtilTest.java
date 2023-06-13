package com.mtzz.services.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static com.mtzz.services.utils.CPFUtil.formatCpfNumber;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
public class CPFUtilTest
{
    @Test
    public void shouldFormatCpfAccordingToRegistrationStandard()
    {
        boolean correctPositionInFormatting = false;
        String validUnformattedCpf = "12345678900";
        String validFormattedCpf = "123.456.789-00";

        String formattedCpf = formatCpfNumber(validUnformattedCpf);

        char firstPoint = formattedCpf.charAt(3);
        char secondPoint = formattedCpf.charAt(7);
        char hyphen = formattedCpf.charAt(11);

        if(firstPoint == '.' && secondPoint == '.' && hyphen == '-')
        {
            correctPositionInFormatting = true;
        }

        assertEquals(validFormattedCpf, formattedCpf);
        assertTrue(correctPositionInFormatting);
    }
}
