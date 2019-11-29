package sk.stuba.fei.ikt.iktclient.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retrofit client is used for all network traffic. It has defined base server url that it is
 * connecting to. ROOT_URL should be changed based on ip address of server.
 */
public class RetroClient {

    private static final String ROOT_URL = "http://167.172.186.143:8000/api/";


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
