import java.util.Date;

public class YatirimHesap extends BankaHesap{

	public String hesapTuru;

	public double yatirimBakiye;

	public String yatirimTuru;

	public float kur;

	public YatirimHesap() {
	}
	
	
	public YatirimHesap(String hesapTuru, int yatirimBakiye, String yatirimTuru, float kur) {
		super();
		this.hesapTuru = hesapTuru;
		this.yatirimBakiye = yatirimBakiye;
		this.yatirimTuru = yatirimTuru;
		this.kur = kur;
	}


	public YatirimHesap(String hesapTuru, double yatiirimBakiye, String YatirimTuru, float kur) {
	}

	public double paraEkle(int yatirimBakiye, String yatirimTuru, float kur) {
		return 0;
		
	}
	public double paraBoz(int yatirimBakiye, String yatirimTuru, float kur) {
		return 0;
		
	}
}
