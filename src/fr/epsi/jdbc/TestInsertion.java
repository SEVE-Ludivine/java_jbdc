package fr.epsi.jdbc;

import java.sql.SQLException;

import fr.epsi.jdbc.dal.PersistenceManager;
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
            System.out.println("Attention, je n'ai pas pu fermer la connexion");
        }

    }
}
