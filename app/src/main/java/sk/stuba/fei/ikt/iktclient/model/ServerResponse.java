package sk.stuba.fei.ikt.iktclient.model;

/**
 * Model of server response object
 */
public class ServerResponse extends BaseObject {

    private Integer code;
    private String message;
    private String token;

    public ServerResponse(String token) {
        this.token = token;
    }

    public ServerResponse(ApiState error) {
        this.code = error.getCode();
        this.message = error.getMessage();
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getToken() {
        return token;
    }


}
