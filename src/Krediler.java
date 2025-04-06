
public class Krediler {

	private int krediID;

	public double krediMiktari;

	public double taksitMiktari;

	public Krediler() {
	}

	public Krediler(int krediID, double krediMiktari, double taksitMiktari) {
		this.krediID = krediID;
		this.krediMiktari = krediMiktari;
		this.taksitMiktari = taksitMiktari;
	}

	public void kampanya(KrediKarti limit)
	{
		
	}
	
	public int getKrediID() {
		return krediID;
	}

	public void setKrediID(int krediID) {
		this.krediID = krediID;
	}

	@Override
	public String toString() {
		return "Krediler [krediID=" + krediID + ", krediMiktari=" + krediMiktari + ", taksitMiktari=" + taksitMiktari
				+ "]";
	}

	
}
