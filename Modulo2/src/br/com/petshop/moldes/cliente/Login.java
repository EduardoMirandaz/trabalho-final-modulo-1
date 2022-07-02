package br.com.petshop.moldes.cliente;

import br.com.petshop.moldes.cliente.Cliente;

public class Login {
    private String login;
    private String senha;
    private Cliente cliente;

    @Override
    public String toString() {
        return "Login{" +
                "login='" + login + '\'' +
                ", senha='" + senha + '\'' +
                '}';
    }

    public Login(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
