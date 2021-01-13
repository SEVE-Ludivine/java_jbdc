package fr.epsi.jdbc.dao;

import fr.epsi.jdbc.entites.Article;
import fr.epsi.jdbc.entites.Fournisseur;

import java.sql.SQLException;
import java.util.List;

public interface ArticleDAO {
    List<Article> extraire() throws SQLException;
    void insert(Article article) throws SQLException;
    int update(Article article) throws SQLException;
    boolean delete(Article article) throws SQLException;
    void updatePrixMate() throws SQLException;
}
