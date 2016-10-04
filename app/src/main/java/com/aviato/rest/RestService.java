package com.aviato.rest;

import com.aviato.model.ChooseData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;


public class RestService {

    private static final String URL = "";

    public interface AviatoEndPoint {
        // Request method and URL specified in the annotation
        // Callback for the parsed response is the last parameter

        @GET("/attlist")
        Call<List<ChooseData>> getUser(@Query("loc") String airportCode);

        @POST("/book")
        Call createBooking(@Body BookingRequest bookingRequest);

    }

    public static AviatoEndPoint getService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .build();
        return retrofit.create(AviatoEndPoint.class);
    }
}
