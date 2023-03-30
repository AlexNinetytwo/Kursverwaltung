package view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.TeilnehmerController;
import dataobjects.Teilnehmer;

public class TeilnehmerAnlegen {
	
	private TeilnehmerController tnc;
	private BaseWindow popup;
	private JTextField text, text2, text3;

	public TeilnehmerAnlegen(TeilnehmerController tnc) {
		
		this.tnc = tnc;
		popup = new BaseWindow("Neuer Teilnehmer", false, 200, 200, 350, 350);
		// MIt dieser Einstellunge sorgen wir dafuer, dass nicht die ganze App beendet wird
		// wenn wir das Unterfenster schließen
		popup.getFrame().setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	public void generateWindow() {
		
		popup.setSubpanel("Center", "grid", Color.LIGHT_GRAY);
		
		JLabel label = new JLabel("Vorname");
		JLabel label2 = new JLabel("Nachname");
		JLabel label3 = new JLabel("Aufgenommen");
		
		label.setBounds(10,5,20,10);
		label2.setBounds(10,5,20,10);
		label3.setBounds(10,5,20,10);
		
		text = new JTextField("",15);
		text2 = new JTextField("",15);
		DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
		
		String datum = df.format(new Date());
		text3 = new JTextField(datum,20);
		
		// Hilfsrahmen in Form zweier Zeile
		// Erste Zeile Formular
		//Zweiter Zeile Buttons
		
		JButton ok = new JButton("OK");
		JButton cancel = new JButton("Abbrechen");
		ok.setBackground(Color.green);
		cancel.setBackground(Color.red);
		cancel.setForeground(Color.white);
		
		// Eventhandler zum schliessen des Fnsters hinzufuegen
		
		cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				popup.getFrame().dispose();
			}
		});
		
		ok.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Teilnehmer tmptn = new Teilnehmer();
				tmptn.setTNID(-1);
				tmptn.setVorname(text.getText());
				tmptn.setNachname(text2.getText());
				tmptn.setAufgenommen(text3.getText());
				tnc.update(tmptn);
				popup.getFrame().dispose();
			}
		});
		
		// Hifsrahen in Form zweier Zeilen
		// Erste Zeile Formular
		// Zweite Zeile Buttons

		JPanel line1 = new JPanel(new FlowLayout());
		JPanel line2 = new JPanel(new FlowLayout());
		
		// Zu r besseren Positionierung der Elemente innerhalb der Zeilen
		// Ein Grid zur Hilfe
		// Je Eingabemöglichkeit eine Zeile des halb 3 rows
		// Je Zeile ein Feld für Beschriftung und Eingabefeld
		// also 2 columns
		JPanel line1grid = new JPanel(new GridLayout(3,2));
		
		// Panel fuer Zeile 2 erstellen
		JPanel line2grid = new JPanel(new GridLayout(1,2));
		
		// Label und Textfelder in der Hilfsgrid einfuegen
		line1grid.add(label);
		line1grid.add(text);
		line1grid.add(label2);
		line1grid.add(text2);
		line1grid.add(label3);
		line1grid.add(text3);
		
		line2grid.add(ok);
		line2grid.add(cancel);
		
		// Hilfsgrid mit allen darin befindlichen Elementen der
		// Zeile 1 hinzufuegen
		
		line1.add(line1grid);
		line2.add(line2grid);

		
		// Zeile 1 und Zeile 2 nun an das Fenster uebergeben
		popup.setElement(line1,  "Center");
		popup.setElement(line2, "Center");
		popup.updateWindow();
	}
	
	
	public JFrame getFrame() {
		return popup.getFrame();
	}
	
	public boolean isShowing() {
		
		return popup.getFrame().isShowing();
	}
}
