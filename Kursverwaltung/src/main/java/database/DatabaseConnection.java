package database;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseConnection {


	
	public static void main(String[] args) {
		
		try {
			// Verbindung zur DB aufbauen
			// wenn dies fehlschaegt wird ein Fehler "geworfen", der dann im
			// catch bereich abgefangen wird
			Connection con = DriverManager.getConnection(
					"jdbc:mariadb://ns2.firmen-schulungen.info:3306/admin_kursverwaltung002",
					"admin_alex002",
					"z5qeB57?6"
					);
			// SQL Statement in eine Variable legen
			String statement = "SELECT 'Hello World!'";
			// Verbindung fuer das Senden und Ausfuehren eines Statements vorbereiten
			Statement stm = con.createStatement();
			// Statement senden und das Ergebnis als ResultSet abholen
			ResultSet results = stm.executeQuery(statement);
			// Zeiger auf die erste Ergebniszeile setzen
			results.first();
			// Aus dem Ergebnis nur die erste Ergebniszeile ausgeben.
			System.out.println(results.getString(1));
			// Einen Datensatz in die Tabelle Teilnehmer einfuegen
			// Die zuletzt vergeben TNID soll auf Bidlschirm ausgegeben werden
			// Die bisherige Connection wird weiterverwendet
			// Auch stm wird wieder verwertet
			
			statement = "INSERT INTO tbl_teilnehmer (vorname,nachname,aufgenommen) VALUES('Lara','Croft','1999-01-11')";
			
			stm = con.createStatement();
			// Execute Update ist auch fuer Inserts zu verwenden
			stm.executeUpdate(statement, Statement.RETURN_GENERATED_KEYS);
			
			ResultSet number = stm.getGeneratedKeys();
			
			number.first();
			System.out.printf("Teilnehmer mit der Nummer: %s angelegt!%n", number.getInt(1));
			
			// ReslutSetz sind fuer uns i.d.R. nicth gut nutzbar
			// deshalb ist es immer eine gute Idee eine ArrayList daraus zu machen
			ArrayList<String> vornamen = new ArrayList<>();
			
			// Abfragen der vorhandenen Teilnehmer
			// Auch hier verwenden wir die bestehenden Variablen und Verbindungen
			
			statement = "SELECT TNID,vorname,nachname,aufgenommen FROM tbl_teilnehmer;";
			
			stm = con.createStatement();
			
			results = stm.executeQuery(statement);
			
			// results durchlaufen und in die ArrayList uebertragen
			while(results.next()) {
				System.out.printf("Vorname: %s%n", results.getString(2));
				vornamen.add(results.getString(2));
			}
			// Ausgabe der ArrayList
			
			for (int i=0; i< vornamen.size(); i++) {
				System.out.printf("Vorname: %s%n", vornamen.get(i).toString());
			}
			
			// Schließen des Statements
			stm.close();
			// Schließen der Ergebnisse
			results.close();
			// Schließen der Datenbankverbindung
			con.close();
			
		} catch (SQLException e) {
			// Dieser catch Bereich wird abgearbeitet, wenn es im Try Bereich zu einem Fehler gekommen ist
			e.printStackTrace();
			System.out.println("Verbindung fehlgeschlagen");
		}

	}
}
