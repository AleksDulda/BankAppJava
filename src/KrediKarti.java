
public class KrediKarti {

	private long kartNumarasi;
	public double limit;
	public double guncelBorc;
	public double kullanılabilirLimit;

	
	
	public KrediKarti(long kartNumarasi, double limit, double guncelBorc, double kullanılabilirLimit) {
		this.kartNumarasi = kartNumarasi;
		this.limit = limit;
		this.guncelBorc = guncelBorc;
		this.kullanılabilirLimit = kullanılabilirLimit;
	}

	public void krediKartiEkle(long kartNumarasi, double limit, double guncelBorc, int kullanilabilirLimit) {

	}

	public void krediKartiSil(long kartNumarasi, double limit, double guncelBorc) {

	}

	public double kullanilabilirLimit(long kartNumarasi,double guncelBorc) {
	
		return 0.0;
	}

	public long getKartNumarasi() {
		return kartNumarasi;
	}

	public void setKartNumarasi(long kartNumarasi) {
		this.kartNumarasi = kartNumarasi;
	}

	

}
