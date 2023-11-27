package database.daos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import database.BDSQLServer;
import database.core.*;
import database.dbos.*;

/**
 *
 * @author Rafael
 */
public class TaskFlow {

    public static boolean autenticar(String user, String password) throws Exception {
        boolean retorno = false;

        try {
            String sql;

            sql = "SELECT COUNT(*) AS EXIST " +
                    "FROM taskFlow.users " +
                    "WHERE username = ? and password_hash = ?";

            BDSQLServer.COMANDO.prepareStatement(sql);

            BDSQLServer.COMANDO.setString(1, user);

            BDSQLServer.COMANDO.setString(2, password);

            MeuResultSet resultado = (MeuResultSet) BDSQLServer.COMANDO.executeQuery();

            resultado.first();

            retorno = resultado.getInt("EXIST") != 0;
        } catch (SQLException erro) {
            BDSQLServer.COMANDO.rollback();
            throw new Exception(erro);
        }
        return retorno;
    }

    public static boolean insertUser(String[] info) throws Exception {
        for (int i = 0; i < info.length-1; i++) {
            if (info[i].length() == 0) {
                throw new Exception("Preencha todos os campos!");
            }
        }
        boolean retorno = false;

        try {
            String sql;

            sql = "EXEC taskFlow.spAddUser " +
                    "@first_name = ?," +
                    "@last_name = ?," +
                    "@username = ?," +
                    "@email = ?," +
                    "@password_hash = ?," +
                    "@profile_image = ?";

            BDSQLServer.COMANDO.prepareStatement(sql);

            BDSQLServer.COMANDO.setString(1, info[0]);
            BDSQLServer.COMANDO.setString(2, info[1]);
            BDSQLServer.COMANDO.setString(3, info[3]);
            BDSQLServer.COMANDO.setString(4, info[2]);
            BDSQLServer.COMANDO.setString(5, info[4]);
            BDSQLServer.COMANDO.setNull(6, -2);

            BDSQLServer.COMANDO.executeUpdate();
            BDSQLServer.COMANDO.commit();

            retorno = true;
        } catch (SQLException erro) {
            BDSQLServer.COMANDO.rollback();
            throw new Exception("Erro ao inserir o Usuário; Erro: " + erro);
        }
        return retorno;
    }

    public static boolean updateUser(String[] info) throws Exception{
        boolean retorno = false;

        try {
            String sql;


            sql = "EXEC taskFlow.spUpdateUser " +
                    "@user_id = ?," +
                    "@first_name = ?," +
                    "@last_name = ?," +
                    "@username = ?," +
                    "@email = ?";
                    

            BDSQLServer.COMANDO.prepareStatement(sql);

            BDSQLServer.COMANDO.setInt(1, Integer.valueOf(info[0]));
            BDSQLServer.COMANDO.setString(2, info[1]);
            BDSQLServer.COMANDO.setString(3, info[2]);
            BDSQLServer.COMANDO.setString(4, info[3]);
            BDSQLServer.COMANDO.setString(5, info[4]);

            BDSQLServer.COMANDO.executeUpdate();
            BDSQLServer.COMANDO.commit();

            retorno = true;
        } catch (SQLException erro) {
            BDSQLServer.COMANDO.rollback();
            throw new Exception("Erro ao inserir o Usuário; Erro: " + erro);
        }

        return retorno;
    }

    public static boolean deleteUser(int id) throws Exception {
        boolean retorno = false;

        try {
            String sql;

            sql = "EXEC taskFlow.spDeleteUser " +
                    "@user_id = ?";

            BDSQLServer.COMANDO.prepareStatement(sql);

            BDSQLServer.COMANDO.setInt(1, id);

            BDSQLServer.COMANDO.executeUpdate();
            BDSQLServer.COMANDO.commit();

            retorno = true;
        } catch (SQLException erro) {
            BDSQLServer.COMANDO.rollback();
            throw new Exception("Erro ao excluir Usuário");
        }

        return retorno;
    }

    public static List<User> searchUser(String content) throws Exception{
        List<User> fetchAll = new ArrayList<User>();
        MeuResultSet resultado = null;


        try {
            String sql;

            sql = "EXEC taskFlow.spSearchUser " +
                    "@infoN = ?";

            BDSQLServer.COMANDO.prepareStatement(sql);

            BDSQLServer.COMANDO.setString(1, content);

            resultado = (MeuResultSet) BDSQLServer.COMANDO.executeQuery();

            while (resultado.next()) {
                User user = new User();

                user.setId(resultado.getInt(1));

                user.setFirstName(resultado.getString(2));

                user.setLastName(resultado.getString(3));

                user.setUsername(resultado.getString(4));

                user.setEmail(resultado.getString(5));
                
                user.setCreatedAt(resultado.getString(6));

                user.setUpdatedAt(resultado.getString(7));

                fetchAll.add(user);
                
            }
        } 
        catch (SQLException erro) {
            throw new Exception("Erro ao encontrar Usuário; Erro: "+erro);
        }
       
        return fetchAll;
    }

    
    public static List<User> getUsers() throws Exception {
        List<User> fetchAll = new ArrayList<User>();
        MeuResultSet resultado = null;

        try {
            String sql;

            sql = "SELECT * " +
                    "FROM taskFlow.vUsers";

            BDSQLServer.COMANDO.prepareStatement(sql);

            resultado = (MeuResultSet) BDSQLServer.COMANDO.executeQuery();

            while (resultado.next()) {
                User user = new User();

                user.setId(resultado.getInt(1));

                user.setFirstName(resultado.getString(2));

                user.setLastName(resultado.getString(3));

                user.setUsername(resultado.getString(4));

                user.setEmail(resultado.getString(5));

                user.setCreatedAt(resultado.getString(6));

                user.setUpdatedAt(resultado.getString(7));

                fetchAll.add(user);
            }
        } 
        catch (SQLException erro) {
            throw new Exception("Erro ao recuperar usuários");
        }
       
        return fetchAll;
    }
}
