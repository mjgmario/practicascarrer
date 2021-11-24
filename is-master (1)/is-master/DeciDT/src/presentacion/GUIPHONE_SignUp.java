package presentacion;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GUIPHONE_SignUp extends JFrame{
	private JPanel mainPanel;
	private ControllerPhone _ctrl;
	private JButton signUpbtn;
	
	public GUIPHONE_SignUp(ControllerPhone c) {
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
		icon.setBounds(151, 68, 128, 128);
		icon.setIcon(new ImageIcon("resources/agregar-usuario.png"));
		add(icon);
		
		JLabel member = new JLabel("MEMBER SIGN UP");
		member.setFont(new Font("Century Schoolbook", Font.BOLD, 18));
		member.setBounds(126, 212, 188, 20);
		add(member);
		
		JLabel userLabel = new JLabel("Usuario");
		userLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
		userLabel.setBounds(74, 290, 69, 20);
		add(userLabel);
		
		JTextField userTextField = new JTextField();
		userTextField.setBounds(74, 326, 240, 30);
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
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a");
		lblContrasea.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
		lblContrasea.setBounds(74, 372, 95, 20);
		add(lblContrasea);
		
		JLabel lblIntroduzcaDeNuevo = new JLabel("Introduzca de nuevo la contrase\u00F1a");
		lblIntroduzcaDeNuevo.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
		lblIntroduzcaDeNuevo.setBounds(74, 442, 281, 20);
		add(lblIntroduzcaDeNuevo);
		
		JPasswordField passwordField = new JPasswordField();
		passwordField.setBounds(74, 396, 240, 30);
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
		
		JPasswordField passwordField_prove = new JPasswordField();
		passwordField_prove.setBounds(74, 472, 240, 30);
		add(passwordField_prove);
		passwordField_prove.setFocusable(false);
		passwordField_prove.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent arg0) {
				passwordField_prove.setFocusable(true);
			}
			public void mouseEntered(MouseEvent arg0) {}
			public void mouseExited(MouseEvent arg0) {}
			public void mousePressed(MouseEvent arg0) {}
			public void mouseReleased(MouseEvent arg0) {}
		});
		
		JCheckBox chckbxAceptarTrminos = new JCheckBox("Aceptar t\u00E9rminos");
		chckbxAceptarTrminos.setFont(new Font("Tahoma", Font.PLAIN, 12));
		chckbxAceptarTrminos.setBackground(SystemColor.info);
		chckbxAceptarTrminos.setSelected(false);
		chckbxAceptarTrminos.setBounds(70, 514, 139, 29);
		chckbxAceptarTrminos.setFocusable(false);
		add(chckbxAceptarTrminos);
		
		JLabel checkBoxcompleta = new JLabel("de condiciones");
		checkBoxcompleta.setFont(new Font("Tahoma", Font.PLAIN, 12));
		checkBoxcompleta.setBounds(98, 538, 95, 20);
		add(checkBoxcompleta);
		
		JLabel dot = new JLabel("*");
		dot.setForeground(Color.RED);
		dot.setBounds(329, 478, 18, 20);
		add(dot);
		
		JLabel label_1 = new JLabel("*");
		label_1.setForeground(Color.RED);
		label_1.setBounds(329, 401, 18, 20);
		add(label_1);
		
		JLabel label_2 = new JLabel("*");
		label_2.setForeground(Color.RED);
		label_2.setBounds(329, 331, 18, 20);
		add(label_2);
		
		signUpbtn = new JButton("SIGN UP");
		signUpbtn.setFont(new Font("Sitka Small", Font.PLAIN, 14));
		signUpbtn.setBackground(UIManager.getColor("FormattedTextField.selectionBackground"));
		signUpbtn.setBounds(131, 584, 129, 29);
		signUpbtn.setFocusable(false);
		signUpbtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(!chckbxAceptarTrminos.isSelected()) {
					JOptionPane.showMessageDialog(GUIPHONE_SignUp.this, "Debe aceptar los términos de las condiciones.","WARNING",JOptionPane.ERROR_MESSAGE);
					mainPanel.requestFocus();
				}
				
				else {
					String user = userTextField.getText();
					String password = new String(passwordField.getPassword());
					String password_1 = new String(passwordField_prove.getPassword());
					if(user.equals("") || password.equals("") || password_1.equals("")) {
						String message="campo obligatorio.";
						if(user.equals("")) message="Usuario: "+ message;
						else message="Contraseña: " + message;
						JOptionPane.showMessageDialog(GUIPHONE_SignUp.this, message,"WARNING",JOptionPane.ERROR_MESSAGE);
						requestFocus();
					}
					else {
						if(!password.equals(password_1)) {
							JOptionPane.showMessageDialog(GUIPHONE_SignUp.this, "Contraseñas diferentes. Asegurese de que son la misma.","WARNING",JOptionPane.ERROR_MESSAGE);
							mainPanel.requestFocus();
						}
						else {
							//Ya estan probados todos los fallos. ACCEDER A BASE DE DATOS....
							//SI EL USUARIO YA EXISTE, SEGUIR EN LA MISMA PANTALLA MOSTRANDO UN CUADRO DE DIALOGO Y PONIENDO
							//TODO A VACIO.
							try {
								_ctrl.signUp(user, password);
							} catch(Exception exc) {
								JOptionPane.showMessageDialog(GUIPHONE_SignUp.this, exc.getMessage(),"WARNING",JOptionPane.ERROR_MESSAGE);
								requestFocus();
								userTextField.setText("");
								return;
							}
							_ctrl.setVisible_Welcome(user);
							dispose();
							
						}
					}
				}
			}
			
		});
		
		
		mainPanel.add(signUpbtn);
		
		setSize(450,700);
		setResizable(false);
		setLocation(350,70);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
//	public void setUserText(String s) {
//		userTextField.setText(s);
//	}
//	
//	public void setPasswordTexts(String s) {
//		passwordField.setText(s);
//		passwordField_prove.setText(s);
//	}
	
}
