package presentacion;

import java.io.FileNotFoundException;
import java.util.List;

import org.json.JSONException;

import negocio.EnumSkill;
import negocio.PollTransfer;
import negocio.SAPoll;
import negocio.SAPoll_Imp;
import negocio.SAUser;
import negocio.SAUser_Imp;

public class ControllerPhone {
    private String actualUser;
    private GUIPHONE_Init guiPhone_Init;
    private GUIPHONE_SignUp guiPhone_SignUp;
    private GUIPHONE_LogIn guiPhone_LogIn;
    public GUIPHONE_Welcome guiPhone_Welcome;
    private GUIPHONE_MenuInit guiPhone_MenuInit;
    private GUIPHONE_CreatePoll guiPhone_CreatePoll;
    private GUIPHONE_CreatePoll2 guiPhone_CreatePoll2;
    private GUIPHONE_ShowOnePoll guiPhone_ShowOnePoll;
    private GUIPHONE_UserProfile guiPHONE_UserProfile;
    private GUIPHONE_CreateRoulette guiPhone_CreateRoulette;
    private GUIPHONE_CreateTournament guiPHONE_CreateTournament;
    
    public void setActualUser(String value) {
        actualUser = value;
    }
    
    public String getActualUser() {
        return actualUser;
    }
    
    public void signUp(String user,String password) throws IllegalArgumentException, JSONException, FileNotFoundException{
    	SAUser SA= new SAUser_Imp();
    	actualUser = SA.signUp(user,password);
    }

    public void logIn(String user, String password) throws JSONException, FileNotFoundException{
        SAUser SA= new SAUser_Imp();
        actualUser=SA.logIn(user,password);
    }
    
    public PollTransfer createPoll(String question, List<String> options, boolean anonimous, int daysLeft) throws FileNotFoundException {
    	SAPoll SA=new SAPoll_Imp();
    	return SA.createPoll(question, options, anonimous,actualUser, daysLeft);
    }
    
//    public int pickOption(List<String> options) {
//        return 0;
//    }

    public PollTransfer findPoll(String pollId) throws JSONException, FileNotFoundException {
    	SAPoll PollServices = new SAPoll_Imp();
    	return PollServices.getPoll(pollId);
    }
    
    public void vote(String pollId, String user, String option) throws JSONException, FileNotFoundException {
    	SAPoll PollServices = new SAPoll_Imp();
    	PollServices.vote(pollId, user, option);
    }

    public void addComment(String pollId, String user, String comment) throws JSONException, FileNotFoundException {
    	SAPoll PollServices = new SAPoll_Imp();
    	PollServices.addComment(pollId, user, comment);
    }

    public void likeOrDislikeComment(String pollId, int commentId, String user, boolean likeOrDislike) throws JSONException, FileNotFoundException {
    	SAPoll PollServices = new SAPoll_Imp();
    	PollServices.likeOrDislikeComment(pollId, commentId, likeOrDislike);
    	SAUser SA= new SAUser_Imp();
        SA.updateScore(user, likeOrDislike);
    }

    public List<PollTransfer> getActivePolls() throws JSONException, FileNotFoundException {
    	SAPoll PollServices = new SAPoll_Imp();
        return PollServices.getActivePolls();
    }
    
    public List<String> getUserFriends() {
        return null;
    }
    
    public List<PollTransfer> getUserPolls(String id) throws JSONException, FileNotFoundException {
    	SAPoll PollServices = new SAPoll_Imp();
        return PollServices.getUserPolls(id);
    }

    public EnumSkill calculateSkill() {
        return null;
    }

    public List<String> createTournament(final List<String> players) {
        return null;
    }

	public void setVisible_LogIn() {
		// TODO Auto-generated method stub
		if(guiPhone_LogIn==null) {
			guiPhone_LogIn= new GUIPHONE_LogIn(this);
		}
		else guiPhone_LogIn.setVisible(true);
	}

	public void setVisible_SignUp() {
		// TODO Auto-generated method stub
		if(guiPhone_SignUp==null) {
			guiPhone_SignUp= new GUIPHONE_SignUp(this);
		}
		else guiPhone_SignUp.setVisible(true);
	}
	
	public void setVisible_Init() {
		if(guiPhone_Init==null) {
			guiPhone_Init= new GUIPHONE_Init(this);
		}
		else guiPhone_Init.setVisible(true);
	}

	public void setVisible_MenuInit() {
		// TODO Auto-generated method stub
		if(guiPhone_MenuInit==null) {
			guiPhone_MenuInit=new GUIPHONE_MenuInit(this);
		}
		else guiPhone_MenuInit.setVisible(true);
	}

	public void setVisible_Welcome(String id) {
		// TODO Auto-generated method stub
		if(guiPhone_Welcome==null) {
			guiPhone_Welcome=new GUIPHONE_Welcome(this, id);
		}
		else guiPhone_Welcome.setVisible(true);
	}
	
	public void setVisible_CreatePoll() {
		// TODO Auto-generated method stub
		if(guiPhone_CreatePoll == null) {
			guiPhone_CreatePoll = new GUIPHONE_CreatePoll(this);
		}
		else guiPhone_CreatePoll.setVisible(true);
	}
	
	public void setVisible_CreatePoll2(boolean anonima,int num_options,String pregunta) {
		if(guiPhone_CreatePoll2 == null) {
			guiPhone_CreatePoll2 = new GUIPHONE_CreatePoll2(this,pregunta,num_options,anonima);
		}
		else guiPhone_CreatePoll2.setVisible(true);
	}
	
	public void setVisible_ShowOnePoll(String id) {
		if (guiPhone_ShowOnePoll != null) guiPhone_ShowOnePoll.dispose();
		guiPhone_ShowOnePoll = new GUIPHONE_ShowOnePoll(this, id);
	}
	
	public void closePoll(String pollId) throws JSONException, FileNotFoundException {
		SAPoll PollServices = new SAPoll_Imp();
    	PollServices.closePoll(pollId);
	}

}
