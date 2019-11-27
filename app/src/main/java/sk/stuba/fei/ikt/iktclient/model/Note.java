package sk.stuba.fei.ikt.iktclient.model;

public class Note extends BaseObject {
    private int id;
    private int userId;
    private String heading;
    private String message;
    private String token;

    public Note(int id, String heading, String message, String token) {
        this.id = id;
        this.heading = heading;
        this.message = message;
        this.token = token;
    }

    public Note(String heading, String message, String token) {
        this.heading = heading;
        this.message = message;
        this.token = token;
    }

    public int getId() {
        return id;
    }

    public String getHeading() {
        return heading;
    }

    public String getMessage() {
        return message;
    }

    public int getUserId() {
        return userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
