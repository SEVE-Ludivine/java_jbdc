package fr.epsi.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import fr.epsi.jdbc.dal.PersistenceManager;
import fr.epsi.jdbc.dao.FournisseurDao;
import fr.epsi.jdbc.dao.FournisseurDaoJdbc;
import fr.epsi.jdbc.entites.Fournisseur;

public class TestInsertion {
    public static void main(String[] args) throws SQLException {

        FournisseurDaoJdbc f = new FournisseurDaoJdbc();
        //f.insert(new Fournisseur("La maison de la peinture2", 15));

        //Il faut mettre 2 fois ' pour que ca puisse rentrer dans la base de données
        f.insert(new Fournisseur("Tst'apostrophe", 15));

        try {
            PersistenceManager.closeConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
