package presentacion;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import javax.swing.UIManager;

public class GUIPHONE_LogIn extends JFrame{
  
    private ControllerPhone _ctrl;
    private JPanel mainPanel;
    private JButton logInbtn;
   
    public GUIPHONE_LogIn(ControllerPhone c) {
		// TODO Auto-generated constructor stub
    	super("DeciDT");
    	_ctrl=c;
    	initGUI();
	}
    public void initGUI() {
    	mainPanel = new JPanel();
    	mainPanel.setBackground(SystemColor.info);
    	mainPanel.setLayout(null);
    	setContentPane(mainPanel);
    	
    	JButton regresar = new JButton();
 	    regresar.setToolTipText("regresar");
 	    regresar.addActionListener(new ActionListener() {
 			public void actionPerformed(ActionEvent e) {;
 				getRootPane().getParent().setVisible(false);
 				_ctrl.setVisible_Init();
 			}
 	    });
 	    regresar.setBounds(0, 0, 60, 60);
 	    add(regresar);
    	
		JLabel icon = new JLabel("");
		icon.setIcon(new ImageIcon("resources/peopleBigger.png"));
		icon.setBounds(151, 88, 128, 135);
		add(icon);
		
		JLabel member = new JLabel("MEMBER LOGIN");
		member.setFont(new Font("Century Schoolbook", Font.BOLD, 18));
		member.setBounds(137, 239, 164, 20);
		add(member);
		
		JLabel userLabel = new JLabel("Usuario");
		userLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
		userLabel.setBounds(74, 325, 69, 20);
		add(userLabel);
		
		JTextField userTextField = new JTextField();
		userTextField.setBounds(74, 361, 240, 30);
		add(userTextField);
		userTextField.setColumns(10);
		userTextField.setFocusable(false);
		userTextField.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent arg0) {
				userTextField.setFocusable(true);
			}
			public void mouseEntered(MouseEvent arg0) {}
			public void mouseExited(MouseEvent arg0) {}
			public void mousePressed(MouseEvent arg0) {}
			public void mouseReleased(MouseEvent arg0) {}
		});
		
		JLabel lblNewLabel = new JLabel("*");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setBounds(329, 361, 18, 20);
		add(lblNewLabel);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a");
		lblContrasea.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
		lblContrasea.setBounds(74, 407, 95, 20);
		add(lblContrasea);
		
		JPasswordField passwordField = new JPasswordField();
		passwordField.setBounds(74, 443, 240, 30);
		add(passwordField);
		passwordField.setFocusable(false);
		passwordField.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent arg0) {
				passwordField.setFocusable(true);
			}
			public void mouseEntered(MouseEvent arg0) {}
			public void mouseExited(MouseEvent arg0) {}
			public void mousePressed(MouseEvent arg0) {}
			public void mouseReleased(MouseEvent arg0) {}
			
		});
		
		JLabel label = new JLabel("*");
		label.setForeground(Color.RED);
		label.setBounds(329, 448, 18, 20);
		add(label);
		
		JCheckBox cb = new JCheckBox("Recordar");
		cb.setBackground(SystemColor.info);
		cb.setForeground(new Color(0, 0, 0));
		cb.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 12));
		cb.setSelected(true);
		cb.setBounds(70, 485, 107, 20);
		add(cb);
		cb.setFocusable(false);
    	
    	logInbtn = new JButton("LOG IN");
    	logInbtn.setFont(new Font("Sitka Small", Font.PLAIN, 14));
    	logInbtn.setBackground(UIManager.getColor("FormattedTextField.selectionBackground"));
    	logInbtn.setBounds(122, 549, 149, 29);
    	logInbtn.setFocusable(false);
    	logInbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String id = userTextField.getText();
				String password = new String(passwordField.getPassword());
				if(id.equals("") || password.equals("")) {
					String message = "campo obligatorio.";
					if(id.equals(""))
						message = "Usuario: "+ message;
					else
						message = "Contraseña: " + message;
					JOptionPane.showMessageDialog(GUIPHONE_LogIn.this, message,"WARNING",JOptionPane.ERROR_MESSAGE);
					mainPanel.requestFocus();
				}
				else {
					//FUNCIONES
					//Si ha fallado, notificar si contraseña incorrecta o usuario incorrecto y poner los textField vacios
					try {
						_ctrl.logIn(id, password);
					}catch(Exception e) {
						JOptionPane.showMessageDialog(GUIPHONE_LogIn.this, e.getMessage(),"WARNING",JOptionPane.ERROR_MESSAGE);
						mainPanel.requestFocus();
						return;
					}
					_ctrl.setVisible_MenuInit();
					dispose();
				}
			}
		});
		add(logInbtn);
    
		setSize(450,700);
		setResizable(false);
		setLocation(350,70);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
    }

 
}
