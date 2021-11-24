package negocio;

import java.io.FileNotFoundException;

import org.json.JSONException;

import integracion.DAOUser;
import integracion.DAOUser_Imp;

public class SAUser_Imp implements SAUser {
	
	public String logIn(String id,String password) throws IllegalArgumentException, JSONException, FileNotFoundException{
		DAOUser DAO =new DAOUser_Imp();
		UserTransfer user = DAO.findUser(id);
		if (user == null) throw new IllegalArgumentException("Usuario no encontrado, introduzcalo de nuevo");
		if(user.getPassword().equals(password)) return user.getUser();
		else throw new IllegalArgumentException("Contraseña errónea, introduzcala de nuevo");
	}
	
	public String signUp(String id, String password) throws IllegalArgumentException, JSONException, FileNotFoundException {
		DAOUser DAO = new DAOUser_Imp();
		UserTransfer userDB = DAO.findUser(id);
		if (userDB == null) return DAO.createUser(id,password).getUser();
		else throw new IllegalArgumentException("Usuario repetido, introduzca otro");
	}

    public EnumSkill showSkill(int score) {
        return null;
    }
    
    public UserTransfer getUser(String id) throws JSONException, FileNotFoundException{
    	DAOUser DAO = new DAOUser_Imp();
		return DAO.findUser(id);
    }
  
    public void updateScore(String id, boolean likeOrDislike) throws JSONException, FileNotFoundException {
    	DAOUser DAO = new DAOUser_Imp();
    	UserTransfer user = DAO.findUser(id);
    	if (likeOrDislike) user.setScore(user.getScore() + 1);
    	else user.setScore(user.getScore() - 1);
    	DAO.updateUser(user);
    }

    public void notifyFriends(String question, String[] friends) {}
	
}
