package negocio;

import java.io.FileNotFoundException;

import org.json.JSONException;

public interface SAUser {  
    EnumSkill showSkill(int score);
    void updateScore(String id, boolean likeOrDislike) throws JSONException, FileNotFoundException;
    void notifyFriends(String question, String[] friends);
	String logIn(String id,String password) throws IllegalArgumentException, JSONException, FileNotFoundException;
	UserTransfer getUser(String id) throws JSONException, FileNotFoundException;
	String signUp(String id, String password) throws IllegalArgumentException, JSONException, FileNotFoundException;
}