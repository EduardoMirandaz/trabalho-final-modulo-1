package br.com.petshop.repository;

import br.com.petshop.exceptions.BancoDeDadosException;
import br.com.petshop.model.cliente.Cliente;
import br.com.petshop.model.cliente.Contato;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContatoRepository implements Repositorio <Integer, Contato> {
@Override
    public Integer getProximoId(Connection connection) throws BancoDeDadosException {
            try {
            String sql = "SELECT seq_id_contato.nextval seqContato from DUAL";
            Statement stmt = connection.createStatement();
            ResultSet res = stmt.executeQuery(sql);

            if (res.next()) {
            return res.getInt("seqContato");
            }

            return null;
            } catch (SQLException e) {
            throw new BancoDeDadosException(e.getCause());
            }
            }

    @Override
    public Contato adicionar(Contato contato) throws BancoDeDadosException {
        Connection con = null;
        try {
                con = ConexaoBancoDeDados.getConnection();

                Integer proximoId = this.getProximoId(con);
                contato.setIdContato(proximoId);

                String sql = "INSERT INTO CONTATO\n" +
                "(ID_CONTATO, ID_CLIENTE, TELEFONE, DESCRICAO)\n" +
                "VALUES(?, ?, ?, ?)\n";

                PreparedStatement stmt = con.prepareStatement(sql);

                stmt.setInt(1, contato.getIdContato());
                stmt.setInt(2, contato.getCliente().getId());
                stmt.setInt(3, contato.getTelefone());
                stmt.setString(4, contato.getDescricao());

                int res = stmt.executeUpdate();
                System.out.println("adicionarContato.res=" + res);
                return contato;
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

            String sql = "DELETE FROM CONTATO WHERE ID_CONTATO = ?";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, id);

            // Executa-se a consulta
            int res = stmt.executeUpdate();
            System.out.println("removerContatoPorId.res=" + res);

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
    public boolean editar(Integer id, Contato contato) throws BancoDeDadosException {
        Connection con = null;

        try {
            con = ConexaoBancoDeDados.getConnection();

            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE contato SET \n");
            Cliente cliente = contato.getCliente();

            if (cliente != null) {
                if (cliente.getId() != null) {
                    sql.append(" id_pessoa = ?,");
                }
            }

            if (contato.getTelefone() != null) {
                sql.append(" numero = ?,");
            }

            if (contato.getDescricao() != null) {
                sql.append(" descricao = ?,");
            }

            sql.deleteCharAt(sql.length() - 1); //remove o ultimo ','
            sql.append(" WHERE id_contato = ? ");

            PreparedStatement stmt = con.prepareStatement(sql.toString());

            int index = 1;
            if (cliente != null) {
                if (cliente.getId() != null) {
                    stmt.setInt(index++, cliente.getId());
                }
            }
            if (contato.getTelefone() != null) {
                stmt.setInt(index++, contato.getTelefone());
            }
            if (contato.getDescricao() != null) {
                stmt.setString(index++, contato.getDescricao());
            }

            stmt.setInt(index++, id);

            // Executa-se a consulta
            int res = stmt.executeUpdate();
            System.out.println("editarContato.res=" + res);

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
    public List<Contato> listar() throws BancoDeDadosException {
        List<Contato> contatos = new ArrayList<>();
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();
            Statement stmt = con.createStatement();

            String sql = "SELECT CTT.*, " +
            "                    C.NOME AS NOME_CLIENTE " +
            "               FROM CONTATO CTT " +
            "               LEFT JOIN CLIENTE C ON (C.ID_CLIENTE = CTT.ID_CLIENTE) ";

            // Executa-se a consulta
            ResultSet res = stmt.executeQuery(sql);

            while (res.next()) {
            Contato contato = getContatoFromResultSet(res);
            contatos.add(contato);
            }
            return contatos;
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

    public List<Contato> listarContatosPorCliente(Integer idCliente) throws BancoDeDadosException {
            List<Contato> contatos = new ArrayList<>();
            Connection con = null;
            try {
                con = ConexaoBancoDeDados.getConnection();


                String sql = "SELECT ctt.* " +
                "                  , ctt.NOME AS NOME_PESSOA " +
                "               FROM CONTATO ctt " +
                "             INNER JOIN CLIENTE c ON (c.ID_CLIENTE = ctt.ID_CLIENTE) " +
                "             WHERE ctt.ID_CLIENTE = ? ";

            // Executa-se a consulta
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setInt(1, idCliente);

                ResultSet res = stmt.executeQuery();

                while (res.next()) {
                    Contato contato = getContatoFromResultSet(res);
                    contatos.add(contato);
                }
                return contatos;
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

    private Contato getContatoFromResultSet(ResultSet res) throws SQLException {
            Contato contato = new Contato();
            contato.setIdContato(res.getInt("id_Contato"));
            Cliente cliente = new Cliente();
            cliente.setNome(res.getString("nome_pessoa"));
            cliente.setId(res.getInt("id_pessoa"));
            contato.setCliente(cliente);
            contato.setTelefone(res.getInt("telefone"));
            contato.setDescricao(res.getString("descricao"));
            return contato;
    }
}
