package integracion;

import java.io.FileNotFoundException;
import java.util.List;

import org.json.JSONException;

import negocio.PollTransfer;

public interface DAOPoll {
    PollTransfer createPoll(String question, List<String> options, boolean anonymous, String user, int daysLeft) throws FileNotFoundException;
    void updatePoll(PollTransfer poll) throws JSONException, FileNotFoundException;
    void deletePoll(String id) throws JSONException, FileNotFoundException;
    void closePoll(String id) throws JSONException, FileNotFoundException;
    PollTransfer findPoll(String id) throws JSONException, FileNotFoundException;
    List<PollTransfer> getActivePolls() throws JSONException, FileNotFoundException;
    List<PollTransfer> getUserPolls(String idUser) throws JSONException, FileNotFoundException;
}
