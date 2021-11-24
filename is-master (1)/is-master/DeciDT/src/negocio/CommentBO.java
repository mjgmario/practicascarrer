package negocio;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;



public class CommentBO {
    private List<CommentTransfer> comments = new ArrayList<CommentTransfer>();

    public List<CommentTransfer> getCommentList() {
		return comments;
	}

    public CommentTransfer getComment(int id) {
    	ListIterator<CommentTransfer> listIt = comments.listIterator();
    	CommentTransfer c = null;
    	boolean deleted = false;
    	while(listIt.hasNext() && !deleted) {
    		CommentTransfer currentComment = listIt.next();
    		if (id == currentComment.getId()) c = currentComment;
    	}
    	return c;
	}
    
	public void setComments(List<CommentTransfer> comments) {
		this.comments = comments;
	}

	public void insertComment(CommentTransfer c) {
		this.comments.add(c);
    }
   
    public void deleteComment(CommentTransfer c) {
    	ListIterator<CommentTransfer> listIt = comments.listIterator();
    	boolean deleted = false;
    	while(listIt.hasNext() && !deleted) {
    		CommentTransfer currentComment = listIt.next();
    		if (c.getId() == currentComment.getId()) listIt.remove();
    	}
    }
  
    public void updateComment(final CommentTransfer c) {
    	ListIterator<CommentTransfer> listIt = comments.listIterator();
    	boolean deleted = false;
    	while(listIt.hasNext() && !deleted) {
    		CommentTransfer currentComment = listIt.next();
    		if (c.getId() == currentComment.getId()) {
    			currentComment.setComment(c.getComment());
    			currentComment.setUser(c.getUser());
    			currentComment.setLikes(c.getLikes());
    			currentComment.setDislikes(c.getDislikes());
    		}
    	}
    }

}
