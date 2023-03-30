package controller;

import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

import dataobjects.Teilnehmer;
import main.Database;
import model.ModelTeilnehmer;
import view.BaseWindow;
import view.TeilnehmerAnlegen;
import view.Teilnehmertabelle;
// Der Controller steuert nur den Programmablauf fuer einen oder mehrere Vorgaenge
public class TeilnehmerController {
	
	private TeilnehmerAnlegen tna;
	
	// Unser BaseWindow ist der View, der hier noch allgemein gehalten ist
	// ein spezial spezialisierter View koennte die gesamte Gestaltung der Fensters uebernehmen
	private Database db;
	private BaseWindow unserFenster = new BaseWindow("Testfenster", 600, 300, 800, 500, true);
	
	// Das Model ist hier ausschliesslich fuer die Teilnehmerdaten zustaendig
	// Es holt die gewooten Daten aus der Datenbank und bereitet diese fuer den View auf
	// Das Model wird aber auch das Schreiben der Daten in die Datenbank uebernehmen
	
	private ModelTeilnehmer teilnehmer;
	
	public TeilnehmerController(Database db) {
		
		tna = new TeilnehmerAnlegen(this);
		tna.generateWindow();
		this.db = db;
		teilnehmer = new ModelTeilnehmer(db);
		// View vorbereitung
		
		unserFenster.setSubpanel("Center", "grid", Color.cyan);
		
		// Muessen wir bearbeiten, wenn es unser Model wird
		// Wir uebergeben hier die Liste direkt aus dem getter des Models
		
		JTable tabelle = buildTable();
		
		tabelle.setAutoCreateRowSorter(true);
		
		JScrollPane scroll = new JScrollPane(tabelle);
		
		unserFenster.setSubpanel("North", "grid", Color.cyan);
		unserFenster.setElement(new JButton("Button 2"), "North");
		unserFenster.setSubpanel("West", "grid", Color.DARK_GRAY);
		JButton addUserButton = new JButton("TN anlegen");
		addUserButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) {
				if(!tna.isShowing())
					addTeilnehmerPopup();
			}
		});
		unserFenster.setElement(addUserButton, "West");
		unserFenster.setSubpanel("South", "grid", Color.green);
		unserFenster.setElement(new JButton("Button 4"), "South");
		unserFenster.setSubpanel("East", "grid", Color.BLACK);
		unserFenster.setElement(new JButton("Button 5"), "East");
		unserFenster.setElement(scroll, "Center");
		unserFenster.updateWindow();
	}
	
	private JTable buildTable() {
		TableModel tblModel = new Teilnehmertabelle(teilnehmer.getTeilnehmerList(), this);
		JTable tabelle = new JTable(tblModel);
		return tabelle;
	}
	
	public void update(Teilnehmer changedTN) {
		
		if(changedTN.getTNID()>0) {
			teilnehmer.update(changedTN);
			
		} else {
			teilnehmer.insertTeilnehmer(changedTN);
			unserFenster.updateWindow();
			}
	}
	
	
	private void addTeilnehmerPopup() {
		
		tna.getFrame().setVisible(true);
		
	}
	
	
		
	
	
	
	
}
