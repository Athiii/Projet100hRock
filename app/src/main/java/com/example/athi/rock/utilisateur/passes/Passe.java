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
    private String imagePasseUrl;
    private String videoPasseUrl;

    public  Passe(){

    }

    public Passe (String nom, int niveau, String imagePasseUrl,String videoPasseUrl){
        this.nom=nom;
        this.niveau=niveau;
        this.imagePasseUrl=imagePasseUrl;
        this.videoPasseUrl=videoPasseUrl;
    }

    public String getNom() {return nom;}
    public void setNom(String nom) {this.nom = nom;}
    public int getNiveau() {return niveau;}
    public void setNiveau(int niveau) {this.niveau = niveau;}
    public String getImagePasseUrl() {return imagePasseUrl;}
    public void setImagePasseUrl(String imagePasseUrl) {this.imagePasseUrl = imagePasseUrl;}
    public String getVideoPasseUrl() {return videoPasseUrl;}
    public void setVideoPasseUrl(String videoPasseUrl) {this.videoPasseUrl = videoPasseUrl;}
}
