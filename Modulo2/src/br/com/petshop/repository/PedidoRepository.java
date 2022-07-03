package br.com.petshop.repository;

import br.com.petshop.exceptions.BancoDeDadosException;
import br.com.petshop.moldes.cliente.Cliente;
import br.com.petshop.moldes.cliente.Pedido;

import java.sql.*;

public class PedidoRepository implements Repositorio<Integer, Pedido>{
    @Override
    public Integer getProximoId(Connection connection) throws BancoDeDadosException {
        try {
            String sql = "SELECT seq_pedido.nextval seqPedido from DUAL";
            Statement stmt = connection.createStatement();
            ResultSet res = stmt.executeQuery(sql);

            if(res.next()) {
                return res.getInt("seqAnimal");
            }

            return null;
        } catch (SQLException e) {
            throw new BancoDeDadosException(e.getCause());
        }
    }

    @Override
    public Pedido adicionar(Pedido pedido) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();

            Integer proximoId = this.getProximoId(con);
            pedido.setIdPedido(proximoId);

            String sql  = "INSERT INTO PEDIDO\n" +
                    "(ID_PEDIDO, ID_CLIENTE, ID_ANIMAL, VALOR, DESCRICAO)\n" +
                    "VALUES(?, ?, ?, ?, ?)\n";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, pedido.getIdPedido());
            stmt.setInt(2, pedido.getCliente().getId());
            stmt.setInt(3, pedido.getAnimal().getIdAnimal());
            stmt.setDouble(4, pedido.getValor());
            stmt.setString(5, pedido.getDescricao());

            int res = stmt.executeUpdate();
            System.out.println("adicionarPedido=" + res);
            return pedido;
        } catch (SQLException e) {
            throw new BancoDeDadosException(e.getCause());
        } finally {
            try {
                if(con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean remover(Integer id) throws  BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();

            String sql = "DELETE FROM PEDIDO WHERE ID_PEDIDO = ?";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, id);

            int res = stmt.executeUpdate();
            System.out.println("removerPedidoPorId.res=" + res);

            return res > 0;
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

//    @Override
//    public boolean editar(Integer id, Pedido pedido) throws  BancoDeDadosException {
//        Connection con = null;
//        try {
//            con = ConexaoBancoDeDados.getConnection();
//
//            StringBuilder sql = new StringBuilder();
//            sql.append("UPDATE pedido SET \n");
//            if (pedido.)
//        }
//    }

}
