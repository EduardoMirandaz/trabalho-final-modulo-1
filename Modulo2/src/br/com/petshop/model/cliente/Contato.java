package br.com.petshop.model.cliente;

public class Contato {

    private Integer idContato;
    private Cliente cliente;
    private Integer telefone;
    private String descricao;

    @Override
    public String toString() {
        return "Contato{" +
                ", idContato=" + idContato +
                ", idCliente=" + (cliente != null ? cliente.getId() : null) +
                ", nomeCliente=" + (cliente != null ? cliente.getNome() : null) +
                ", telefone=" + telefone +
                ", descricao=" + descricao +
                "}";
    }

    public Integer getIdContato() {
        return idContato;
    }
    public void setIdContato(Integer idContato) {
        this.idContato = idContato;
    }
    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getTelefone() {
        return telefone;
    }

    public void setTelefone(Integer telefone) {
        this.telefone = telefone;
    }
}
