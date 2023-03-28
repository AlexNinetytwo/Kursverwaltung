package kurse;

public class Modul {

	private String bezeichnung;
	private String lerninhalt;
	private int dauerUE;
	
	
	public String getBezeichnung() {
		return bezeichnung;
	}
	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}
	public String getLerninhalt() {
		return lerninhalt;
	}
	public void setLerninhalt(String lerninhalt) {
		this.lerninhalt = lerninhalt;
	}
	public int getDauer() {
		return dauerUE;
	}
	public void setDauer(int dauer) {
		this.dauerUE = dauer;
	}
}
