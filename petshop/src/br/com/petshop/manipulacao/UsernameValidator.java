package br.com.petshop.manipulacao;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UsernameValidator {

    // simple regex
    //private static final String USERNAME_PATTERN = "^[a-z0-9\\._-]{5,20}$";

    // strict regex
    private static final String USERNAME_PATTERN =
            "^[a-zA-Z\\d]([._-](?![._-])|[a-zA-Z\\d]){3,18}[a-zA-Z\\d]$";
    private static final String PASSWORD_PATTERN =
            "^\\d+$";
    private static final String NAME_PATTERN =
            "^[a-zA-Z ]+$";

    private static final String CEP_PATTERN =
            "^\\d+$";
    private static final Pattern loginPattern = Pattern.compile(USERNAME_PATTERN);
    private static final Pattern passwordPattern = Pattern.compile(PASSWORD_PATTERN);

    private static final Pattern namePattern = Pattern.compile(NAME_PATTERN);

    private static final Pattern cepPattern = Pattern.compile(CEP_PATTERN);

    public static boolean isValidLogin(final String login) {
        Matcher matcher = loginPattern.matcher(login);
        return matcher.matches();
    }
    public static boolean isValidSenha(final String senha) {
        Matcher matcher = passwordPattern.matcher(senha);
        return matcher.matches();
    }

    public static boolean isValidName(final String senha) {
        Matcher matcher = namePattern.matcher(senha);
        return matcher.matches();
    }

    public static boolean isValidCEP(final String cep) {
        Matcher matcher = namePattern.matcher(cep);
        return matcher.matches();
    }

}