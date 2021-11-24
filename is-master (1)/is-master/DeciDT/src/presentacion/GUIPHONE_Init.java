package presentacion;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GUIPHONE_Init extends JFrame{
	private ControllerPhone _ctrl;
	private JButton registerbtn;
	private JButton logInbtn;
	private JPanel mainPanel;
	
	
	public GUIPHONE_Init(ControllerPhone ctrl) {
		super("DeciDT");
		_ctrl=ctrl;
		initGUI();
	}
	public void initGUI() {
		
		mainPanel= new JPanel();
		mainPanel.setBackground(SystemColor.info);
		mainPanel.setLayout(null);
		setContentPane(mainPanel);
		
		JLabel lblNewLabel = new JLabel("Encuentra la soluci\u00F3n\r\n");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setFont(new Font("Century Schoolbook", Font.BOLD, 28));
		lblNewLabel.setBounds(36, 227, 354, 57);
		add(lblNewLabel);
		
		JLabel lblATusProblemas = new JLabel("a tus problemas");
		lblATusProblemas.setForeground(Color.BLACK);
		lblATusProblemas.setFont(new Font("Century Schoolbook", Font.BOLD, 28));
		lblATusProblemas.setBounds(36, 270, 247, 41);
		add(lblATusProblemas);
		
		ActionButton MiListenerBoton = new ActionButton();
//		JLabel n=new JLabel("");
//		n.requestFocus();
//		mainPanel.add(n);
		
		registerbtn = new JButton("Crear una cuenta");
		registerbtn.setForeground(Color.BLACK);
		registerbtn.setFont(new Font("Sitka Small", Font.PLAIN, 14));
		registerbtn.setBackground(Color.WHITE);
		registerbtn.setBounds(122, 353, 193, 41);
		registerbtn.setActionCommand("registrarse");
		registerbtn.addActionListener(MiListenerBoton);
		registerbtn.setFocusable(false);
		
		logInbtn = new JButton("Iniciar Sesi\u00F3n");
		logInbtn.setForeground(Color.BLACK);
		logInbtn.setBackground(Color.WHITE);
		logInbtn.setFont(new Font("Sitka Small", Font.PLAIN, 14));
		logInbtn.setBounds(122, 410, 193, 41);
		logInbtn.setActionCommand("iniciarSes");
		logInbtn.addActionListener(MiListenerBoton);
		logInbtn.setFocusable(false);
	
		add(logInbtn);
		add(registerbtn);
		
		setSize(450,700);
		setResizable(false);
		setLocation(350,70);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	
		//_ctrl.setVisibleLogIn();
	}
	
	private class ActionButton implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			switch(e.getActionCommand()) {
				case "registrarse":
					dispose();
					_ctrl.setVisible_SignUp();
				break;
				case "iniciarSes":
					dispose();
					_ctrl.setVisible_LogIn();
					break;
			}
		}	
	}
	
}
