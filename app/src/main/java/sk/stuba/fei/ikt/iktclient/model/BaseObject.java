package sk.stuba.fei.ikt.iktclient.model;

import com.google.gson.Gson;

public class BaseObject {

    public String toJson() {
        return new Gson().toJson(this);
    }

}
