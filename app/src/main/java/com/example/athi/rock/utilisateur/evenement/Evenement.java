package com.example.athi.rock.utilisateur.evenement;

import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
    private Date dateEvent;
   // private ArrayList<Photo> listePhotosEvent;

    public Evenement(int idEvent, String nomEvent, String descriptionEvent, String adresse, Date date){
        super();
        this.idEvent=idEvent;
        this.nomEvent=nomEvent;
        this.descriptionEvent=descriptionEvent;
        this.adresse=adresse;
       // this.affiche=affiche;
        this.dateEvent=date;
        // this.listePhotosEvent=listePhotosEvent;
    }

    public Evenement() {}

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
    public Date getDateEvent() {return dateEvent;}
    public void setDateEvent(Date date) {this.dateEvent = date;}
    //public ArrayList<Photo> getListePhotosEvent() {return listePhotosEvent;}
    //public void setListePhotosEvent(ArrayList<Photo> listePhotosEvent) {this.listePhotosEvent = listePhotosEvent;}


/*
    //Faire les fonctions avec la base de donnée !!
    public void ajouterEvenement(Evenement evenement){
        List<Evenement> evenements = new ArrayList<Evenement>();
        evenements.add(evenement);
    }


    public void supprimerEvenementById(int id){ //Fonction a Changer id de l'évenement n'est pas égal à sa place dans la liste
        List<Evenement> evenements = new ArrayList<Evenement>();
        evenements.remove(id);
    }
    public void modifierEvenementById(int id, Evenement evenement){
        List<Evenement> evenements = new ArrayList<Evenement>();
        Evenement evenementAChanger = evenements.get(id);
    }
    */
}
