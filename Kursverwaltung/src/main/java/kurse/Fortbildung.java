package kurse;

public class Fortbildung extends Kurs{

	private boolean abschlusspruefung;
	private String zertifikatsart;
	
	
	public boolean isAbschlusspruefung() {
		return abschlusspruefung;
	}
	public void setAbschlusspruefung(boolean abschlusspruefung) {
		this.abschlusspruefung = abschlusspruefung;
	}
	public String getZertifikatsart() {
		return zertifikatsart;
	}
	public void setZertifikatsart(String zertifikatsart) {
		this.zertifikatsart = zertifikatsart;
	}
}
