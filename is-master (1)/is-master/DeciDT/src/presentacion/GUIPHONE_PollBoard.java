package presentacion;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.json.JSONException;
import negocio.PollTransfer;

public class GUIPHONE_PollBoard extends JPanel {
	ControllerPhone _ctrl;
	JPanel tablon;
	String sortBy = "Recientes";
	
	public GUIPHONE_PollBoard(ControllerPhone c, Dimension d) {
		_ctrl = c;
		tablon = new JPanel();
		setSize(d);
		setLayout(null);
		setBackground(SystemColor.textHighlight);
		initGUI();
	}
	
	public void refresh() {
		tablon.removeAll();
		drawTablon();
		tablon.revalidate();
	}
	
	private void initGUI() {
		String[] sorts = {"Recientes", "Populares"};
		JComboBox<String> sort = new JComboBox<>(sorts);
		sort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sortBy = (String)sort.getSelectedItem();
				tablon.removeAll();
				drawTablon();
				tablon.revalidate();
			}
		});
		sort.setBounds(25, 5, 100, 20);
		add(sort);
				
		drawTablon();
		
		JScrollPane scrollTablon = new JScrollPane(tablon);
		scrollTablon.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		//scrollTablon.getVerticalScrollBar().setPreferredSize(new Dimension(0,0));
		scrollTablon.setBounds(0, 30, getWidth(), getHeight() - 30);
		add(scrollTablon);
		
	}
		
	private void drawTablon() {
		tablon.setLayout(new BoxLayout(tablon, BoxLayout.Y_AXIS));
		tablon.setBackground(this.getBackground());
		
		List<PollTransfer> activePolls;
		try {
			activePolls = _ctrl.getActivePolls();
			if (sortBy.equals("Recientes")) Collections.sort(activePolls, new SortByNewest());
			else Collections.sort(activePolls, new SortByMostPopular());
	    	for (PollTransfer poll: activePolls) {
	    		List<String> trimmedQuestion = wrap(poll.getQuestion(), getWidth() - 75, new Font("Sitka Small", Font.PLAIN, 20));
	    		int votes = 0;
	    		for (Iterator<Map.Entry<String, Integer>> entries = poll.getOptions().entrySet().iterator(); entries.hasNext(); ) {
	                Map.Entry<String, Integer> entry = entries.next();
	                votes += entry.getValue();
	            }
	    		PollButton pollBtn = new PollButton(poll.getId(), poll.getUser(), votes, trimmedQuestion);
	    		int fontHeight = getFontMetrics(new Font("Sitka Small", Font.PLAIN, 20)).getHeight();
	    		pollBtn.setMinimumSize(new Dimension(getWidth() - 50, (trimmedQuestion.size() * (fontHeight + 5) + 60)));
	    		pollBtn.setPreferredSize(new Dimension(getWidth() - 50, (trimmedQuestion.size() * (fontHeight + 5) + 60)));
	    		pollBtn.setMaximumSize(new Dimension(getWidth() - 50, (trimmedQuestion.size() * (fontHeight + 5) + 60)));
	    		pollBtn.setAlignmentX(JComponent.CENTER_ALIGNMENT);
	    		pollBtn.setFocusable(false);
	    		tablon.add(pollBtn);
	    		tablon.add(Box.createRigidArea(new Dimension(0, 10)));
	    	}
		} catch (JSONException | FileNotFoundException e) {
			JOptionPane.showMessageDialog(this.getParent(), "Se ha producido un error al acceder a las votaciones");
		}
		
	}
	
	private List<String> wrap(String str, int length, Font font) {
		List<String> trimmedString = new ArrayList<>();
		String[] split = str.split(" ");
		StringBuilder line = new StringBuilder(split[0]);
		for (int i = 1; i < split.length; ++i) {
			if (getFontMetrics(font).stringWidth(line + split[i]) <= length) {
				line.append(" ");
				line.append(split[i]);
			}
			else {
				trimmedString.add(line.toString());
				line = new StringBuilder(split[i]);
			}
		}
		trimmedString.add(line.toString());
		return trimmedString;
	}
	
	private class PollButton extends JComponent {
		private String id;
		private String user;
		private int votes;
		private List<String> trimmedQuestion;
		
    	PollButton(String id, String user, int votes, List<String> trimmed) {
			this.id = id;
			this.user = user;
			this.votes = votes;
			this.trimmedQuestion = trimmed;
			initGUI();
		}
    	
		private void initGUI() {
			setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
			
			addMouseListener(new MouseListener() {
				public void mouseEntered(MouseEvent e) {}
				public void mouseClicked(MouseEvent e) {
					getRootPane().getParent().setVisible(false);
					_ctrl.setVisible_ShowOnePoll(id);;
				}
				public void mouseExited(MouseEvent e) {}
				public void mousePressed(MouseEvent e) {}
				public void mouseReleased(MouseEvent e) {}
			});
		}
		
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);		
			
			Graphics2D gr = (Graphics2D) g;
			gr.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			gr.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			
			// background
			gr.setColor(Color.WHITE);
			gr.fillRect(0, 0, getWidth(), getHeight());
			
			gr.setFont(new Font("Sitka Small", Font.PLAIN, 8));
			gr.setColor(Color.BLUE);
			gr.drawString("Preguntado por \"" + user + "\"", 8, 14);
			
			gr.setColor(Color.BLACK);
			gr.drawString("N de votos: " + votes, 8, getHeight() - 10);
			
			gr.setFont(new Font("Sitka Small", Font.PLAIN, 20));
			int fontHeight = getFontMetrics(new Font("Sitka Small", Font.PLAIN, 20)).getHeight();
			for (int i = 0; i < trimmedQuestion.size(); ++i) {
				gr.drawString(trimmedQuestion.get(i), 8, (fontHeight + 5) * i + 50);
			}
		}
		
	}
    
    private class SortByNewest implements Comparator<PollTransfer> { 
    	public int compare(PollTransfer p1, PollTransfer p2) { 
            if (p2.getId().length() - p1.getId().length() == 0) return p1.getId().compareTo(p2.getId());
            return p2.getId().length() - p1.getId().length();
        } 
    }
    
    private class SortByMostPopular implements Comparator<PollTransfer> { 
        public int compare(PollTransfer p1, PollTransfer p2) { 
            int p1Votes = 0;
            for (Iterator<Map.Entry<String, Integer>> entries = p1.getOptions().entrySet().iterator(); entries.hasNext(); ) {
                Map.Entry<String, Integer> entry = entries.next();
                p1Votes += entry.getValue();
            }
            int p2Votes = 0;
            for (Iterator<Map.Entry<String, Integer>> entries = p2.getOptions().entrySet().iterator(); entries.hasNext(); ) {
                Map.Entry<String, Integer> entry = entries.next();
                p2Votes += entry.getValue();
            }
            return p2Votes - p1Votes;
        } 
    }
	
}
