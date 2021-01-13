package fr.epsi.jdbc;

import fr.epsi.jdbc.dal.PersistenceManager;
import fr.epsi.jdbc.dao.ArticleDAO;
import fr.epsi.jdbc.entites.Article;
import fr.epsi.jdbc.entites.Fournisseur;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArticleDaoJdbc implements ArticleDAO {

    private static final String CREATE_QUERY_STRING = "INSERT INTO article (REF, DESIGNATION, PRIX, ID_FOU) VALUES (?,?,?, (SELECT ID FROM fournisseur WHERE NOM=?))";
    private static final String UPDATE_QUERY_STRING = "UPDATE article SET REF=?, DESIGNATION=?, PRIX=?, ID_FOU=? WHERE ID=? ";
    private static final String UPDATE_PRIX_MATE_QUERY_STRING = "UPDATE article SET PRIX=PRIX-PRIX*0.2 WHERE DESIGNATION LIKE '%mate%' ";
    private static final String DELETE_QUERY_STRING = "DELETE from article WHERE ID=? ";

    @Override
    public List<Article> extraire() throws SQLException {
        Connection connection = PersistenceManager.getConnection();
        List<Article> a = new ArrayList<>();
        ResultSet rs;
        try ( Statement st = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            rs = st.executeQuery("SELECT article.* ,fournisseur.*  FROM article INNER JOIN fournisseur WHERE fournisseur.ID = article.ID_FOU");
            //ResultSetMetaData rsm = rs.getMetaData();
            while(rs.next()) {
                a.add(new Article(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDouble(4),new Fournisseur(rs.getString(6), rs.getInt(5))));
            }
        }
        catch ( SQLException e ) {
            e.printStackTrace();
        }
        return a;
    }

    @Override
    public void insert(Article article) throws SQLException {
        Connection connection = PersistenceManager.getConnection();
        try(PreparedStatement pst = connection.prepareStatement(CREATE_QUERY_STRING)){
            pst.setString(1, article.getRef());
            pst.setString(2, article.getDesignation());
            pst.setDouble(3, article.getPrix());
            pst.setString(4, article.getFournisseur().getNom());
            pst.executeUpdate();
        }
    }

    @Override
    public int update(Article article) throws SQLException {
        Connection connection = PersistenceManager.getConnection();
        try(PreparedStatement pst = connection.prepareStatement(UPDATE_QUERY_STRING)){
            pst.setString(1, article.getRef());
            pst.setString(2, article.getDesignation());
            pst.setDouble(3, article.getPrix());
            pst.setInt(4, article.getFournisseur().getId());
            pst.setInt(5, article.getId());
            pst.executeUpdate();
        }
        return 0;
    }

    @Override
    public boolean delete(Article article) throws SQLException {
        Connection connection = PersistenceManager.getConnection();
        try(PreparedStatement pst = connection.prepareStatement(DELETE_QUERY_STRING)){
            pst.setInt(1, article.getId());
            pst.executeUpdate();
        }
        return true;
    }

    @Override
    public void updatePrixMate() throws SQLException {
        Connection connection = PersistenceManager.getConnection();
        try(PreparedStatement pst = connection.prepareStatement(UPDATE_PRIX_MATE_QUERY_STRING)){
            pst.executeUpdate();
        }

    }
}
