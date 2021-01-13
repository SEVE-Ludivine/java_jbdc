package fr.epsi.jdbc.entites;

public class Fournisseur {
    public String nom;
    public int id;

    public Fournisseur(String nom, int id) {
        this.nom = nom;
        this.id = id;
    }
    public Fournisseur(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
