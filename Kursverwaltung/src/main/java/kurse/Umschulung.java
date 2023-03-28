package kurse;

public class Umschulung extends Kurs{

	private String praktikumstart;
	private String praktikumende;
	private String pruefungsort;
	private String anmeldefrist;
	
	
	public String getPraktikumstart() {
		return praktikumstart;
	}
	public void setPraktikumstart(String praktikumstart) {
		this.praktikumstart = praktikumstart;
	}
	public String getPraktikumende() {
		return praktikumende;
	}
	public void setPraktikumende(String praktikumende) {
		this.praktikumende = praktikumende;
	}
	public String getPruefungsort() {
		return pruefungsort;
	}
	public void setPruefungsort(String pruefungsort) {
		this.pruefungsort = pruefungsort;
	}
	public String getAnmeldefrist() {
		return anmeldefrist;
	}
	public void setAnmeldefrist(String anmeldefrist) {
		this.anmeldefrist = anmeldefrist;
	}

}
