package fr.epsi.jdbc;

import fr.epsi.jdbc.dal.PersistenceManager;

import java.sql.SQLException;

public class TestUpdate {
    public static void main(String[] args) throws SQLException {

        FournisseurDaoJdbc f = new FournisseurDaoJdbc();
        f.update("COUCOU", "COUCOU 2");


        try {
            PersistenceManager.closeConnection();
        } catch (SQLException throwables) {
            System.out.println("Attention, je n'ai pas pu fermer la connexion");
        }

    }
}
