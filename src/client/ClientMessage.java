package client;

import org.json.simple.JSONObject;


public class ClientMessage {

    public static String messageSend(String message){
        JSONObject object = new JSONObject();
        object.put("type", "message");
        object.put("text", message);
        return object.toJSONString();
    }

    public static String versionSend(String version){
        JSONObject object = new JSONObject();
        object.put("type", "version");
        object.put("version", version);
        return object.toJSONString();
    }

    public static String loginAttemptSend(String login, String password){
        JSONObject object = new JSONObject();
        object.put("type", "loginAttempt");
        object.put("login", login);
        object.put("password", password);
        return object.toJSONString();
    }

    public static String disconnectSend(String reason){
        JSONObject object = new JSONObject();
        object.put("type", "disconnect");
        object.put("reason", reason);
        return object.toJSONString();
    }

    public static String userPong() {
        JSONObject object = new JSONObject();
        object.put("type", "pong");
        return object.toJSONString();
    }

    public static String roomChangeRequestSend(String roomId) {
        JSONObject object = new JSONObject();
        object.put("type", "joinRoom");
        object.put("first", roomId);
        return object.toJSONString();
    }
}