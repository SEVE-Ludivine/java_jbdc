package fr.epsi.jdbc;

import fr.epsi.jdbc.dao.FournisseurDaoJdbc;
import fr.epsi.jdbc.entites.Fournisseur;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;

public class TestDelete {
    public static void main(String[] args) {
        /*ResourceBundle bundle = ResourceBundle.getBundle( "database" );
        try (Connection connection = DriverManager.getConnection(bundle.getString("db.url"), bundle.getString("db.user"),
                bundle.getString("db.password"));
             Statement st = connection.createStatement();
        ) {
            int nb = st.executeUpdate( "DELETE from fournisseur WHERE nom = 'La maison des Peintures'" );
            System.out.println(nb);
        } catch ( SQLException e ) {
            e.printStackTrace();
        } catch ( Exception e ) {
            e.printStackTrace();
        }
        System.out.println("fin");*/

        FournisseurDaoJdbc f = new FournisseurDaoJdbc();
        boolean b = f.delete(new Fournisseur("La maison des peintures 2", 15));
    }
}
