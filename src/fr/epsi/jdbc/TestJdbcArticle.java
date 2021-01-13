package fr.epsi.jdbc;

import fr.epsi.jdbc.dal.PersistenceManager;
import fr.epsi.jdbc.entites.Article;
import fr.epsi.jdbc.entites.Fournisseur;

import java.sql.SQLException;
import java.util.List;

public class TestJdbcArticle {
    public static ArticleDaoJdbc a = new ArticleDaoJdbc();
    public static void main(String[] args) {


        //INSERT les données dans la base de données
        //insert();

        //UPDATE PRIX MATE
        /*try {
            a.updatePrixMate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }*/

        //Afficher la liste de tous les articles
        try {
            List<Article> articles = a.extraire();
            for(Article article: articles)
            {
                System.out.println(article.getRef()+" "+ article.getDesignation());
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public static void insert(){
        ArticleDaoJdbc a = new ArticleDaoJdbc();
        //f.insert(new Fournisseur("La maison de la peinture2", 15));

        //Il faut mettre 2 fois ' pour que ca puisse rentrer dans la base de données
        try {
            a.insert(new Article("P01", "Peinture blanche 1L", 12.5, new Fournisseur("La maison de la peinture")));
            a.insert(new Article("P02", "Peinture rouge mate 1L", 15.5, new Fournisseur("La maison de la peinture")));
            a.insert(new Article("P03", "Peinture noire laquée 1L", 17.8, new Fournisseur("La maison de la peinture")));
            a.insert(new Article("P04", "Peinture bleue mate 1L", 15.5, new Fournisseur("La maison de la peinture")));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            PersistenceManager.closeConnection();
        } catch (SQLException throwables) {
            System.out.println("Attention, je n'ai pas pu fermer la connexion");
        }
    }
}
