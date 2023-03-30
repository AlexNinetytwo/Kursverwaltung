package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import dataobjects.Teilnehmer;
import main.Database;

public class ModelTeilnehmer {
	
	Database db;
	ArrayList<Teilnehmer> teilnehmer = new ArrayList<>();
	
	public ModelTeilnehmer(Database db) {
		
		this.db = db;
		selectDatabase();
	}
	
	public List<Teilnehmer> getTeilnehmerList() {
		
		return teilnehmer;
	}
	
	public void update(Teilnehmer changedTN) {
		
		for(int i=0; i < teilnehmer.size(); i++ ) {
			
			if(teilnehmer.get(i).getTNID()==changedTN.getTNID()) {
				teilnehmer.set(i, changedTN);
				updateDatabase(i);
			}
		}
	}
	
	public void insertTeilnehmer(Teilnehmer newTN) {
		
		teilnehmer.add(newTN);
	}
	
	private void updateDatabase(int i) {
		
		String sql = "UPDATE tbl_teilnehmer SET vorname=?,nachname=?,aufgenommen=? WHERE TNID=?;";
		try {
			SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy | HH:mm:ss");
			Date datum = format.parse(teilnehmer.get(i).getAufgenommen());
			Object[] daten = {teilnehmer.get(i).getVorname(), teilnehmer.get(i).getNachname(), datum};
			db.setSQLUpdate(sql,  teilnehmer.get(i).getTNID(),daten);
			
		} catch (SQLException | ParseException ex) {
			JOptionPane.showMessageDialog(null, "Datenbankupdate fehlgeschlagen!");
			System.out.println(ex.getMessage());
			}
	}
	
	private void selectDatabase() {
		
		try {
			db.setSelectStatement("SELECT TNID, vorname, nachname, aufgenommen FROM tbl_teilnehmer");
			ResultSet tn = db.getSelectResults();
			
			while(tn.next())
			{
				Teilnehmer tmptn = new Teilnehmer();
				tmptn.setTNID(tn.getInt(1));
				tmptn.setVorname(tn.getString(2));
				tmptn.setNachname(tn.getString(3));
				Date datum = tn.getDate(4);
				DateFormat df = new SimpleDateFormat("dd.MM.yyyy | HH:mm:ss");
				String tmpdate = df.format(datum);
				tmptn.setAufgenommen(tmpdate);
				teilnehmer.add(tmptn);
		}
		} catch (SQLException e) {
			System.out.println("Datenbankabfrage fehlgeschlagen");
		}
	}
}



