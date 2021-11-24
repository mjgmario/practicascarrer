package negocio;

import java.util.Map;
import java.util.Set;

public class PollTransfer {
    private String id;
    private String question;
    private String user;
    private boolean anonymous;
    private int daysLeft;
    private Map<String, Integer> options;
    private Set<String> voters;
    private CommentBO comments;
    
    public String getId() {
        return this.id;
    }

    public void setId(String value) {
        this.id = value;
    }
    
    public String getQuestion() {
        return this.question;
    }
  
    public void setQuestion(String value) {
        this.question = value;
    }
   
    public String getUser() {
        return this.user;
    }
     
    public void setUser(String value) {
        this.user = value;
    }
  
    public boolean getAnonymous() {
        return this.anonymous;
    }
     
    public void setAnonymous(boolean value) {
        this.anonymous = value;
    }
    
    public int getDaysLeft() {
        return this.daysLeft;
    }
     
    public void setDaysLeft(int value) {
        this.daysLeft = value;
    }
    
    public Map<String, Integer> getOptions() {
        return this.options;
    }
   
    public void setOptions(Map<String, Integer> value) {
        this.options = value;
    }
    
    public Set<String> getVoters() {
        return this.voters;
    }
   
    public void setVoters(Set<String> value) {
        this.voters = value;
    }
    
    public CommentBO getComments() {
        return this.comments;
    }
    
    public void setComments(CommentBO value) {
        this.comments = value;
    }
    
}
