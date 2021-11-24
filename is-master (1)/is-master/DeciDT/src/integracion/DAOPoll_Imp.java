package integracion;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import negocio.CommentBO;
import negocio.CommentTransfer;
import negocio.PollTransfer;

public class DAOPoll_Imp implements DAOPoll {
  
	private String nextId(String id) {
		return "A";
	}
	
	private JSONObject PollTransferToJSONObject(PollTransfer poll) {
		JSONObject json = new JSONObject();
		json.put("question", poll.getQuestion());
		json.put("user", poll.getUser());
		json.put("anonymous", poll.getAnonymous());
		json.put("daysleft", poll.getDaysLeft());
		json.put("options", poll.getOptions());
		json.put("voters", poll.getVoters());
		json.put("comments", poll.getComments().getCommentList());
    	return json;
	}
	
    public PollTransfer createPoll(String question, List<String> options, boolean anonymous, String user, int daysLeft) throws FileNotFoundException {
    	JSONObject jsonPolls = new JSONObject(new JSONTokener(new FileInputStream(new File("resources/PollsDB.txt"))));
    	JSONObject jsonActivePolls = jsonPolls.getJSONObject("activepolls");
    	PollTransfer poll = new PollTransfer();
    	poll.setId(jsonPolls.getString("lastid"));
    	jsonPolls.put("lastid", nextId(poll.getId()));
    	poll.setQuestion(question);
    	poll.setUser(user);
    	poll.setAnonymous(anonymous);
    	poll.setDaysLeft(daysLeft);
    	Map<String, Integer> optionsMap = new HashMap<String, Integer>();
    	for (int i = 0; i < options.size(); ++i) optionsMap.put(options.get(i), 0);
    	poll.setOptions(optionsMap);
    	poll.setComments(new CommentBO());
    	jsonActivePolls.put(poll.getId(), PollTransferToJSONObject(poll));
    	PrintStream p = new PrintStream(new FileOutputStream(new File("resources/PollsDB.txt")));
    	p.print(jsonPolls.toString(3));
    	p.close();
        return poll;
    }

    public void updatePoll(PollTransfer poll) throws JSONException, FileNotFoundException {
    	JSONObject jsonPolls = new JSONObject(new JSONTokener(new FileInputStream(new File("resources/PollsDB.txt"))));
    	JSONObject jsonActivePolls = jsonPolls.getJSONObject("activepolls");
    	JSONObject jsonCompletedPolls = jsonPolls.getJSONObject("completedpolls");
    	if (jsonActivePolls.has(poll.getId())) jsonActivePolls.put(poll.getId(), PollTransferToJSONObject(poll));
    	else if (jsonCompletedPolls.has(poll.getId())) jsonCompletedPolls.put(poll.getId(), PollTransferToJSONObject(poll));
    	PrintStream p = new PrintStream(new FileOutputStream(new File("resources/PollsDB.txt")));
    	p.print(jsonPolls.toString(3));
    	p.close();
    }

    public void deletePoll(String id) throws JSONException, FileNotFoundException {
    	JSONObject jsonPolls = new JSONObject(new JSONTokener(new FileInputStream(new File("resources/PollsDB.txt"))));
    	JSONObject jsonActivePolls = jsonPolls.getJSONObject("activepolls");
    	JSONObject jsonCompletedPolls = jsonPolls.getJSONObject("completedpolls");
    	jsonActivePolls.remove(id);
    	jsonCompletedPolls.remove(id);
    	PrintStream p = new PrintStream(new FileOutputStream(new File("resources/PollsDB.txt")));
    	p.print(jsonPolls.toString(3));
    	p.close();
    }

    private PollTransfer findPoll(String id, JSONObject polls) {
    	if (!polls.has(id)) return null;
    	JSONObject jsonPoll = polls.getJSONObject(id);
    	PollTransfer poll = new PollTransfer();
    	poll.setId(id);
	    poll.setQuestion(jsonPoll.getString("question"));
	    poll.setUser(jsonPoll.getString("user"));
	    poll.setAnonymous(jsonPoll.getBoolean("anonymous"));
	    poll.setDaysLeft(jsonPoll.getInt("daysleft"));
	    JSONObject jsonOptions = jsonPoll.getJSONObject("options");
	    Map<String, Integer> options = new HashMap<String, Integer>();
	    Iterator<String> keys = jsonOptions.keys();
		while(keys.hasNext()) {
			String option = keys.next();
			Integer votes = jsonOptions.getInt(option);
			options.put(option, votes);
		}
		poll.setOptions(options);
		JSONArray jsonVoters = jsonPoll.getJSONArray("voters");
		Set<String> voters = new HashSet<String>();
	    for (int i = 0; i < jsonVoters.length(); ++i)
	    	voters.add(jsonVoters.getString(i));
	    poll.setVoters(voters);
		JSONArray jsonComments = jsonPoll.getJSONArray("comments");
	    CommentBO comments = new CommentBO();
	    for (int i = 0; i < jsonComments.length(); ++i) {
	    	JSONObject actualComment = jsonComments.getJSONObject(i);
	    	CommentTransfer comment = new CommentTransfer();
	    	comment.setComment(actualComment.getString("comment"));
	    	comment.setId(actualComment.getInt("id"));
	    	comment.setUser(actualComment.getString("user"));
	    	comment.setLikes(actualComment.getInt("likes"));
	    	comment.setDislikes(actualComment.getInt("dislikes"));
	    	comments.insertComment(comment);
	    }
	    poll.setComments(comments);
	    return poll;
    }
    
    public void closePoll(String id) throws JSONException, FileNotFoundException {
    	JSONObject jsonPolls = new JSONObject(new JSONTokener(new FileInputStream(new File("resources/PollsDB.txt"))));
    	JSONObject jsonActivePolls = jsonPolls.getJSONObject("activepolls");
    	JSONObject jsonCompletedPolls = jsonPolls.getJSONObject("completedpolls");
    	jsonCompletedPolls.put(id, jsonActivePolls.get(id));
    	jsonActivePolls.remove(id);
    	PrintStream p = new PrintStream(new FileOutputStream(new File("resources/PollsDB.txt")));
    	p.print(jsonPolls.toString(3));
    	p.close();
    }
    
    public PollTransfer findPoll(String id) throws JSONException, FileNotFoundException {
    	JSONObject jsonPolls = new JSONObject(new JSONTokener(new FileInputStream(new File("resources/PollsDB.txt"))));
    	PollTransfer poll = findPoll(id, jsonPolls.getJSONObject("activepolls"));
    	if (poll == null) poll = findPoll(id, jsonPolls.getJSONObject("completedpolls"));
	    return poll;
    }

    public List<PollTransfer> getActivePolls() throws JSONException, FileNotFoundException {
    	JSONObject jsonPolls = new JSONObject(new JSONTokener(new FileInputStream(new File("resources/PollsDB.txt"))));
    	JSONObject jsonActivePolls = jsonPolls.getJSONObject("activepolls");
    	List<PollTransfer> activePolls = new ArrayList<PollTransfer>();
    	Iterator<String> keys = jsonActivePolls.keys();
		while(keys.hasNext()) {
		    String key = keys.next();
		    JSONObject actualPoll = jsonActivePolls.getJSONObject(key);
		    PollTransfer pollT = new PollTransfer();
			pollT.setId(key);
			pollT.setQuestion(actualPoll.getString("question"));
			pollT.setUser(actualPoll.getString("user"));
			pollT.setDaysLeft(actualPoll.getInt("daysleft"));
			JSONObject jsonOptions = actualPoll.getJSONObject("options");
		    Map<String, Integer> options = new HashMap<String, Integer>();
		    Iterator<String> optkeys = jsonOptions.keys();
			while(optkeys.hasNext()) {
				String option = optkeys.next();
				Integer votes = jsonOptions.getInt(option);
				options.put(option, votes);
			}
			pollT.setOptions(options);
			activePolls.add(pollT);
		}
        return activePolls;
    }
    
    public void getUserPolls(String idUser, JSONObject polls, List<PollTransfer> userPolls) {
    	Iterator<String> keys = polls.keys();
		while(keys.hasNext() ){
		    String key = keys.next();
		    JSONObject jsonPoll = polls.getJSONObject(key);
		    if (idUser.equals(jsonPoll.getString("user"))) {
		    	PollTransfer pollT = new PollTransfer();
		    	pollT.setId(key);
		    	pollT.setQuestion(jsonPoll.getString("question"));
		    	pollT.setUser(jsonPoll.getString("user"));
		    	JSONObject jsonOptions = jsonPoll.getJSONObject("options");
			    Map<String, Integer> options = new HashMap<String, Integer>();
			    Iterator<String> optkeys = jsonOptions.keys();
				while(optkeys.hasNext()) {
					String option = optkeys.next();
					Integer votes = jsonOptions.getInt(option);
					options.put(option, votes);
				}
				pollT.setOptions(options);
		    	userPolls.add(pollT);
		    }
		}
    }
    
    public List<PollTransfer> getUserPolls(String idUser) throws JSONException, FileNotFoundException {
    	JSONObject jsonPolls = new JSONObject(new JSONTokener(new FileInputStream(new File("resources/ActivePollsDB.txt"))));
    	List<PollTransfer> userPolls = new ArrayList<PollTransfer>();
    	getUserPolls(idUser, jsonPolls.getJSONObject("activepolls"), userPolls);
    	getUserPolls(idUser, jsonPolls.getJSONObject("completedpolls"), userPolls);
        return userPolls;
    }

}
