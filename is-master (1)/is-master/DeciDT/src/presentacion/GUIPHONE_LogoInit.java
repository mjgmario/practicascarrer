package presentacion;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUIPHONE_LogoInit extends JFrame{
	private ControllerPhone _ctrl;
	private JPanel mainPanel;
	private Image fondo;
	
	public GUIPHONE_LogoInit(ControllerPhone c) {
		super("DeciDT");
		_ctrl=c;
		initGUI();
	}
	
	public void initGUI() {
		addWindowListener(new WindowAdapter() {
			public void windowOpened(WindowEvent e) {
				Thread th=Thread.currentThread();
				try {
					th.sleep(2000);
				}catch(InterruptedException exc) {
					
				}
				_ctrl.setVisible_Init();
				dispose();
				
			}
		});
		
		fondo=new ImageIcon("resources/LOGO_DECIDT.png").getImage();
		mainPanel = new JPanel() {
			public void paint(Graphics g) {
				g.drawImage(fondo,0,0,getWidth(),getHeight(),this);
			}
		};
		mainPanel.setBackground(SystemColor.info);
		mainPanel.setLayout(null);
		setContentPane(mainPanel);
		
		setSize(450,700);
		setResizable(false);
		setLocation(350,70);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}
