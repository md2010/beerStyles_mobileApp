package com.example.letthebeerchoosesyou;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkUtils {
    private static APIInterface apiinterface;
    private static String BASE_URL = "http://192.168.43.73/";
    public static APIInterface getGetAPIInterface(){
        if(apiinterface == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            apiinterface = retrofit.create(APIInterface.class);
        }
        return apiinterface;
    }
}
