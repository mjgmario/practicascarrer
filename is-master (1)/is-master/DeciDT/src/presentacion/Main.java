package presentacion;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.SwingUtilities;

import org.json.JSONException;

import integracion.DAOPoll;
import integracion.DAOPoll_Imp;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ControllerPhone ctrl=new ControllerPhone();
    	SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				GUIPHONE_LogoInit initWindow = new GUIPHONE_LogoInit(ctrl);				
			}
    		
    	});
	}

}
