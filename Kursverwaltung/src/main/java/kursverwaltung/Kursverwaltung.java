package kursverwaltung;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;

import java.util.Scanner;

import database.Database;
import kurse.Kurs;
import kurse.Umschulung;
import teilnehmer.Teilnehmer;

public class Kursverwaltung {
	
	private static Database datenbank;
	
	public static void main(String[] args) {
		
		try {
			datenbank = new Database();
		} catch (SQLException e) {
			System.out.println("Verbindung fehlgeschlagen.");
		}
		
		try {
			datenbank.setSelectStatement("SELECT TNID,vorname,nachname,aufgenommen FROM tbl_teilnehmer");
		} catch (SQLException e) {
			System.out.println("Statement nicht ausfuehrbar.");
		}
		
		// wir brauchen einen Platz fuer unsere Teilnehmer
		// das koennen mehrere sein. Deshalb nehmen wir eine ArrayList
		ArrayList<Teilnehmer> teilnehmer = new ArrayList<>();

			
		ResultSet tn = datenbank.getSelectResults();
		
		try {
			while(tn.next() ) {
				
				Teilnehmer tmptn = new Teilnehmer();
				tmptn.setTNID(tn.getInt(1));
				tmptn.setVorname(tn.getString(2));
				tmptn.setNachname(tn.getString(3));
				Date datum = tn.getDate(4);
				DateFormat df = new SimpleDateFormat("dd.mm.yyyy hh:mm:ss");
				String tmpdate = df.format(datum);
				tmptn.setAufgenommen(tmpdate);
				teilnehmer.add(tmptn);
			}
		} catch (SQLException e1) {
			System.out.println("Fehler");
		}
		
		try {
			datenbank.setSQLUpdate("DELETE FROM tbl_teilnehmer WHERE TNID > 1");
		} catch (SQLException e) {
			System.out.println("Löschen fehlgeschlaft");
		}
		// Wir wissen bereits jetzt, dass wir Tastatureingaben lesen wollen.
		// Dazu verwenden wir den Scanner, weil der auch Eingaben in
		// verschiedene Datentypen umwandeln kann
		// Vorab initialisieren wir den Scanner indem wir ein Scannerobjekt erzeugen
		
		Scanner eingabe = new Scanner(System.in);
		String auswahl ="";
		
		do {
			System.out.println("Bitte wählen Sie:");
			System.out.println("a) Teilnehmer verwalten");
			System.out.println("b) Kurs verwalten");
			System.out.println("q) für verlassen");
			
			auswahl = eingabe.nextLine();
			
			switch(auswahl) {
			case "a":
				System.out.println("Wählen Sie:");
				System.out.println("a) für neuen TN anlegen");
				System.out.println("b) bestehenden TN bearbeiten");
				System.out.println("Jede andere Taste für zurück");
				// Für die Benutzerauswahl brauchen wir eine zusaetzliche Variable
				// Denn auswahl duerfen wir nicht ueberschreiben, damit wir zurueck kommen koennen
				String unterauswahl = eingabe.nextLine();
				switch(unterauswahl) {
				
				case "a":
					Teilnehmer tmptn = new Teilnehmer();
					System.out.println("Bitte eine TNID angeben: ");
					tmptn.setTNID(eingabe.nextInt());
					// Zeile zum Zuruecksetzen ueberspringen
					eingabe.nextLine();
					System.out.println("Bitte Vorname angeben: ");
					tmptn.setVorname(eingabe.nextLine());
					System.out.println("Bitte Nachname angeben: ");
					tmptn.setNachname(eingabe.nextLine());
					System.out.println("Bitte Datum der Aufnahme angeben: ");
					tmptn.setAufgenommen(eingabe.nextLine());
					teilnehmer.add(tmptn);
					System.out.println("Folgender Teilnehmer wurde erfolgreich: ");
					System.out.printf("TNID: %s\n", tmptn.getTNID());
					System.out.printf("Vorname: %s\n", tmptn.getVorname());
					System.out.printf("Nachname: %s\n", tmptn.getNachname());
					System.out.printf("Aufgenommen am: %s\n", tmptn.getAufgenommen());
					break;
				case "b":
					// wir geben erstmal alle Benutzer aus, die im System vorhanden sind, mit einer Auswahl ID
					System.out.println("Welcher Teilnehmer soll bearbeitet werden?");
					for(int i=0; i<teilnehmer.size(); i++) {
						// i+1 setze ich, damit dem Anwender keine 0 sondern mind. 1 gezeigt wird
						// i darf aber auf keinen Fall veraendert werden, da die ArrayList, wie jedes Array
						// bei Index 0 beginnt
						System.out.println((i+1) + ") " + teilnehmer.get(i).getVorname() + " " + teilnehmer.get(i).getNachname()
							+ " " + teilnehmer.get(i).getTNID());
					}
					int teilnehmerauswahl = eingabe.nextInt() - 1;
					eingabe.nextLine();
					System.out.println("Wählen Sie eine Aktion:");
					System.out.println("a) Den Teilnehmer löschen");
					System.out.println("b) Teilnehmerdaten ändern");
					System.out.println("c) Teilnehmerdaten einem Kurs zuweisen");
					System.out.println("Jede andere Taste = Abbrechen");
					unterauswahl = eingabe.nextLine();
					switch(unterauswahl) {
					
					case "a":
						// Mit der Methode remove koennen enzelne Elemente aus der ArrayList entfernt werden
						teilnehmer.remove(teilnehmerauswahl);
						System.out.println("Der Teilnehmer wurde erfolgreich gelöscht!");
						break;
					case "b":
						System.out.printf("TNID [%s] : \n", teilnehmer.get(teilnehmerauswahl).getTNID());
						int tmpint = eingabe.nextInt();
						eingabe.nextLine();
						if (tmpint > 0) {
							teilnehmer.get(teilnehmerauswahl).setTNID(tmpint);
						}
						System.out.printf("Vorname [%s] :\n", teilnehmer.get(teilnehmerauswahl).getVorname());
						String tmpeingabe = eingabe.nextLine();
						if(!tmpeingabe.isEmpty() || !tmpeingabe.isBlank()) {
							teilnehmer.get(teilnehmerauswahl).setVorname(tmpeingabe);
						}
						System.out.printf("Name [%s] :\n", teilnehmer.get(teilnehmerauswahl).getNachname());
						tmpeingabe = eingabe.nextLine();
						if(!tmpeingabe.isEmpty() || !tmpeingabe.isBlank()) {
							teilnehmer.get(teilnehmerauswahl).setNachname(tmpeingabe);
						}
						System.out.printf("Aufgenommen am [%s] :\n", teilnehmer.get(teilnehmerauswahl).getAufgenommen());
						tmpeingabe = eingabe.nextLine();
						if(!tmpeingabe.isEmpty() || !tmpeingabe.isBlank()) {
							teilnehmer.get(teilnehmerauswahl).setAufgenommen(tmpeingabe);
						}
					}
					break;
				}
				auswahl="";
				
				break;				
			case "b":
				break;
				
			case "q":
				System.out.println("Bye bye!");
				break;
			case "Q":
				System.out.println("Bye bye!");
				break;
			default:
				System.out.println("Du DOOF hast eine unguelige Asuwahl getroffen");
				
			}
		} while(!auswahl.equals("q") && !auswahl.equals("Q"));
		
	
	}

}

