package br.com.petshop.manipulacao;

import br.com.petshop.moldes.cliente.Login;

import java.util.ArrayList;
import java.util.Scanner;

import static br.com.petshop.manipulacao.UsernameValidator.isValidLogin;
import static br.com.petshop.manipulacao.UsernameValidator.isValidSenha;

public class LoginManipulacao {
    public boolean cadastrarNovoLogin(Scanner scan, ArrayList<Login> logins){
        System.out.println("Insira o login desejado\n[9 para sair]");
        String loginDesejado = scan.nextLine();
        if(loginDesejado.equals("9")){
            System.out.println("Voltando ao menu principal");
            return false;
        }
        while ((!isValidLogin(loginDesejado) || loginJaExiste(logins, loginDesejado))){
            if(loginDesejado.equals("9")){
                System.out.println("Voltando ao menu principal");
                return false;
            }
            System.out.println("Login inválido ou já existente, tente outro por favor\n[9 para sair]");
            loginDesejado = scan.nextLine();
        }
        System.out.println("Insira a senha desejada, apenas números\n[9 para sair]");
        String senhaDesejada = scan.nextLine();
        while (!isValidSenha(senhaDesejada)){
            if(senhaDesejada.equals("9")){
                System.out.println("Voltando ao menu principal");
                return false;
            }
            System.out.println("Senha inválida, insira uma senha numérica com 6 a 8 dígitos\n[9 para sair]");
            senhaDesejada = scan.nextLine();
        }
        logins.add(new Login(loginDesejado, senhaDesejada));
        System.out.println("Login e senha válidos, vamos agora preencher seus dados e dos seus pets\n=============================");
        return true;
    }
    public Login verificarLogin(Scanner scan, ArrayList<Login> logins) {

        System.out.println("Insira o login\n[ou -1 para voltar ao menu anterior]");
        String loginEntrada = scan.nextLine();
        while (!isValidLogin(loginEntrada)){
            System.out.println("Login inválido, insira novamente");
            loginEntrada = scan.nextLine();
            if(loginEntrada.equals("-1")){
                return null;
            }
        }
        System.out.println("Insira sua senha numerica\n[ou -1 para voltar ao menu anterior]");
        String senha = scan.nextLine();
        while (!isValidSenha(senha)){
            System.out.println("Senha inválida, insira uma senha numérica com 6 a 8 dígitos");
            senha = scan.nextLine();
            if(senha.equals("-1")){
                return null;
            }
        }
        final String verifyStringLog = loginEntrada;
        final String verifyStringPass = senha;
        boolean loginEhValido = logins.stream().anyMatch(login -> login.getLogin().equals(verifyStringLog) && login.getSenha().equals(verifyStringPass));
        if(loginEhValido) {
            System.out.println("Logado com sucesso!");

            return new Login(verifyStringLog, verifyStringPass);
        }
        System.out.println("Senha incorreta ou usuário inexistente, voltando ao menu principal!");
        return null;
    }

    private boolean loginJaExiste(ArrayList<Login> logins, String loginDesejado) {
        return logins.stream().anyMatch(login -> login.getLogin().equals(loginDesejado));
    }

    public void telaInicialPosLogin() {
        System.out.println("[USUARIO LOGADO]");
        System.out.println("Insira o que deseja fazer\n1- Para operacoes de CONTA.\n2- Para operacoes de PET.\n0- Para sair.");
    }
    public void selecionarContratosDeServico() {

    }
}
