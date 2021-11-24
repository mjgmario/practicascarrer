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
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import org.json.JSONException;

import negocio.CommentTransfer;
import negocio.PollTransfer;

public class GUIPHONE_CommentBoard extends JPanel {
	private ControllerPhone _ctrl;
	private String pollId;
	private List<CommentTransfer> comments;
	private JPanel tablon;
	private String sortBy = "Recientes";
	
	public GUIPHONE_CommentBoard(ControllerPhone c, PollTransfer poll, Dimension d) {
		_ctrl = c;
		pollId = poll.getId();
		comments = poll.getComments().getCommentList();
		tablon = new JPanel();
		setSize(d);
		initGUI();
	}
	
	public void refresh(PollTransfer poll) {
		comments = poll.getComments().getCommentList();
		tablon.removeAll();
		drawTablon();
		tablon.revalidate();
	}
	
	private void initGUI() {
		setLayout(null);
		setBackground(SystemColor.textHighlight);
		String[] sorts = {"Recientes", "Populares"};
		JComboBox<String> sort = new JComboBox<>(sorts);
		sort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sortBy = (String)sort.getSelectedItem();
				try {
					tablon.removeAll();
					refresh(_ctrl.findPoll(pollId));
					tablon.revalidate();
				} catch (JSONException | FileNotFoundException e1) {
					showErrorMessage();
				}
			}
		});
		sort.setBounds(25, 5, 100, 20);
		add(sort);
		
		JTextField writeComment = new JTextField();
		writeComment.setBounds(0, getHeight() - 50, getWidth() - 100, 50);
		add(writeComment);
		
		JButton addComment = new JButton("Publicar");
		addComment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {;
				try {
					_ctrl.addComment(pollId, _ctrl.getActualUser(), writeComment.getText());
					tablon.removeAll();
					refresh(_ctrl.findPoll(pollId));
					tablon.revalidate();
				} catch (JSONException | FileNotFoundException e1) {
					showErrorMessage();
				}
				writeComment.setText("");
			}
		});
		addComment.setBounds(getWidth() - 100, getHeight() - 50, 100, 50);
		add(addComment);
				
		drawTablon();
		
		JScrollPane scrollTablon = new JScrollPane(tablon);
		scrollTablon.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollTablon.setBounds(0, 30, getWidth(), getHeight() - 80);
		add(scrollTablon);
		
	}
	
	private void drawTablon() {
		tablon.setLayout(new BoxLayout(tablon, BoxLayout.Y_AXIS));
		tablon.setBackground(this.getBackground());
		
		if (sortBy.equals("Populares")) Collections.sort(comments, new SortByMostPopular());
		for (int i = comments.size() - 1; i >= 0; --i) {
	    	CommentTransfer comment = comments.get(i);
			List<String> trimmedComment = wrap(comment.getComment(), getWidth() - 75, new Font("Sitka Small", Font.PLAIN, 20));
	    	CommentButton commentBttn = new CommentButton(pollId, trimmedComment, comment.getId(), comment.getUser(), comment.getLikes(), comment.getDislikes());
	    	int fontHeight = getFontMetrics(new Font("Sitka Small", Font.PLAIN, 20)).getHeight();
	    	commentBttn.setMinimumSize(new Dimension(getWidth() - 50, (trimmedComment.size() * (fontHeight + 5) + 60)));
    		commentBttn.setPreferredSize(new Dimension(getWidth() - 50, (trimmedComment.size() * (fontHeight + 5) + 60)));
    		commentBttn.setMaximumSize(new Dimension(getWidth() - 50, (trimmedComment.size() * (fontHeight + 5) + 60)));
    		commentBttn.setAlignmentX(JComponent.CENTER_ALIGNMENT);
    		commentBttn.setFocusable(false);
	    	tablon.add(commentBttn);
	    	tablon.add(Box.createRigidArea(new Dimension(0, 10)));
	    }
	}
	
	private void showErrorMessage() {
		JOptionPane.showMessageDialog(getRootPane(), "Se ha producido un error al acceder a la votacion");
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
	
	private class CommentButton extends JPanel {
		private String pollId;
		private List<String> trimmedComment;
		private int id;
		private String user;
		private int likes;
		private int dislikes;
		
		JButton like;
		JButton dislike;
		
    	CommentButton(String pollId, List<String> trimmed, int id, String user, int likes, int dislikes) {
    		this.pollId = pollId;
    		this.trimmedComment = trimmed;
    		this.id = id;
			this.user = user;
			this.likes = likes;
			this.dislikes = dislikes;
			initGUI();
		}
    	    	
		private void initGUI() {
			setLayout(null);
			setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
			
			like = new JButton();
			like.setToolTipText("like");
			like.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {;
					try {
						_ctrl.likeOrDislikeComment(pollId, id, user, true);
					} catch (JSONException | FileNotFoundException e1) {
						showErrorMessage();
					}	
					++likes;
					repaint();
				}
			});
			like.setContentAreaFilled(false);
			add(like);
			
			dislike = new JButton();
			dislike.setToolTipText("dislike");
			dislike.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {;
					try {
						_ctrl.likeOrDislikeComment(pollId, id, user, false);
					} catch (JSONException | FileNotFoundException e1) {
						showErrorMessage();
					}	
					++dislikes;
					repaint();
				}
			});
			dislike.setContentAreaFilled(false);
			add(dislike);
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
			gr.drawString("Escrito por \"" + user + "\"", 8, 14);
			
			gr.setColor(Color.GREEN);
			gr.fillRect(10, getHeight() - 15, 5, 7);
			gr.fillPolygon(new int[] {13, 7, 18}, new int[] {getHeight() - 20, getHeight() - 15, getHeight() - 15}, 3);
			gr.drawString(Integer.toString(likes), 22, getHeight() - 10);
			like.setBounds(5, getHeight() - 25, 15, 20);
			
			gr.setColor(Color.RED);
			gr.fillRect(60, getHeight() - 15, 5, 7);
			gr.fillPolygon(new int[] {63, 57, 68}, new int[] {getHeight() - 20, getHeight() - 15, getHeight() - 15}, 3);
			gr.drawString(Integer.toString(dislikes), 72, getHeight() - 10);
			dislike.setBounds(55, getHeight() - 25, 15, 20);
			
			gr.setColor(Color.BLACK);
			gr.setFont(new Font("Sitka Small", Font.PLAIN, 20));
			int fontHeight = getFontMetrics(new Font("Sitka Small", Font.PLAIN, 20)).getHeight();
			for (int i = 0; i < trimmedComment.size(); ++i) {
				gr.drawString(trimmedComment.get(i), 8, (fontHeight + 5) * i + 50);
			}
		}
		
	}
    
//    private class SortByNewest implements Comparator<CommentTransfer> { 
//    	public int compare(CommentTransfer c1, CommentTransfer c2) { 
//            return c2.getId() - c1.getId();
//        } 
//    }
    
    private class SortByMostPopular implements Comparator<CommentTransfer> { 
    	public int compare(CommentTransfer c1, CommentTransfer c2) { 
            return c1.getLikes() - c1.getDislikes() - (c2.getLikes() - c2.getDislikes());
        } 
    }
	
}