package kurse;

import java.util.ArrayList;

import teilnehmer.Teilnehmer;

public class Kurs {

	/* Die ArrayList<Teilnehmer> ist eine Aggregation, da das fertige Teilnehmermodul von
	 * aussen uebergeben werden wird. Es existiert also unabhaengig vom Kurs
	 */
	private ArrayList<Teilnehmer> teilnehmer = new ArrayList<>();
	/*
	 * Diese ArrayList wird als Komposition verwendet. Die Objekte werden vollstaendig
	 * aus dieser Klasse heraus verwaltet. Ist der Kurs zerstoert, so gibt es auch die Module nicht
	 * mehr.
	 */
	private ArrayList<Modul> module = new ArrayList<>();
	private String bezeichnung;
	private String starttermin;
	private String endtermin;
	// protected ist in Java eine Luege!!!
	// protected ist in Java das, was im UML als package bekannt ist
	// dafuer gibt es package als Sichtbarkeit nicht
	protected boolean addModul(String bezeichnung, String lerninhalt, int dauerUE) {
		
		// Object vom Modul erzeugen
		Modul tmpmodul = new Modul();
		// Hilfvariable um festzuhalten ob ein Modul mit dem Namen existiert
		boolean gefunden = false;
		// Mit einer Schleife die ArrayList durchlaufen und jeweils das Modul
		// in die Variable modul schreiben
		for(Modul modul : module) {
			// in der Variable modul die Methode getBezeichnung aufrufen und den Ihalt
			// mit dem Wert auf nezeichnung pruefen. Wenn die Bezeichnung uebereinstimmt
			// wurde ein Modul mit dem gleichen Namen gefunden
			if(modul.getBezeichnung().equals(bezeichnung)) {
				
				// gefunden wird nun auf true gesetzt und die Sckleife verlassen
				gefunden = true;
				break;
			}
		}
		// Mit dem ! wird der ture oder false Wert umgekehrt. Die Bedingung ist insgesamt true
		// wenn der Wert in gefunden false ist.
		if(!gefunden) {
			tmpmodul.setBezeichnung(bezeichnung);
			tmpmodul.setDauer(dauerUE);
			tmpmodul.setLerninhalt(lerninhalt);
			this.module.add(tmpmodul);
		}
		return !gefunden;
	}
	
	public boolean modulHinzufuegen(String bezeichnung, String lerninhalt, int dauerUE) {
		if(!(bezeichnung.isEmpty() || lerninhalt.isEmpty() || dauerUE <= 0)) {
			if(addModul(bezeichnung, lerninhalt, dauerUE))
				return true;
		}
		return false;
	}
	
	
	
	public ArrayList<Modul> getModule() {
		
		return this.module;
	}
	public ArrayList<Teilnehmer> getTeilnehmer() {
		return teilnehmer;
	}
	public void setTeilnehmer(ArrayList<Teilnehmer> teilnehmer) {
		for(Teilnehmer person : teilnehmer) {
			this.teilnehmer.add(person);
		}
	}
	public void setTeilnehmer(Teilnehmer teilnehmer) {
		this.teilnehmer.add(teilnehmer);
	}
	public String getBezeichnung() {
		return bezeichnung;
	}
	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}
	public String getStarttermin() {
		return starttermin;
	}
	public void setStarttermin(String starttermin) {
		this.starttermin = starttermin;
	}
	public String getEndtermin() {
		return endtermin;
	}
	public void setEndtermin(String endtermin) {
		this.endtermin = endtermin;
	}
}
