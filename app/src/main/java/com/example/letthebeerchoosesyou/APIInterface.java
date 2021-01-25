package com.example.letthebeerchoosesyou;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {

    @GET("api/beers")
    Call<List<Data>> getBeersWithAllParams(@Query("abv") int abv, @Query("srm") int srm,  @Query("ibu") int ibu);

}
