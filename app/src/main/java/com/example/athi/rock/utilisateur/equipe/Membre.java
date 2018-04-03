package com.example.athi.rock.utilisateur.equipe;

/**
 * Created by Athi on 11/02/2018.
 * Constructeur Membre -> Représente les memebres de l'équipe
 */
/* TODO: Update blank fragment layout
*int color -> Image photoMdembre
*
 */
public class Membre {
    private String role;
    private String description;
    private String nom;
    private String prenom;
    private int color;

    public Membre(){
    }

    public Membre (int color, String description, String nom, String prenom,String role){
        this.role=role;
        this.description=description;
        this.nom=nom;
        this.prenom=prenom;
        this.color=color;
    }
    public String getRole() {return role;}
    public void setRole(String role) {this.role = role;}
    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}
    public String getNom() {return nom;}
    public void setNom(String nom) {this.nom = nom;}
    public String getPrenom() {return prenom;}
    public void setPrenom(String prenom) {this.prenom = prenom;}
    public int getColor() {return color;}
    public void setColor(int color) {this.color = color;}
}
