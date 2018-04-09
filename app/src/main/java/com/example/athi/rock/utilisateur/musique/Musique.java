package com.example.athi.rock.utilisateur.musique;

/**
 * Created by Athi on 12/02/2018.
 */
/*TODO: Update blank fragment layout
* nbLike -> int
* */
public class Musique {

    private String nomMusique;
    private String artiste;
    private int nbLike;

    public Musique(){
    }

    public Musique (String nomMusique,String artiste,int nbLike){

        this.nomMusique=nomMusique;
        this.artiste=artiste;
        this.nbLike=nbLike;
    }

    public String getNomMusique() {return nomMusique;}
    public void setNomMusique(String nomMusique) {this.nomMusique = nomMusique;}
    public String getArtiste() {return artiste;}
    public void setArtiste(String artiste) {this.artiste = artiste;}
    public int getNbLike() {return nbLike;}
    public void setNbLike(int nbLike) {this.nbLike = nbLike;}

}
