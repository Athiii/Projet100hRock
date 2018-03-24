package com.example.athi.rock.utilisateur.passes;

/**
 * Created by Athi on 12/02/2018.
 */
/* TODO: Update blank fragment layout
 * niveau -> int
 * date apparition -> Date
 * video ???
 */
public class Passe {
    private String nom;
    private int niveau;
    private int color;

    public Passe (String nom, int niveau, int color){
        this.nom=nom;
        this.niveau=niveau;
        this.color=color;
    }

    public String getNom() {return nom;}
    public void setNom(String nom) {this.nom = nom;}
    public int getNiveau() {return niveau;}
    public void setNiveau(int niveau) {this.niveau = niveau;}
    public int getColor() {return color;}
    public void setColor(int color) {this.color = color;}
}
