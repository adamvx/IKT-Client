package sk.stuba.fei.ikt.iktclient.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import sk.stuba.fei.ikt.iktclient.model.Note;
import sk.stuba.fei.ikt.iktclient.model.ServerResponse;
import sk.stuba.fei.ikt.iktclient.model.User;

/**
 * This interface defines all network requests against server.
 */
public interface Api {

    @GET("check")
    Call<ServerResponse> serverAvailable();

    @POST("notes")
    Call<List<Note>> getNotes(@Body ServerResponse serverResponse);

    @POST("login")
    Call<ServerResponse> login(@Body User user);

    @POST("register")
    Call<ServerResponse> register(@Body User user);

    @POST("delete")
    Call<List<Note>> deleteNote(@Body Note note);

    @POST("create")
    Call<List<Note>> createNote(@Body Note note);

    @POST("edit")
    Call<List<Note>> editNote(@Body Note note);
}
