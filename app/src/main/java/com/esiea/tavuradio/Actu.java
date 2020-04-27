package com.esiea.tavuradio;

import android.graphics.Bitmap;

import java.net.URL;

public class Actu {

    private String titre = ""  ;
    private String description ="" ;
    private String dateEtHeure = "";
    private String imageUrl = "";

    public Actu() {
    }


    public String getImageUrl() { return imageUrl; }

    public String getTitre() {
        return titre;
    }

    public String getDescription(){
        return description;
    }

    public String getDateEtHeure() { return dateEtHeure; }


}

