package database;

import database.core.*;

public class BDSQLServer
{
    public static final MeuPreparedStatement COMANDO;

    static
    {
        System.out.println("Chegou no BD");
    	MeuPreparedStatement comando = null;

    	try
        {
            comando =
            new MeuPreparedStatement (
            "com.microsoft.sqlserver.jdbc.SQLServerDriver",
            "jdbc:sqlserver://regulus.cotuca.unicamp.br:1433;databasename=BD23333;trustServerCertificate=true",
            "BD23333", "BD23333");

            System.out.println("Conexão feita");
        }
        catch (Exception erro)
        {
            System.err.println ("Problemas de conexao com o BD");
            System.out.println(erro);
            System.exit(0); // aborta o programa
        }
        
        COMANDO = comando;
    }
}