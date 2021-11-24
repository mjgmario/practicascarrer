package integracion;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import negocio.UserTransfer;

public class DAOUser_Imp implements DAOUser {

    public UserTransfer findUser(String id) throws JSONException, FileNotFoundException{
    	JSONObject jsonUsers = new JSONObject(new JSONTokener(new FileInputStream(new File("resources/UsersDB.txt"))));
    	if (!jsonUsers.has(id)) return null;
    	JSONObject jsonUser = jsonUsers.getJSONObject(id);
    	UserTransfer user = new UserTransfer();
    	user.setUser(id);
    	user.setPassword(jsonUser.getString("password"));
    	user.setScore(jsonUser.getInt("score"));
    	JSONArray jsonFriendList = jsonUser.getJSONArray("friends");
    	String[] friendList = new String[jsonFriendList.length()];
    	for (int i = 0; i < jsonFriendList.length(); ++i)
    		friendList[i] = jsonFriendList.getString(i);
    	return user;
    }

    private JSONObject UserTransferToJSONObject(UserTransfer user) {
    	JSONObject json = new JSONObject();
    	json.put("password", user.getPassword());
    	json.put("score", user.getScore());
    	json.put("friends", user.getFriendList());
    	return json;
    }
    
    public UserTransfer createUser(String id,String password) throws JSONException, FileNotFoundException{
    	UserTransfer u = new UserTransfer();
    	u.setUser(id);
    	u.setPassword(password);
    	JSONObject jsonUsers = new JSONObject(new JSONTokener(new FileInputStream(new File("resources/UsersDB.txt"))));
    	jsonUsers.put(u.getUser(), UserTransferToJSONObject(u));
    	PrintStream p = new PrintStream(new FileOutputStream(new File("resources/UsersDB.txt")));
    	p.print(jsonUsers.toString(3));
    	p.close();
    	return u;
    }
   
    public void updateUser(UserTransfer user) throws JSONException, FileNotFoundException {
    	JSONObject jsonUsers = new JSONObject(new JSONTokener(new FileInputStream(new File("resources/UsersDB.txt"))));
    	jsonUsers.put(user.getUser(), UserTransferToJSONObject(user));
    	PrintStream p = new PrintStream(new FileOutputStream(new File("resources/UsersDB.txt")));
    	p.print(jsonUsers.toString(3));
    	p.close();
    }
    
    public void deleteUser(final UserTransfer user) throws JSONException, FileNotFoundException {
    	JSONObject jsonUsers = new JSONObject(new JSONTokener(new FileInputStream(new File("resources/UsersDB.txt"))));
    	jsonUsers.remove(user.getUser());
    	PrintStream p = new PrintStream(new FileOutputStream(new File("resources/UsersDB.txt")));
    	p.print(jsonUsers.toString(3));
    	p.close();
    }
    
}
