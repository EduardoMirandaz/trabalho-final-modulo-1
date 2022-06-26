package br.com.petshop.manipulacao;

import br.com.petshop.moldes.cliente.Login;

import java.util.ArrayList;
import java.util.Scanner;

import static br.com.petshop.manipulacao.UsernameValidator.isValidLogin;
import static br.com.petshop.manipulacao.UsernameValidator.isValidSenha;

public class LoginManipulacao {
    public boolean cadastrarNovoLogin(Scanner scan, ArrayList<Login> logins){
        System.out.println("Insira o login desejado");
        String loginDesejado = scan.nextLine();
        while (!isValidLogin(loginDesejado) || loginJaExiste(logins, loginDesejado)){
            System.out.println("Login inválido ou já existente, tente outro por favor");
            loginDesejado = scan.nextLine();
        }
        System.out.println("Insira a senha desejada, apenas números");
        String senhaDesejada = scan.nextLine();
        while (!isValidSenha(senhaDesejada)){
            System.out.println("Senha inválida, insira uma senha numérica com 6 a 8 dígitos");
            senhaDesejada = scan.nextLine();
        }
        logins.add(new Login(loginDesejado, senhaDesejada));
        System.out.println("Login e senha válidos, vamos agora preencher seus dados e dos seus bixinhos\n=============================");
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

    public void telaInicialLogin() {
        System.out.println("[USUARIO LOGADO]");
        System.out.println("""
                Insira o que deseja fazer
                1 - Para adicionar novo pet.
                2 - Para editar um pet existente.
                3 - Para remover um pet existente.
                4 - Para listar seus pets.
                5 - Para contratar servicos.
                -1 - Para sair""");

    }
    public void selecionarContratosDeServico() {

    }
}
