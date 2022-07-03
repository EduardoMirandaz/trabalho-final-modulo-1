package br.com.petshop.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UsernameValidator {

    // simple regex
    //private static final String USERNAME_PATTERN = "^[a-z0-9\\._-]{5,20}$";

    // strict regex
    private static final String USERNAME_PATTERN =
            "^[a-zA-Z\\d]([._-](?![._-])|[a-zA-Z\\d]){3,18}[a-zA-Z\\d]$";
    private static final String PASSWORD_PATTERN =
            "^\\d{6,8}+$";
    private static final String NAME_PATTERN =
            "^[a-zA-Z ]+$";

    private static final String CEP_PATTERN =
            "^[\\d]{5}-[\\d]{3}+$";

    private static final String ONLY_NUMBERS_PATTERN =
            "^[\\d]{0,5}+$";

    private static final String FONE_NUMBER_PATTERN =
            "^[\\d]{2} [\\d]{8,9}+$";

    private static final String DIGIT_NUMBER_PATTERN =
            "^[\\d]{1}+$";

    private static final Pattern loginPattern = Pattern.compile(USERNAME_PATTERN);
    private static final Pattern passwordPattern = Pattern.compile(PASSWORD_PATTERN);

    private static final Pattern namePattern = Pattern.compile(NAME_PATTERN);

    private static final Pattern cepPattern = Pattern.compile(CEP_PATTERN);

    private static final Pattern onlyNumbersPattern = Pattern.compile(ONLY_NUMBERS_PATTERN);

    private static final Pattern foneNumberPattern = Pattern.compile(FONE_NUMBER_PATTERN);

    private static final Pattern digitNumberPattern = Pattern.compile(DIGIT_NUMBER_PATTERN);


    public static boolean isValidLogin(final String login) {
        Matcher matcher = loginPattern.matcher(login);
        return matcher.matches();
    }
    public static boolean isValidSenha(final String senha) {
        Matcher matcher = passwordPattern.matcher(senha);
        return matcher.matches();
    }

    public static boolean isValidName(final String nome) {
        Matcher matcher = namePattern.matcher(nome);
        return matcher.matches();
    }

    public static boolean isValidCEP(final String cep) {
        Matcher matcher = cepPattern.matcher(cep);
        return matcher.matches();
    }

    public static boolean isValidNUM(final String num) {
        Matcher matcher = onlyNumbersPattern.matcher(num);
        return matcher.matches();
    }

    public static boolean isValidFONENUMBER(final String num) {
        Matcher matcher = foneNumberPattern.matcher(num);
        return matcher.matches();
    }

    public static boolean isValidDigit(final String num) {
        Matcher matcher = digitNumberPattern.matcher(num);
        return matcher.matches();
    }
}