package com.example.athi.rock.evenement;

import android.media.Image;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Athi on 20/02/2018.
 * Constructeur Evenement -> Représente les événements
 */

public class Evenement {
    private int idEvent;
    private String nomEvent;
    private String descriptionEvent;
    private String adresse;
    //private Photo affiche;
    private Timestamp dateEvent;
   // private ArrayList<Photo> listePhotosEvent;

    public Evenement(int idEvent, String nomEvent, String descriptionEvent, String adresse, Timestamp date){
        super();
        this.idEvent=idEvent;
        this.nomEvent=nomEvent;
        this.descriptionEvent=descriptionEvent;
        this.adresse=adresse;
       // this.affiche=affiche;
        this.dateEvent=date;
        // this.listePhotosEvent=listePhotosEvent;
    }

    public int getIdEvent() {return idEvent;}
    public void setIdEvent(int idEvent) {this.idEvent = idEvent;}
    public String getNomEvent() {return nomEvent;}
    public void setNomEvent(String nomEvent) {this.nomEvent = nomEvent;}
    public String getDescriptionEvent() {return descriptionEvent;}
    public void setDescriptionEvent(String descriptionEvent) {this.descriptionEvent = descriptionEvent;}
    public String getAdresse() {return adresse;}
    public void setAdresse(String adresse) {this.adresse = adresse;}
   // public Photo getAffiche() {return affiche;}
    //public void setAffiche(Photo affiche) {this.affiche = affiche;}
    public Timestamp getDateEvent() {return dateEvent;}
    public void setDateEvent(Timestamp date) {this.dateEvent = date;}
    //public ArrayList<Photo> getListePhotosEvent() {return listePhotosEvent;}
    //public void setListePhotosEvent(ArrayList<Photo> listePhotosEvent) {this.listePhotosEvent = listePhotosEvent;}
}
