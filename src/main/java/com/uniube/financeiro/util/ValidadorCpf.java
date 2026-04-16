package com.uniube.financeiro.util;

public class ValidadorCpf {
    public static boolean isValid(String cpf) {
        if (cpf == null || cpf.length() != 11 || !cpf.matches("\\d{11}")) {
            return false;
        }

        int sum1 = 0, sum2 = 0;
        for (int i = 0; i < 9; i++) {
            sum1 += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
            sum2 += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
        }
        int digit1 = 11 - (sum1 % 11);
        digit1 = (digit1 >= 10) ? 0 : digit1;
        sum2 += digit1 * 2;
        int digit2 = 11 - (sum2 % 11);
        digit2 = (digit2 >= 10) ? 0 : digit2;
        return digit1 == Character.getNumericValue(cpf.charAt(9)) && digit2 == Character.getNumericValue(cpf.charAt(10));
    }
}

