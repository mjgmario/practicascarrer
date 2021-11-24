package presentacion;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

import org.json.JSONException;

import negocio.PollTransfer;

public class GUIPHONE_ShowOnePoll extends JFrame{
    private ControllerPhone _ctrl;
    private String pollId;
    private boolean voted;

    private String opcionElegida;
    private JPanel votacion;
    private GUIPHONE_CommentBoard comentarios;
    
   public GUIPHONE_ShowOnePoll(ControllerPhone c, String id) {
	   _ctrl = c;
	   pollId = id;
	   opcionElegida = new String();
	   initGUI();
	   setVisible(true);
   }
 
   private void initGUI() {
	   setLayout(null);
	   getContentPane().setBackground(SystemColor.textHighlight);
	   setSize(450,700);
	   setResizable(false);
	   setLocation(350,70);
	   setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   setVisible(true);
	   
	   JPanel titulo = new JPanel();
	   titulo.setBackground(new Color(240, 230, 140));
	   titulo.setBounds(0, 0, 450, 60);
	   titulo.setLayout(null);
	   titulo.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
	   JLabel tituloLabel = new JLabel("DeciDT");
	   tituloLabel.setFont(new Font("Sitka Display", Font.BOLD | Font.ITALIC, 34));
	   tituloLabel.setForeground(Color.BLACK);
	   tituloLabel.setEnabled(true);
	   tituloLabel.setBounds(170, 13, 110, 35);
	   titulo.add(tituloLabel);
	   add(titulo);
	   
	   JButton regresar = new JButton();
	   regresar.setToolTipText("regresar");
	   regresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {;
				getRootPane().getParent().setVisible(false);
				_ctrl.setVisible_MenuInit();
			}
	   });
	   regresar.setBounds(0, 0, 60, 60);
	   titulo.add(regresar);
	   
	   JButton refresh = new JButton();
	   refresh.setToolTipText("Refresh");
	   refresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {;
				refresh();
			}
	   });
	   refresh.setBounds(384, 0, 60, 60);
	   titulo.add(refresh);
	   
	   JButton close = new JButton();
	   close.setToolTipText("Close");
	   close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {;
				try {
					_ctrl.closePoll(pollId);
				} catch (JSONException | FileNotFoundException e1) {
					showMessage("Se ha producido un error al acceder a la votacion");
				}
			}
	   });
	   close.setBounds(324, 0, 60, 60);
	   titulo.add(close);
	   
	   PollTransfer poll = null;
	   try {
		   poll = _ctrl.findPoll(pollId);
		   close.setVisible(_ctrl.getActualUser().equals(poll.getUser()));
		   voted = _ctrl.getActualUser().equals(poll.getUser()) || poll.getVoters().contains(_ctrl.getActualUser());
		   votacion = new JPanel();
		   if (!voted) drawOptions(poll);
		   else drawResults(poll);
		   JScrollPane scrollOpciones = new JScrollPane(votacion);
		   scrollOpciones.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		   scrollOpciones.setBounds(12, 60, 420, 240);
		   add(scrollOpciones);
	   } catch (JSONException | FileNotFoundException e) {
		   showMessage("Se ha producido un error al acceder a la votacion");
	   }
	   
	   comentarios = new GUIPHONE_CommentBoard(_ctrl, poll, new Dimension(444, 350));
	   comentarios.setLocation(0, 300);
	   add(comentarios);
   	}
   
   private void drawOptions(PollTransfer poll) {
	   votacion.setBackground(getContentPane().getBackground());
	   votacion.setLayout(new BoxLayout(votacion, BoxLayout.Y_AXIS));
	   votacion.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black, 2), "Votacion", TitledBorder.LEFT, TitledBorder.TOP));
	   
	   List<String> trimmedQuestion = wrap(poll.getQuestion(), 387, new Font("Sitka Small", Font.PLAIN, 20));
	   for (int i = 0; i < trimmedQuestion.size(); ++i) {
		   JLabel label = new JLabel(trimmedQuestion.get(i));
		   label.setForeground(Color.WHITE);
		   label.setFont(new Font("Sitka Small", Font.PLAIN, 20));
		   votacion.add(label);
	   }
	   
	   ButtonGroup grupoOpciones = new ButtonGroup();
	   Iterator<String> it = poll.getOptions().keySet().iterator();
	   while (it.hasNext()) {
		   JRadioButton optBttn = new JRadioButton(it.next());
		   optBttn.setBackground(getContentPane().getBackground());
		   optBttn.setForeground(Color.WHITE);
		   optBttn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {;
					opcionElegida = optBttn.getText();
				}
			});
	       grupoOpciones.add(optBttn);
	       votacion.add(optBttn);
	   }	
	   
	   JButton votar = new JButton("Votar");
	   votar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {;
				try {
					if (opcionElegida == null) showMessage("Elige una de las opciones");
					else {
						_ctrl.vote(pollId, _ctrl.getActualUser(), opcionElegida);
						voted = true;
						votacion.removeAll();
						drawResults(_ctrl.findPoll(pollId));
						votacion.revalidate();
					}
				} catch (JSONException | FileNotFoundException e1) {
					showMessage("Se ha producido un error al acceder a la votacion");
				}	
			}
		});
	   votacion.add(votar);
   }
   
   private void refresh() {
	   try {
		   PollTransfer poll = _ctrl.findPoll(pollId);
		   votacion.removeAll();
		   if (!voted) drawOptions(poll);
		   else drawResults(poll);
		   votacion.revalidate();
		   comentarios.refresh(poll);
	   } catch (JSONException | FileNotFoundException e) {
		   showMessage("Se ha producido un error al acceder a la votacion");
	   }
   }
   
   private void drawResults(PollTransfer poll) {
	   votacion.setBackground(getContentPane().getBackground());
	   votacion.setLayout(new BoxLayout(votacion, BoxLayout.Y_AXIS));
	   votacion.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black, 2), "Votacion", TitledBorder.LEFT, TitledBorder.TOP));
	   
	   List<String> trimmedQuestion = wrap(poll.getQuestion(), 387, new Font("Sitka Small", Font.PLAIN, 20));
	   for (int i = 0; i < trimmedQuestion.size(); ++i) {
		   JLabel label = new JLabel(trimmedQuestion.get(i));
		   label.setForeground(Color.WHITE);
		   label.setFont(new Font("Sitka Small", Font.PLAIN, 20));
		   votacion.add(label);
	   }
	   
	   int totalVotes = 0;
	   for (Iterator<Map.Entry<String, Integer>> entries = poll.getOptions().entrySet().iterator(); entries.hasNext(); ) {
           Map.Entry<String, Integer> entry = entries.next();
           totalVotes += entry.getValue();
       }
	   
	   Iterator<String> it = poll.getOptions().keySet().iterator();
	   while (it.hasNext()) {
		   String option = it.next();
		   int optionVotes = poll.getOptions().get(option);
		   JLabel result = new JLabel("Opcion: " + option);
		   result.setForeground(Color.WHITE);
		   result.setFont(new Font("Sitka Small", Font.PLAIN, 14));
		   JLabel votes = new JLabel("    " + optionVotes + " vote/s (" + String.format("%.2f", Double.valueOf(optionVotes * 100) / Double.valueOf(totalVotes)) + "%)");
		   votes.setForeground(Color.WHITE);
		   votes.setFont(new Font("Sitka Small", Font.PLAIN, 14));
	       votacion.add(result);
	       votacion.add(votes);
	   }
	   
   }
   
   private void showMessage(String message) {
	   JOptionPane.showMessageDialog(getRootPane(), message);
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
   	
//    private void showPoll() {
//    }
//
//  
//    private void vote() {
//    }
//
//
//    private void addComment() {
//    }
//
//  
//    private void likeOrDislikeComment() {
//    }

}
