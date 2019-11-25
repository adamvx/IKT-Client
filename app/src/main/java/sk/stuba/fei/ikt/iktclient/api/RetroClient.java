package sk.stuba.fei.ikt.iktclient.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroClient {

    private static final String ROOT_URL = "http://192.168.1.115:8000/api/";


    private static Retrofit getRetrofitInstance() {

        return new Retrofit.Builder()
                .baseUrl(ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static Api getApiService() {
        return getRetrofitInstance().create(Api.class);
    }
}
