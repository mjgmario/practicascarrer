package negocio;

import java.io.FileNotFoundException;
import java.util.List;

import org.json.JSONException;


public interface SAPoll {
    void vote(String idPoll, String idUser, String option) throws JSONException, FileNotFoundException;
    PollTransfer createPoll(String id, List<String> options, boolean anonimous, String user, int daysLeft) throws FileNotFoundException;
    void addComment(String pollId, String user, String comment) throws JSONException, FileNotFoundException;
    void likeOrDislikeComment(String pollId, int commentId, boolean likeOrDislike) throws JSONException, FileNotFoundException;
    void closePoll(String pollId) throws JSONException, FileNotFoundException;
    public PollTransfer getPoll(String id) throws JSONException, FileNotFoundException;
    List<PollTransfer> getUserPolls(String user) throws JSONException, FileNotFoundException;
    List<PollTransfer> getActivePolls() throws JSONException, FileNotFoundException;
}
