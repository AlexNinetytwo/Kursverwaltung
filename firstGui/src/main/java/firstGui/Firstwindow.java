package firstGui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Firstwindow extends JFrame{
	
	private JPanel contentRahmen;
	private JTextField TextFeld;

	public void BasisFenster() {
		
		setTitle("Unser erstes Fensert");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Position und Groesse bestimmen
		// Position auf dem Bildschirm von obern links 100 Pixel
		// Position von overn nach unten 100 Pixel
		// Vreite 300 Pixel
		// Hoehe 400 Pixel
		setBounds(100,100,300,400);
		// Einen Inhaltsrahmen erstellen
		contentRahmen = new JPanel();
		// Rahmen des Inhalts bestimmen
		contentRahmen.setBorder(new EmptyBorder(5,5,5,5));
		setContentPane(contentRahmen);
		// keinen Manager verwenden
		contentRahmen.setLayout(null);
		// Jetzt legen wir ein Textfeld
		TextFeld = new JTextField();
		TextFeld.setFont(new Font("Calibri", Font.PLAIN, 12));
		TextFeld.setBounds(10,45,130,25);
		contentRahmen.add(TextFeld);
		JLabel neuerTitel = new JLabel("Neuer Fenstertitel");
		neuerTitel.setFont(new Font("Calibri", Font.PLAIN, 12));
		neuerTitel.setBounds(10,10,130,25);
		contentRahmen.add(neuerTitel);
		
		JButton btAendern = new JButton("Ändern");
		btAendern.setFont(new Font("Calibri", Font.BOLD, 16));
		btAendern.setBounds(10,100,100,40);
		btAendern.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				aendereTitel();
			}
		});
		contentRahmen.add(btAendern);
		JButton btBeenden = new JButton("Beenden");
		btBeenden.setFont(new Font("Calibri", Font.BOLD, 16));
		btBeenden.setBounds(130,100,100,40);
		btBeenden.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				beendeApp();
			}
		});
		contentRahmen.add(btBeenden);
		
		String[] sprachen = new String[] {"Java","JavaScript","PHP","Python","C++","C#","Brainfuck"};
		JComboBox<String> combobox = new JComboBox<String>(sprachen);
		combobox.setFont(new Font("Calibri", Font.BOLD, 16));
		combobox.setBounds(130,160,100,40);
		contentRahmen.add(combobox);
		
		// Jetzt wird es kompliziert
		// Wir geben unserem eigenen Datentypen eine eigene toString Methode, damit eine sinnvolle Anzeige moeglich ist
		
		Teilnehmer user1 = new Teilnehmer();
		user1.setVorname("Max");
		user1.setNachname("Mustermann");
		user1.setTNID(1);
		Teilnehmer user2 = new Teilnehmer();
		user2.setVorname("Moritz");
		user2.setNachname("Musterdivers");
		user2.setTNID(2);
		
		JComboBox<Teilnehmer> combo2 = new JComboBox<Teilnehmer>();
		combo2.addItem(user1);
		combo2.addItem(user2);
		combo2.setFont(new Font("Calibri", Font.BOLD, 16));
		combo2.setBounds(10,220,250,40);
		contentRahmen.add(combo2);
		// Problem: Eine Array List kann nicht direkt uebergeben werden
		// Bei der direkten expliziten Typenumwandlung in ein Array
		// kommt es aber immer wieder zu Problemen
		// Deshalb
		
		ArrayList<Teilnehmer> tn = new ArrayList<Teilnehmer>();
		tn.add(user1);
		tn.add(user2);

		Teilnehmer[] tnArray = new Teilnehmer[tn.size()];
		tn.toArray(tnArray);

		JComboBox<Teilnehmer> combo3 = new JComboBox<Teilnehmer>(tnArray);
		combo3.setFont(new Font("Calibri", Font.BOLD, 16));
		combo3.setBounds(10,260,250,40);
		contentRahmen.add(combo3);
		
		// Direkter expliziter cast ohne Umweg ueber eigenes Array
		// Dtatt dessen definieren wir das DefaultComboBoxModell
		// Vorteile: kein zusaetzliches array erforderlich und dynamisch
		// eisetzbar (auch bei Veraenderung der Groesse)
		final JComboBox combo4 = new JComboBox<Teilnehmer>();
		combo4.setModel(new DefaultComboBoxModel<Teilnehmer>(tn.toArray(new Teilnehmer[0])));
		combo4.setFont(new Font("Calibri", Font.BOLD, 16));
		combo4.setBounds(10,300,250,40);
		contentRahmen.add(combo4);
		combo4.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				auswahl(combo4.getSelectedItem().toString() + " " + combo4.getSelectedIndex());
			}
		});
		
	}
	
	private void aendereTitel() {
		
		setTitle(TextFeld.getText());
	}
	
	private void beendeApp() {
		
		//Fenster schließen und Application beenden
		dispose();
		System.exit(0);
	}
	public void auswahl(String auswahl) {
		
		setTitle("Es wurde " + auswahl + " gewählt");
	}
}



















