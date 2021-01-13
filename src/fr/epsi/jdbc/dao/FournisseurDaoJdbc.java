package fr.epsi.jdbc.dao;

import fr.epsi.jdbc.entites.Fournisseur;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class FournisseurDaoJdbc implements FournisseurDao {
    @Override
    public List<Fournisseur> extraire() {
        ResourceBundle bundle = ResourceBundle.getBundle( "database" );
        List<Fournisseur> fournisseurs = new ArrayList<Fournisseur>();
        try (Connection connection =DriverManager
                .getConnection( bundle.getString( "db.url" ), bundle.getString( "db.user" ), bundle
                        .getString( "db.password" ));
             Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery( "SELECT * FROM fournisseur" )
        ) {
            ResultSetMetaData rsm = rs.getMetaData();

            // test arraylist


            int nbColumns = rsm.getColumnCount();
            for(int i = 1; i <= nbColumns; ++i) {
                System.out.printf( "%30s (%s)", rsm.getColumnName( i ), rsm.getColumnTypeName( i ) );
            }
            System.out.println();
            while(rs.next()) {
                fournisseurs.add(new Fournisseur(rs.getString(2),rs.getInt(1)));
            }

        } catch ( SQLException e ) {
            e.printStackTrace();
        }
        return fournisseurs;
    }

    @Override
    public void insert(Fournisseur fournisseur) {
        ResourceBundle bundle = ResourceBundle.getBundle( "database" );
        try (Connection connection = DriverManager.getConnection(bundle.getString("db.url"), bundle.getString("db.user"),
                bundle.getString("db.password"));
             Statement st = connection.createStatement();
        ) {
            int nb = st.executeUpdate( "INSERT INTO fournisseur (NOM) VALUES ('"+ fournisseur.nom+"')" );
            System.out.println(nb);
        } catch ( SQLException e ) {
            e.printStackTrace();
        } catch ( Exception e ) {
            e.printStackTrace();
        }
        System.out.println("fin");
    }

    @Override
    public int update(String ancienNom, String nouveauNom) {
        ResourceBundle bundle = ResourceBundle.getBundle( "database" );
        try (Connection connection = DriverManager.getConnection(bundle.getString("db.url"), bundle.getString("db.user"),
                bundle.getString("db.password"));
             Statement st = connection.createStatement();
        ) {
            int nb = st.executeUpdate( "UPDATE fournisseur SET nom='"+nouveauNom+"' WHERE nom = '"+ancienNom+"'" );
            System.out.println(nb);
        } catch ( SQLException e ) {
            e.printStackTrace();
        } catch ( Exception e ) {
            e.printStackTrace();
        }
        System.out.println("fin");
        return 0;
    }

    @Override
    public boolean delete(Fournisseur fournisseur) {
        ResourceBundle bundle = ResourceBundle.getBundle( "database" );
        try (Connection connection = DriverManager.getConnection(bundle.getString("db.url"), bundle.getString("db.user"),
                bundle.getString("db.password"));
             Statement st = connection.createStatement();
        ) {
            int nb = st.executeUpdate( "DELETE from fournisseur WHERE nom = '"+fournisseur.nom+"'" );
            System.out.println(nb);
        } catch ( SQLException e ) {
            e.printStackTrace();
        } catch ( Exception e ) {
            e.printStackTrace();
        }
        System.out.println("fin");
        return true;
    }
}
