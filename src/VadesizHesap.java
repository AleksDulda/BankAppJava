import java.util.Date;

public class VadesizHesap extends BankaHesap {

	public String hesapTuru;

	public double vadesizBakiye;

	public VadesizHesap() {
	}

	public VadesizHesap(String hesapTuru, double vadesizBakiye) {
		super();
		this.hesapTuru = hesapTuru;
		this.vadesizBakiye = vadesizBakiye;
	}

	public double paraTransfer(String hesapTuru, long iban, double vadesizBakiye, int islemMiktari) {
		return 0;
	}
	
	public double krediKartiBorcOdeme(double vadesizBakiye,KrediKarti guncelBorc) {
		return 0;
	}
	public double krediOdeme(int KrediID,Krediler krediMiktari, Krediler taksitMiktari) {
		return 0; 
	}
}
