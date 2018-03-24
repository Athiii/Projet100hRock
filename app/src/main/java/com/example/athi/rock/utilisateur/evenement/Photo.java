package com.example.athi.rock.utilisateur.evenement;

import android.media.Image;

/**
 * Created by Athi on 20/02/2018.
 */
/* TODO
* Image Photo comment gérer les images
* */
public class Photo {
    private int idPhoto;
    private Image photo;

    public Photo (int idPhoto, Image photo){
        this.idPhoto=idPhoto;
        this.photo=photo;
    }

    public int getIdPhoto() {return idPhoto;}
    public void setIdPhoto(int idPhoto) {this.idPhoto = idPhoto;}
    public Image getPhoto() {return photo;}
    public void setPhoto(Image photo) {this.photo = photo;}
}
