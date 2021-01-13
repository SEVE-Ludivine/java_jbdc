package fr.epsi.jdbc;

import fr.epsi.jdbc.dal.PersistenceManager;
import fr.epsi.jdbc.dao.FournisseurDao;
import fr.epsi.jdbc.entites.Fournisseur;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class FournisseurDaoJdbc implements FournisseurDao {
    private static final String CREATE_QUERY_STRING = "INSERT INTO fournisseur (NOM) VALUES (?)";
    private static final String UPDATE_QUERY_STRING = "UPDATE fournisseur SET nom=? WHERE nom =? ";
    private static final String DELETE_QUERY_STRING = "DELETE from fournisseur WHERE nom =? ";

    @Override
    public List<Fournisseur> extraire() {
        ResourceBundle bundle = ResourceBundle.getBundle( "database" );
        List<Fournisseur> fournisseurs = new ArrayList<Fournisseur>();
        try (Connection connection = PersistenceManager.getConnection();
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
    public void insert(Fournisseur fournisseur)  throws SQLException{
        Connection connection = PersistenceManager.getConnection();
        try(PreparedStatement pst = connection.prepareStatement(CREATE_QUERY_STRING)){
            pst.setString(1,fournisseur.nom);
            pst.executeUpdate();
        }

/*
// CE CODE N'EST PAS SECURISE
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
        System.out.println("fin");*/
    }

    @Override
    public int update(String ancienNom, String nouveauNom) throws SQLException {
        Connection connection = PersistenceManager.getConnection();
        try(PreparedStatement pst = connection.prepareStatement(UPDATE_QUERY_STRING)){
            pst.setString(1,nouveauNom);
            pst.setString(2,ancienNom);
            pst.executeUpdate();
        }
        return 0;
    }

    @Override
    public boolean delete(Fournisseur fournisseur) throws SQLException {
        Connection connection = PersistenceManager.getConnection();
        try(PreparedStatement pst = connection.prepareStatement(DELETE_QUERY_STRING)){
            pst.setString(1,fournisseur.nom);
            pst.executeUpdate();
        }
        return true;
    }
}
