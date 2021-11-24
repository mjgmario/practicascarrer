package negocio;

import java.io.FileNotFoundException;
import java.util.List;

import org.json.JSONException;

import integracion.DAOPoll;
import integracion.DAOPoll_Imp;

public class SAPoll_Imp implements SAPoll {
   
    public void vote(String pollId, String user, String option) throws JSONException, FileNotFoundException {
    	DAOPoll DAO = new DAOPoll_Imp();
    	PollTransfer poll = DAO.findPoll(pollId);
    	if (!poll.getVoters().contains(user)) {
    		poll.getVoters().add(user);
    		int votes = poll.getOptions().get(option);
    		poll.getOptions().put(option, votes + 1);
    		DAO.updatePoll(poll);
    	}
    }

    public PollTransfer createPoll(String question, List<String> options, boolean anonimous, String user, int daysLeft) throws FileNotFoundException {
    	DAOPoll DAO = new DAOPoll_Imp();
        return DAO.createPoll(question, options,anonimous, user, daysLeft);
    }

    public void addComment(String pollId, String user, String comment) throws JSONException, FileNotFoundException {
    	DAOPoll DAO = new DAOPoll_Imp();
    	PollTransfer poll = DAO.findPoll(pollId);
    	CommentTransfer toAdd = new CommentTransfer();
    	toAdd.setComment(comment);
    	toAdd.setUser(user);
    	List<CommentTransfer> comments = poll.getComments().getCommentList();
    	if (comments.size() > 0) toAdd.setId(comments.get(comments.size() - 1).getId() + 1);
    	else toAdd.setId(1);
    	poll.getComments().insertComment(toAdd);
    	DAO.updatePoll(poll);
    }

    public void likeOrDislikeComment(String pollId, int commentId, boolean likeOrDislike) throws JSONException, FileNotFoundException {
    	DAOPoll DAO = new DAOPoll_Imp();
    	PollTransfer poll = DAO.findPoll(pollId);
    	CommentTransfer toUpdate = poll.getComments().getComment(commentId);
    	if (likeOrDislike) toUpdate.setLikes(toUpdate.getLikes() + 1);
    	else toUpdate.setDislikes(toUpdate.getDislikes() + 1);
    	DAO.updatePoll(poll);
    }

    public void closePoll(String pollId) throws JSONException, FileNotFoundException {
    	DAOPoll DAO = new DAOPoll_Imp();
    	DAO.closePoll(pollId);
    }
    
    public PollTransfer getPoll(String id) throws JSONException, FileNotFoundException {
    	DAOPoll DAO = new DAOPoll_Imp();
    	return DAO.findPoll(id);
    }
    
    public List<PollTransfer> getUserPolls(String user) throws JSONException, FileNotFoundException {
    	DAOPoll DAO = new DAOPoll_Imp();
        return DAO.getUserPolls(user);
    }

    public List<PollTransfer> getActivePolls() throws JSONException, FileNotFoundException {
    	DAOPoll DAO = new DAOPoll_Imp();
    	return DAO.getActivePolls();
    }

}
