package fr.epsi.jdbc;

import fr.epsi.jdbc.dal.PersistenceManager;
import fr.epsi.jdbc.entites.Fournisseur;

import java.sql.SQLException;

public class TestDelete {
    public static void main(String[] args) throws SQLException {

        FournisseurDaoJdbc f = new FournisseurDaoJdbc();
        boolean b = f.delete(new Fournisseur("COUCOU 2", 15));

        try {
            PersistenceManager.closeConnection();
        } catch (SQLException throwables) {
            System.out.println("Attention, je n'ai pas pu fermer la connexion");
        }
    }
}
