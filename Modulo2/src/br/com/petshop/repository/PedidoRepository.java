package br.com.petshop.repository;

import br.com.petshop.exceptions.BancoDeDadosException;
import br.com.petshop.moldes.cliente.Cliente;
import br.com.petshop.moldes.cliente.Pedido;
import br.com.petshop.moldes.pets.Animal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PedidoRepository implements Repositorio<Integer, Pedido>{
    @Override
    public Integer getProximoId(Connection connection) throws BancoDeDadosException {
        try {
            String sql = "SELECT seq_id_pedido.nextval seqPedido from DUAL";
            Statement stmt = connection.createStatement();
            ResultSet res = stmt.executeQuery(sql);

            if(res.next()) {
                return res.getInt("seqPedido");
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

            String sql  = """
                    INSERT INTO PEDIDO
                    (ID_PEDIDO, ID_CLIENTE, ID_ANIMAL, VALOR, DESCRICAO)
                    VALUES(?, ?, ?, ?, ?)
                    """;

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
            if(res > 0){
                System.out.println("Pedido removido com sucesso!");
            }
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

    @Override
    public boolean editar(Integer id, Pedido pedido) throws  BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();

            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE pedido SET \n");
            Animal animal = pedido.getAnimal();
            if (animal != null) {
                if(animal.getIdAnimal() != null) {
                    sql.append(" id_animal = ?,");
                }
            }
            if(pedido.getValor() != null) {
                sql.append(" valor = ?,");
            }
            if(pedido.getDescricao() != null) {
                sql.append(" descricao = ?,");
            }

            sql.deleteCharAt(sql.length() -1);
            sql.append("WHERE id_pedido = ? ");

            PreparedStatement  stmt = con.prepareStatement(sql.toString());

            int index = 1;
            if(animal != null) {
                if(animal.getIdAnimal() != null) {
                    stmt.setInt(index++, animal.getIdAnimal());
                }
            }
            if(pedido.getValor() != null) {
                stmt.setDouble(index++, pedido.getValor());
            }
            if(pedido.getDescricao() != null) {
                stmt.setString(index++, pedido.getDescricao());
            }

            stmt.setInt(index++, id);

            int res = stmt.executeUpdate();
            if(res > 0){
                System.out.println("Pedido alterado com sucesso!!");
            }

            return res>0;
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
    public List<Pedido> listar() throws BancoDeDadosException {
        List<Pedido> pedidos = new ArrayList<>();
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();
            Statement stmt = con.createStatement();

            String sql = "SELECT P.*" +
                    "          , C.NOME AS NOME_CLIENTE " +
                    "       FROM PEDIDO P " +
                    "       LEFT JOIN CLIENTE C ON (C.ID_CLIENTE = P.ID_CLIENTE) ";

            ResultSet res = stmt.executeQuery(sql);

            while (res.next()) {
                Pedido pedido = getPedidoFromResultSet(res);
                pedidos.add(pedido);
            }
            return pedidos;
        } catch (SQLException e) {
            throw new BancoDeDadosException(e.getCause());
        } finally {
            try{
                if(con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Pedido> listarPedidosPorCliente(Cliente cliente) throws BancoDeDadosException {
        List<Pedido> pedidos = new ArrayList<>();
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();
            String sql = "SELECT P.*" +
                    "          , C.NOME AS NOME_CLIENTE " +
                    "       FROM PEDIDO P " +
                    "      INNER JOIN CLIENTE C ON (C.ID_CLIENTE = P.ID_CLIENTE) " +
                    "      WHERE P.ID_CLIENTE = ? ";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, cliente.getId());

            ResultSet res = stmt.executeQuery();
            AnimalRepository animalRepository = new AnimalRepository();
            while(res.next()) {
                Pedido pedido = getPedidoFromResultSet(res);
                pedido.setCliente(cliente);
                pedido.setAnimal(animalRepository.getAnimalPorId(pedido.getIdAnimal(), cliente.getId()));
                pedidos.add(pedido);
            }
            return pedidos;
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

    public Pedido getPedidoPorId(int idPedido) throws BancoDeDadosException {
        Pedido pedido = null;
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();
            String sql = """
                                SELECT p.*
                                FROM PEDIDO p
                                WHERE p.ID_PEDIDO = ?
                    """;

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, idPedido);

            ResultSet res = stmt.executeQuery();

            if(res.next()) {
                pedido = getPedidoFromResultSet(res);
            }
            return pedido;
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

    private Pedido getPedidoFromResultSet(ResultSet res) throws  SQLException {
        Pedido pedido = new Pedido();
        pedido.setIdPedido(res.getInt("id_pedido"));
        pedido.setValor(res.getDouble("valor"));
        pedido.setDescricao(res.getString("descricao"));
        pedido.setIdAnimal(res.getInt("id_animal"));
        return pedido;
    }




}
