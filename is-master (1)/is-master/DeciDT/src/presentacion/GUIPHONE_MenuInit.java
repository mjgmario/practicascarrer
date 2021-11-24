package presentacion;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class GUIPHONE_MenuInit extends JFrame{
    
    private ControllerPhone _ctrl;
    private JButton votacionbtn;
    private JButton ruletabtn;
    private JButton perfilbtn;
    private JButton torneobtn;
    private JButton notificacionesbtn;
    private GUIPHONE_PollBoard tablonVotaciones;
    
    public GUIPHONE_MenuInit(ControllerPhone c) {
		// TODO Auto-generated constructor stub
    	super("DeciDT");
    	_ctrl=c;
    	initGUI();
	}
    public void initGUI() {
    	JPanel panel = new JPanel();
    
    	ActionButton MiListenerBoton = new ActionButton();
		
		JPanel toolBar = new JPanel();
		toolBar.setBackground(Color.WHITE);
		toolBar.setBounds(0, 60, 450, 80);
		toolBar.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		toolBar.setLayout(null);
		
		votacionbtn = new JButton("");
		votacionbtn.setToolTipText("Crear Votacion");
		votacionbtn.setIcon(new ImageIcon("resources/crearVotacion.png"));
		votacionbtn.setBounds(116, 17, 50, 50);
		votacionbtn.setActionCommand("votacion");
		votacionbtn.addActionListener(MiListenerBoton);
		votacionbtn.setBorderPainted(false);
		toolBar.add(votacionbtn);
		
		ruletabtn = new JButton("");
		ruletabtn.setToolTipText("Crear Ruleta");
		ruletabtn.setIcon(new ImageIcon("resources/ruleta2.jpg"));
		ruletabtn.setBounds(199, 17, 50, 50);
		ruletabtn.setActionCommand("ruleta");
		ruletabtn.addActionListener(MiListenerBoton);
		ruletabtn.setBorderPainted(false);
		toolBar.add(ruletabtn);
		
		torneobtn = new JButton("");
		torneobtn.setToolTipText("Crear Torneo");
		torneobtn.setIcon(new ImageIcon("resources/torneo.jpg"));
		torneobtn.setBounds(282, 17, 50, 50);
		torneobtn.setActionCommand("torneo");
		torneobtn.addActionListener(MiListenerBoton);
		torneobtn.setBorderPainted(false);
		toolBar.add(torneobtn);
		
		notificacionesbtn = new JButton("");
		notificacionesbtn.setToolTipText("Notificaciones");
		notificacionesbtn.setIcon(new ImageIcon("resources/notificaciones.png"));
		notificacionesbtn.setBounds(365, 16, 50, 50);
		notificacionesbtn.setActionCommand("notificaciones");
		notificacionesbtn.addActionListener(MiListenerBoton);
		notificacionesbtn.setBorderPainted(false);
		toolBar.add(notificacionesbtn);
		
		perfilbtn = new JButton("");
		perfilbtn.setBackground(Color.WHITE);
		perfilbtn.setIcon(new ImageIcon("resources/user.png"));
		perfilbtn.setToolTipText("Mi perfil");
		perfilbtn.setBounds(33, 15, 50, 50);
		perfilbtn.setActionCommand("perfil");
		perfilbtn.addActionListener(MiListenerBoton);
		perfilbtn.setBorderPainted(false);
		toolBar.add(perfilbtn);
		
		JPanel titulo = new JPanel();
		titulo.setBackground(new Color(240, 230, 140));
		titulo.setBounds(0, 0, 444, 60);
		titulo.setLayout(null);
		titulo.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		JLabel tituloLabel = new JLabel("DeciDT");
		tituloLabel.setFont(new Font("Sitka Display", Font.BOLD | Font.ITALIC, 34));
		tituloLabel.setForeground(Color.BLACK);
		tituloLabel.setEnabled(true);
		tituloLabel.setBounds(170, 13, 110, 35);
		titulo.add(tituloLabel);

		JButton cerrarSesion = new JButton();
		cerrarSesion.setToolTipText("Cerrar Sesion");
		cerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {;
				_ctrl.setActualUser(null);
				getRootPane().getParent().setVisible(false);
				_ctrl.setVisible_Init();
			}
		});
		cerrarSesion.setBounds(0, 0, 60, 60);
		titulo.add(cerrarSesion);
		
		JButton refresh = new JButton();
		refresh.setToolTipText("Refresh");
		refresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {;
				tablonVotaciones.refresh();
			}
		});
		refresh.setBounds(384, 0, 60, 60);
		titulo.add(refresh);
		 
		tablonVotaciones = new GUIPHONE_PollBoard(_ctrl, new Dimension(444, 460));
		tablonVotaciones.setLocation(0, 140);
		
		JLabel logo = new JLabel("");
		logo.setIcon(new ImageIcon("resources/LogoMini.png"));
		logo.setBounds(200, 600, 50, 50);
		
		panel.add(titulo);
		panel.add(logo);
		panel.add(toolBar);
		panel.add(tablonVotaciones);
		panel.setBackground(SystemColor.textHighlight);
		panel.setLayout(null);
		
		setContentPane(panel);
		setSize(450,700);
		setResizable(false);
		setLocation(350,70);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

    }
    
//    public void update() {
//    }
    
    private class ActionButton implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			switch(e.getActionCommand()) {
			case "votacion":
				GUIPHONE_MenuInit.this.setVisible(false);
				_ctrl.setVisible_CreatePoll();
				break;
			case "ruleta":
				JOptionPane.showMessageDialog(GUIPHONE_MenuInit.this,"Esta opción se desarrollará en la siguiente versión de la app","Information",JOptionPane.INFORMATION_MESSAGE);
				break;
			case "torneo":
				JOptionPane.showMessageDialog(GUIPHONE_MenuInit.this,"Esta opción se desarrollará en la siguiente versión de la app","Information",JOptionPane.INFORMATION_MESSAGE);
				break;
			case "perfil":
				JOptionPane.showMessageDialog(GUIPHONE_MenuInit.this,"Esta opción se desarrollará en la siguiente versión de la app","Information",JOptionPane.INFORMATION_MESSAGE);
				break;
			case "notificaciones":
				JOptionPane.showMessageDialog(GUIPHONE_MenuInit.this,"Esta opción se desarrollará en la siguiente versión de la app","Information",JOptionPane.INFORMATION_MESSAGE);
				break;
			}
		}
    	
    }
    
}
