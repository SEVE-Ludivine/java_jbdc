package fr.epsi.jdbc;

import fr.epsi.jdbc.dal.PersistenceManager;
import fr.epsi.jdbc.dao.FournisseurDaoJdbc;
import fr.epsi.jdbc.entites.Fournisseur;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class TestUpdate {
    public static void main(String[] args) throws SQLException {

        FournisseurDaoJdbc f = new FournisseurDaoJdbc();
        f.update("COUCOU", "COUCOU 2");


        try { //On ferme la connexion a la fin de l'application
            PersistenceManager.closeConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
