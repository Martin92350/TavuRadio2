package com.esiea.tavuradio;

import retrofit2.Call;
import retrofit2.http.GET;
//import retrofit2.http.Query;

public interface ActuApi {

    @GET("NewApi.json")
    Call<RestActuResponse> getActuResponse();

    //Dans le cas où l'on veut rajouter des infos, plus de précisions dispo sur le serveur en question
    //@GET("api/v2/******")
    //Call<RestActuResponse> get****();
}
