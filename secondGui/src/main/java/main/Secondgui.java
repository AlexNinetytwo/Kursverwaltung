package main;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import controller.TeilnehmerController;

public class Secondgui {
	
	public static void main(String[] args) {
	
		try {
			Database datenbank = new Database();
			TeilnehmerController firstcontroller = new TeilnehmerController(datenbank);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Verbindung fehlgeschlagen!");
		}

	}

}
