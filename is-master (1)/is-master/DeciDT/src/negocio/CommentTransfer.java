package negocio;

public class CommentTransfer { 
	private int id;
	private String comment;
    private String user;
    private int likes;
    private int dislikes;

    public int getId() {
    	return this.id;
    }
    
    public void setId(int value) {
    	this.id = value;
    }
    
    public String getComment() {
        return this.comment;
    }
 
    public void setComment(String value) {
        this.comment = value;
    }
  
    public String getUser() {
        return this.user;
    }
  
    public void setUser(String value) {
        this.user = value;
    }
   
    public int getLikes() {
        return this.likes;
    }

    public void setLikes(int value) {
        this.likes = value;
    }
    
    public int getDislikes() {
        return this.dislikes;
    }
  
    public void setDislikes(int value) {
        this.dislikes = value;
    }
}
