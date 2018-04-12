package com.example.athi.rock.utilisateur.evenement;

import android.media.Image;

/**
 * Created by Athi on 20/02/2018.
 */
/* TODO
* Image Photo comment gérer les images
* */
public class Photo {
    private String type;
    private String nomImage;
    private String urlImage;

    public String getNomImage() {
        return nomImage;
    }
    public void setNomImage(String nomImage) {
        this.nomImage = nomImage;
    }
    public String getUrlImage() {return urlImage;    }
    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }


    public Photo (String urlImage,String type,String nomImage ){
        this.type=type;
        this.nomImage=nomImage;
        this.urlImage=urlImage;
    }

    public Photo(){

    }


}
