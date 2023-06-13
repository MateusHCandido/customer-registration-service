package com.mtzz.services.utils;

public class CPFUtil
{
    public static String formatCpfNumber(String cpfSent)
    {
        cpfSent = cpfSent.replaceAll("[^0-9]", "");

        return String.format("%s%s%s.%s%s%s.%s%s%s-%s%s",
                cpfSent.charAt(0), cpfSent.charAt(1), cpfSent.charAt(2),
                cpfSent.charAt(3), cpfSent.charAt(4), cpfSent.charAt(5),
                cpfSent.charAt(6), cpfSent.charAt(7), cpfSent.charAt(8),
                cpfSent.charAt(9), cpfSent.charAt(10));
    }
}
