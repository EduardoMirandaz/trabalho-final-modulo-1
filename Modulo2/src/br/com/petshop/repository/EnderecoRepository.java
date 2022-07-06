package br.com.petshop.repository;

import br.com.petshop.exceptions.BancoDeDadosException;
import br.com.petshop.model.cliente.Cliente;
import br.com.petshop.model.cliente.Endereco;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnderecoRepository implements Repositorio <Integer, Endereco> {

    @Override
    public Integer getProximoId(Connection connection) throws SQLException {
        try {
            String sql = "SELECT SEQ_ID_ENDERECO.nextval SEQ_ID_ENDERECO from DUAL";

            Statement stmt = connection.createStatement();
            ResultSet res = stmt.executeQuery(sql);

            if (res.next()) {
                return res.getInt("SEQ_ID_ENDERECO");
            }
            return null;
        } catch (SQLException e) {
            throw new BancoDeDadosException(e.getCause());
        }
    }

    @Override
    public Endereco adicionar(Endereco endereco) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();

            Integer proximoId = this.getProximoId(con);
            endereco.setIdEndereco(proximoId);

            String sql = "INSERT INTO ENDERECO\n" +
                    "(ID_ENDERECO, ID_CLIENTE, CEP, LOGRADOURO, CIDADE, BAIRRO, NUMERO, COMPLEMENTO)\n" +
                    "VALUES(?, ?, ?, ?, ?, ?, ?, ?)\n";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, endereco.getIdEndereco());
            stmt.setInt(2, endereco.getCliente().getId());
            stmt.setString(3, endereco.getCep());
            stmt.setString(4, endereco.getLogradouro());
            stmt.setString(5, endereco.getCidade());
            stmt.setString(6, endereco.getBairro());
            stmt.setInt(7, endereco.getNumero());
            stmt.setString(8, endereco.getComplemento());

            return endereco;
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
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();

            String sql = "DELETE FROM ENDERECO WHERE ID_ENDERECO = ?";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, id);

            if (stmt.executeUpdate() > 0) {
                System.out.println("Endereco Removido");
                return true;
            }
            return false;
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
    public boolean editar(Integer id, Endereco endereco) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();

            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE endereco SET \n");
            if (endereco != null) {
                if (endereco.getIdEndereco() != null) {
                    sql.append("ID_ENDERECO = ?,");
                }
            }
            if (endereco.getCep() != null) {
                sql.append("CEP = ?,");
            }
            if (endereco.getLogradouro() != null) {
                sql.append("LOGRADOURO = ?,");
            }
            if (endereco.getCidade() != null) {
                sql.append("CIDADE = ?,");
            }
            if (endereco.getBairro() != null) {
                sql.append("BAIRRO = ?,");
            }
            if (endereco.getNumero() != null) {
                sql.append("NUMERO = ?,");
            }
            if (endereco.getComplemento() != null) {
                sql.append("COMPLEMENTO = ?,");
            }
            sql.deleteCharAt(sql.length() - 1);
            sql.append("WHERE ID_ENDERECO = ?");

            PreparedStatement stmt = con.prepareStatement(sql.toString());

            int index = 1;
            if (endereco.getIdEndereco() != null) {
                stmt.setInt(index++, endereco.getIdEndereco());
            }
            if (endereco.getCep() != null) {
                stmt.setString(index++, endereco.getCep());
            }
            if (endereco.getLogradouro() != null) {
                stmt.setString(index++, endereco.getLogradouro());
            }
            if (endereco.getCidade() != null) {
                stmt.setString(index++, endereco.getCidade());
            }
            if (endereco.getBairro() != null) {
                stmt.setString(index++, endereco.getBairro());
            }
            if (endereco.getNumero() != null) {
                stmt.setInt(index++, endereco.getNumero());
            }
            if (endereco.getComplemento() != null) {
                stmt.setString(index++, endereco.getComplemento());
            }

            if (stmt.executeUpdate() > 0) {
                System.out.println("Endereco editado com sucesso");
                return true;
            }

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
        return false;
    }
    @Override
    public List<Endereco> listar() throws BancoDeDadosException {

        List<Endereco> enderecos = new ArrayList<>();
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();
            Statement stmt = con.createStatement();

            String sql = "SELECT e.*,\n" +
                    "            c.NOME  \n" +
                    "       FROM ENDERECO e \n" +
                    " INNER JOIN CLIENTE c ON (c.ID_CLIENTE = e.ID_CLIENTE)\n" +
                    "      WHERE e.ID_CLIENTE = ? ";


            ResultSet res = stmt.executeQuery(sql);

            while (res.next()) {
                Endereco endereco = getResultadoDosEnderecoSet(res);
                enderecos.add(endereco);
            }
            return enderecos;
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

    public List<Endereco> listarEnderecoPorCliente(Integer idCliente) throws BancoDeDadosException {
        List<Endereco> enderecos = new ArrayList<>();
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();


            String sql = "SELECT e.* " +
                    "                  , c.NOME AS NOME_PESSOA " +
                    "               FROM ENDERECO e " +
                    "             INNER JOIN CLIENTE c ON (c.ID_CLIENTE = e.ID_CLIENTE) " +
                    "             WHERE e.ID_CLIENTE = ? ";

            // Executa-se a consulta
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, idCliente);

            ResultSet res = stmt.executeQuery();

            while (res.next()) {
                Endereco endereco = getResultadoDosEnderecoSet(res);
                enderecos.add(endereco);
            }
            return enderecos;
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

    private Endereco getResultadoDosEnderecoSet(ResultSet res) throws SQLException {
        Endereco endereco = new Endereco();
        endereco.setIdEndereco(res.getInt("ID_ENDERECO"));
        Cliente cliente = new Cliente();
        cliente.setNome(res.getString("NOME"));
        cliente.setId(res.getInt("ID_CLIENTE"));
        endereco.setCliente(cliente);
        endereco.setCep(res.getString("CEP"));
        endereco.setLogradouro(res.getString("LOGRADOURO"));
        endereco.setCidade(res.getString("CIDADE"));
        endereco.setBairro(res.getString("BAIRRO"));
        endereco.setNumero(res.getInt("NUMERO"));
        endereco.setComplemento(res.getString("COMPLEMENTO"));
        return endereco;
    }
}


