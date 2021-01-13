package fr.epsi.jdbc.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class PersistenceManager {


    private static final String DB_URL;
    private static final String DB_USER_LOGIN;
    private static final String DB_USER_PWD;

    private static Connection connection;

    static {
        // System.out.println( "PERSISTENCE INIT STATIC BLOC" );
        ResourceBundle bundle = ResourceBundle.getBundle( "database" );
        DB_URL = bundle.getString( "db.url" );
        DB_USER_LOGIN = bundle.getString( "db.user" );
        DB_USER_PWD = bundle.getString( "db.password" );
    }

    {
        // System.out.println( "PERSISTENCE INIT BLOC (NOT USED IN THIS CASE)" );
    }

    //private PersistenceManager() {}

    public static Connection getConnection() throws SQLException {
        if ( null == connection ) {
            connection = DriverManager.getConnection( DB_URL, DB_USER_LOGIN, DB_USER_PWD );
        }
        return connection;
    }

    public static void closeConnection() throws SQLException {
        if ( null != connection && !connection.isClosed() ) {
            connection.close();
        }
    }


}
