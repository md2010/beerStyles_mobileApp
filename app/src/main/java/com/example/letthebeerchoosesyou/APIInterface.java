package com.example.letthebeerchoosesyou;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {

    @GET("api/beers")
    Call<List<Data>> getBeersWithAllParams(@Query("abv") float abv, @Query("srm") float srm,  @Query("ibu") float ibu);

}
