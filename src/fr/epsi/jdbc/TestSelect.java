package fr.epsi.jdbc;

import fr.epsi.jdbc.dao.FournisseurDaoJdbc;
import fr.epsi.jdbc.entites.Fournisseur;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class TestSelect {
    public static void main(String[] args) {
        /*ResourceBundle bundle = ResourceBundle.getBundle( "database" );
        try (Connection connection =DriverManager
                .getConnection( bundle.getString( "db.url" ), bundle.getString( "db.user" ), bundle
                        .getString( "db.password" ));
             Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery( "SELECT * FROM fournisseur" )
        ) {
            ResultSetMetaData rsm = rs.getMetaData();

            // test arraylist
            List<Fournisseur> fournisseurs = new ArrayList<Fournisseur>();

            int nbColumns = rsm.getColumnCount();
            for(int i = 1; i <= nbColumns; ++i) {
                System.out.printf( "%30s (%s)", rsm.getColumnName( i ), rsm.getColumnTypeName( i ) );
            }
            System.out.println();
            while(rs.next()) {
                fournisseurs.add(new Fournisseur(rs.getString(2),rs.getInt(1)));
            }
            //On affiche les fournisseurs qui sont dans l'arraylist
            for(Fournisseur f: fournisseurs)
            {
                System.out.println(f.id+" "+ f.nom);
            }

        } catch ( SQLException e ) {
            e.printStackTrace();
        }*/

        FournisseurDaoJdbc f = new FournisseurDaoJdbc();
        List<Fournisseur> fournisseurs = f.extraire();

        //On affiche les fournisseurs qui sont dans l'arraylist
        for(Fournisseur four: fournisseurs)
        {
            System.out.println(four.id+" "+ four.nom);
        }
    }
}
