package br.com.petshop.repository;

import br.com.petshop.exceptions.BancoDeDadosException;
import br.com.petshop.moldes.cliente.Cliente;
import br.com.petshop.moldes.cliente.Pedido;

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
            stmt.setInt(3, cliente.getQuantidadeDePedidos());

            if(stmt.executeUpdate() != 0){
                System.out.println("Cliente adicionado com sucesso");
                return cliente;
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
        return null;
    }

    @Override
    public boolean remover(Integer id) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();

            String sql = "DELETE FROM CLIENTE WHERE ID_CLIENTE = ?";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, id);

            // Executa-se a consulta
            if(stmt.executeUpdate() > 0){
                System.out.println("Cliente removido com sucesso");
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
    public boolean editar(Integer id, Cliente cliente) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();

            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE contato SET \n");
            if (cliente != null) {
                if (cliente.getId() != null) {
                    sql.append(" id_cliente = ?,");
                }
            }
            assert cliente != null;
            if (cliente.getNome() != null) {
                sql.append(" nome = ?,");
            }
            if (cliente.getQuantidadeDePedidos() != null) {
                sql.append(" descricao = ?,");
            }
            sql.deleteCharAt(sql.length() - 1); //remove o ultimo ','
            sql.append(" WHERE id_contato = ? ");

            PreparedStatement stmt = con.prepareStatement(sql.toString());

            int index = 1;
            if (cliente.getId() != null) {
                stmt.setInt(index++, cliente.getId());
            }
            if (cliente.getNome() != null) {
                stmt.setString(index++, cliente.getNome());
            }
            if (cliente.getQuantidadeDePedidos() != null) {
                stmt.setInt(index, cliente.getQuantidadeDePedidos());
            }

            // Executa-se a consulta
            if(stmt.executeUpdate() > 0){
                System.out.println("Cliente editado com sucesso");
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
    public List<Cliente> listar() throws BancoDeDadosException {

        List<Cliente> clientes = new ArrayList<>();
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();
            Statement stmt = con.createStatement();

            String sql = "SELECT * FROM PESSOA";

            // Executa-se a consulta
            ResultSet res = stmt.executeQuery(sql);

            while (res.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(res.getInt("ID_CLIENTE"));
                cliente.setNome(res.getString("NOME"));
                cliente.setId(res.getInt("QUANTIDADE_PEDIDOS"));
                clientes.add(cliente);
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
        return clientes;
    }

    public int incrementarQuantidadeDePedidosNoBanco(int idCliente) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();
            String sql = """
                                SELECT c.QUANTIDADE_PEDIDOS
                                FROM CLIENTE c
                                WHERE c.ID_CLIENTE = ?
                    """;

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, idCliente);
            ResultSet res = stmt.executeQuery();
            return res.getInt("QUANTIDADE_PEDIDOS");
        } catch (SQLException e) {
            throw new BancoDeDadosException(e.getCause());
        } finally {
            try {
                if( con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void setPedidosBanco(int idCliente, int novaQuantidade) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();

            String sql = """
                    UPDATE CLIENTE
                    SET QUANTIDADE_PEDIDOS = ?
                    WHERE ID_CLIENTE = ?
                    """;

            PreparedStatement stmt = con.prepareStatement(sql);

            int index = 1;
            stmt.setInt(index++, novaQuantidade);
            stmt.setInt(index, idCliente);

            // Executa-se a consulta
            if(stmt.executeUpdate() > 0){
                System.out.println("Cliente editado com sucesso");
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
    }

    public Cliente getClientePeloId(Integer id) throws BancoDeDadosException {
        Cliente cliente = null;
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();
            String sql = """
                            SELECT c.*
                            FROM CLIENTE c
                            WHERE c.ID_CLIENTE = ?
                """;

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);

            ResultSet res = stmt.executeQuery();

            if(res.next()) {
                cliente = getClienteFromResultSet(res);
            }
            return cliente;
        } catch (SQLException e) {
            throw new BancoDeDadosException(e.getCause());
        } finally {
            try {
                if( con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private Cliente getClienteFromResultSet(ResultSet res) throws  SQLException {
        Cliente cliente = new Cliente();
        cliente.setId(res.getInt("id_cliente"));
        cliente.setNome(res.getString("nome"));
        cliente.setQuantidadeDePedidos(res.getInt("quantidade_pedidos"));
        return cliente;
    }
}

