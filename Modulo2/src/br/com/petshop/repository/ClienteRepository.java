package br.com.petshop.repository;

import br.com.petshop.exceptions.BancoDeDadosException;
import br.com.petshop.moldes.cliente.Cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteRepository implements Repositorio<Integer, Cliente> {
    @Override
    public Integer getProximoId(Connection connection) throws SQLException {
        String sql = "SELECT SEQ_ID_CLIENTE.nextval SEQ_ID_CLIENTE from DUAL";

        Statement stmt = connection.createStatement();
        ResultSet res = stmt.executeQuery(sql);

        if (res.next()) {
            return res.getInt("SEQ_ID_CLIENTE");
        }

        return null;
    }

    @Override
    public Cliente adicionar(Cliente cliente) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();

            cliente.setId(this.getProximoId(con));

            String sql = "INSERT INTO CLIENTE\n" +
                    "(ID_CLIENTE, NOME, QUANTIDADE_PEDIDOS)\n" +
                    "VALUES(?, ?, ?)\n";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, cliente.getId());
            stmt.setString(2, cliente.getNome());
            stmt.setInt(3, 14);

            int res = stmt.executeUpdate();
            System.out.println("adicionarPessoa.res=" + res);
            return cliente;
        } catch (SQLException e) {
            throw new BancoDeDadosException(e.getCause());
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean remover(Integer id) throws BancoDeDadosException {
        return true;
//        Connection con = null;
//        try {
//            con = ConexaoBancoDeDados.getConnection();
//
//            String sql = "DELETE FROM PESSOA WHERE id_pessoa = ?";
//
//            PreparedStatement stmt = con.prepareStatement(sql);
//
//            stmt.setInt(1, id);
//
//            // Executa-se a consulta
//            int res = stmt.executeUpdate();
//            System.out.println("removerPessoaPorId.res=" + res);
//
//            return res > 0;
//        } catch (SQLException e) {
//            throw new BancoDeDadosException(e.getCause());
//        } finally {
//            try {
//                if (con != null) {
//                    con.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
    }

    @Override
    public boolean editar(Integer id, Cliente cliente) throws BancoDeDadosException {
        return false;
    }


    @Override
    public List<Cliente> listar() throws BancoDeDadosException {

        List<Cliente> clientes = new ArrayList<>();
        return clientes;
//        Connection con = null;
//        try {
//            con = ConexaoBancoDeDados.getConnection();
//            Statement stmt = con.createStatement();
//
//            String sql = "SELECT * FROM PESSOA";
//
//            // Executa-se a consulta
//            ResultSet res = stmt.executeQuery(sql);
//
//            while (res.next()) {
//                Pessoa pessoa = new Pessoa();
//                pessoa.setIdPessoa(res.getInt("id_pessoa"));
//                pessoa.setNome(res.getString("nome"));
//                pessoa.setDataNascimento(res.getDate("data_nascimento").toLocalDate());
//                pessoa.setCpf(res.getString("cpf"));
//                pessoas.add(pessoa);
//            }
//        } catch (SQLException e) {
//            throw new BancoDeDadosException(e.getCause());
//        } finally {
//            try {
//                if (con != null) {
//                    con.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        return pessoas;
    }
}
