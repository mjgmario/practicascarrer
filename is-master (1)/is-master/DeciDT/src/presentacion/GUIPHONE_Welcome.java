package presentacion;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GUIPHONE_Welcome extends JFrame{
	
	private ControllerPhone _ctrl;
	private JPanel mainPanel;
	
	public GUIPHONE_Welcome(ControllerPhone c, String id) {
		_ctrl = c;
		
		mainPanel = new JPanel();
		mainPanel.setBackground(SystemColor.info);
		mainPanel.setLayout(null);
		setContentPane(mainPanel);
		
		JLabel lblBienvenido = new JLabel("Bienvenido,");
		lblBienvenido.setFont(new Font("Century Schoolbook", Font.BOLD, 28));
		lblBienvenido.setBounds(71, 261, 207, 42);
		add(lblBienvenido);
		
		JLabel lblNewLabel = new JLabel(id);
		lblNewLabel.setFont(new Font("Century Schoolbook", Font.BOLD, 28));
		lblNewLabel.setBounds(71, 319, 161, 42);
		add(lblNewLabel);
		
		addWindowListener(new WindowAdapter() {
			public void windowOpened(WindowEvent e) {
				Thread th=Thread.currentThread();
				try {
					th.sleep(1500);
				}catch(InterruptedException exc) {
					
				}
				_ctrl.setVisible_MenuInit();
				dispose();
			}
		});
		
		setSize(450,700);
		setResizable(false);
		setLocation(350,70);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}
