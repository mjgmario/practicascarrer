package integracion;

import java.io.FileNotFoundException;

import org.json.JSONException;

import negocio.UserTransfer;

public interface DAOUser {
    void updateUser(UserTransfer user) throws JSONException, FileNotFoundException;
    void deleteUser(UserTransfer user) throws JSONException, FileNotFoundException;
	UserTransfer findUser(String id) throws JSONException, FileNotFoundException;
	UserTransfer createUser(String id, String password) throws JSONException, FileNotFoundException;
}
