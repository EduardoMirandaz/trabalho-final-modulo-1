package br.com.petshop.repository;

import br.com.petshop.exceptions.BancoDeDadosException;
import br.com.petshop.moldes.cliente.Cliente;
import br.com.petshop.moldes.pets.Animal;
import br.com.petshop.moldes.pets.EnumTipoAnimal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnimalRepository implements Repositorio<Integer, Animal>{
    @Override
    public Integer getProximoId(Connection connection) throws BancoDeDadosException {
        try {
            String sql = "SELECT SEQ_ID_ANIMAL.nextval SEQ_ID_ANIMAL from DUAL";
            Statement stmt = connection.createStatement();
            ResultSet res = stmt.executeQuery(sql);

            if(res.next()) {
                return res.getInt("SEQ_ID_ANIMAL");
            }

            return null;
        }catch (SQLException e) {
            throw new BancoDeDadosException(e.getCause());
        }
    }

    public Animal adicionar(Animal animal) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();

            Integer proximoId = this.getProximoId(con);
            animal.setIdAnimal(proximoId);

            String sql = """
                    INSERT INTO ANIMAL\s
                    (ID_ANIMAL, ID_CLIENTE, NOME, TIPO, RACA, PELAGEM, PORTE, IDADE)
                    VALUES (?, ?, ?, ?, ?, ?, ?, ?)
                    """;

            if(animal.getIdAnimal() == null
            || animal.getCliente().getId() == null
            || animal.getNome() == null
            || animal.getTipoAnimal() == null
            || animal.getTipoAnimal().getTipo() == null
            || animal.getPelagem() == null
            || animal.getRaca() == null
            || animal.getIdade() == null
            || animal.getPorte() == null)
            {
                return null;
            }

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, animal.getIdAnimal());
            stmt.setInt(2, animal.getCliente().getId());
            stmt.setString(3, animal.getNome());
            stmt.setString(4, animal.getTipoAnimal().toString());
            stmt.setString(5, animal.getRaca());
            stmt.setInt(6, animal.getPelagem());
            stmt.setInt(7, animal.getPorte());
            stmt.setInt(8, animal.getIdade());

            int res = stmt.executeUpdate();
            System.out.println("adicionarAnimal.res=" + res);
            return animal;
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

    @Override
    public boolean remover(Integer id) throws  BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();

            String sql = "DELETE FROM ANIMAL WHERE ID_ANIMAL = ?";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, id);

            int res = stmt.executeUpdate();
            System.out.println("removerAnimalPorId.res=" + res);

            return res>0;
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
    public boolean editar(Integer id, Animal animal) throws BancoDeDadosException {
        Connection con = null;

        try {
            con = ConexaoBancoDeDados.getConnection();

            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE animal SET \n");
            if(animal.getNome() != null) {
                sql.append("nome = ?,");
            }
            if(animal.getTipoAnimal() != null) {
                sql.append("tipo = ?,");
            }
            if(animal.getRaca() != null) {
                sql.append("raca = ?,");
            }
            if(animal.getPelagem() != null) {
                sql.append("pelagem = ?,");
            }
            if(animal.getPorte() != null) {
                sql.append("porte = ?,");
            }
            if(animal.getIdade() != null) {
                sql.append("idade = ?,");
            }

            sql.deleteCharAt(sql.length() - 1);
            sql.append("WHERE id_animal = ? and animal.id_cliente = ?");

            PreparedStatement stmt = con.prepareStatement(sql.toString());

            int index =1;
            if(animal.getNome() != null) {
                stmt.setString(index++, animal.getNome());
            }
            if(animal.getTipoAnimal() != null) {
                stmt.setString(index++, animal.getTipoAnimal().toString());
            }
            if(animal.getRaca() != null) {
                stmt.setString(index++, animal.getRaca());
            }
            if(animal.getPelagem() != null) {
                stmt.setInt(index++, animal.getPelagem());
            }
            if(animal.getPorte() != null) {
                stmt.setInt(index++, animal.getPorte());
            }
            if(animal.getIdade() != null) {
                stmt.setInt(index++, animal.getIdade());
            }
            stmt.setInt(index++, id);
            stmt.setInt(index, animal.getCliente().getId());
            int res = stmt.executeUpdate();
            System.out.println("editarAnimal.res=" + res);

            if(res <= 0){
                System.out.println("Não foi possível editar esse animal =(");
                return false;
            }
            return true;

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

    public List<Animal> listar() throws BancoDeDadosException {
        List<Animal> animais = new ArrayList<>();
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();
            Statement stmt = con.createStatement();
            String sql = "SELECT A.* " +
                    "          , C.NOME AS NOME_DONO " +
                    "       FROM ANIMAL A " +
                    "       LEFT JOIN CLIENTE C ON (C.ID_CLIENTE = A.ID_CLIENTE) ";

            ResultSet res = stmt.executeQuery(sql);

            while(res.next()) {
                Animal animal = getAnimalFromResultSet(res);
                animais.add(animal);
            }
            return animais;
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

    public List<Animal> listarAnimalPorCliente(Cliente cliente) throws BancoDeDadosException {
        List<Animal> animais = new ArrayList<>();
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();
            String sql = """
                                SELECT a.*
                                , c.NOME
                                FROM ANIMAL a
                                INNER JOIN CLIENTE c ON (c.ID_CLIENTE = a.ID_CLIENTE)
                                WHERE a.ID_CLIENTE = ?
                    """;

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, cliente.getId());

            ResultSet res = stmt.executeQuery();

            while(res.next()) {
                Animal animal = getAnimalFromResultSet(res);
                animal.setCliente(cliente);
                animais.add(animal);
            }
            return animais;
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

    public Animal getAnimalPorId(int idPet) throws BancoDeDadosException {
        Animal animal;
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();
            String sql = """
                                SELECT a.*
                                FROM ANIMAL a
                                WHERE a.ID_ANIMAL = ?
                    """;

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, idPet);

            ResultSet res = stmt.executeQuery();
            animal = getAnimalFromResultSet(res);
            return animal;
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

    public Animal getAnimalPorId(int idPet, int idUsuario) throws BancoDeDadosException {
        Animal animal = null;
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();
            String sql = """
                                SELECT a.*
                                FROM ANIMAL a
                                WHERE a.ID_ANIMAL = ?
                                AND a.ID_CLIENTE = ?
                    """;

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, idPet);
            stmt.setInt(2, idUsuario);

            ResultSet res = stmt.executeQuery();

            if(res.next()) {
                animal = getAnimalFromResultSet(res);
            }
            return animal;
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

    private Animal getAnimalFromResultSet(ResultSet res) throws SQLException {
        Animal animal = new Animal();
        animal.setIdAnimal(res.getInt("id_animal"));
        animal.setNome(res.getString("nome"));
        animal.setTipoAnimal(EnumTipoAnimal.valueOf(res.getString("tipo")));
        animal.setRaca(res.getString("raca"));
        animal.setPelagem(res.getInt("pelagem"));
        animal.setPorte(res.getInt("porte"));
        animal.setIdade(res.getInt("idade"));
        return animal;
    }
}
