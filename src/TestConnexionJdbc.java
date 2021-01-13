import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class TestConnexionJdbc {

    public static void main(String[] args) {
        try {
            ResourceBundle bundle = ResourceBundle.getBundle("database");
            //Class.forName("org.mariadb.jdbc.Driver");
            Connection connection = DriverManager.getConnection(bundle.getString("db.url"), bundle.getString("db.user"), bundle.getString("db.password"));
            System.out.println(connection);
            connection.close();
        }catch(SQLException e){
            e.printStackTrace();
        }




    }


}

