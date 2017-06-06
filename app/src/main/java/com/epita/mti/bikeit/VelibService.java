package com.epita.mti.bikeit;


import com.epita.mti.bikeit.models.Data;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by T4ze on 24/05/2017.
 */

public interface VelibService {
    public static final String ENDPOINT = "https://opendata.paris.fr/";

    @GET("/api/records/1.0/search/?dataset=stations-velib-disponibilites-en-temps-reel&rows=100&facet=banking&facet=bonus&facet=status&facet=contract_name")
    Call<Data> getVelibData();

}
