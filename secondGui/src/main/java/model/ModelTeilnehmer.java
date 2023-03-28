package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
				DateFormat df = new SimpleDateFormat("dd.MM.yyyy | hh:mm:ss");
				String tmpdate = df.format(datum);
				tmptn.setAufgenommen(tmpdate);
				teilnehmer.add(tmptn);
		}
		} catch (SQLException e) {
			System.out.println("Datenbankabfrage fehlgeschlagen");
		}
	}
}



