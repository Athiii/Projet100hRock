package com.example.athi.rock.evenement;

import java.sql.Timestamp;

/**
 * Created by Athi on 22/02/2018.
 */

public class EvenementPasse extends Evenement {

    public EvenementPasse(int idEvent, String nomEvent, String descriptionEvent, String adresse, Timestamp date){
        super(idEvent,nomEvent,descriptionEvent,adresse,date);
    }
}
