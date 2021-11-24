package negocio;

import java.util.List;

public class UserTransfer {
    private String user;
    private String password;
    private int score;
    private List<String> friendList;

	public String getUser() {
        return this.user;
    }
   
    public void setUser(final String value) {
        this.user = value;
    }
  
    public String getPassword() {
        return this.password;
    }

    public void setPassword(final String value) {
        this.password = value;
    }
   
    public int getScore() {
        return this.score;
    }
   
    public void setScore(final int value) {
        this.score = value;
    }
   
    public List<String> getFriendList() {
        return this.friendList;
    }
   
    public void setFriendList(List<String> value) {
        this.friendList = value;
    }

}
