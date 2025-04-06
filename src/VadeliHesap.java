import java.util.Date;

public class VadeliHesap extends BankaHesap {

	public String hesapTuru;
	public double vadeliBakiye;
	public float faizOrani;
	
	public VadeliHesap() {}


	public VadeliHesap(String hesapTuru, int vadeliBakiye, float faizOrani) {
		super();
		this.hesapTuru = hesapTuru;
		this.vadeliBakiye = vadeliBakiye;
		this.faizOrani = faizOrani;
	}


	@Override
	public String toString() {
		return "VadeliHesap [hesapTuru=" + hesapTuru + ", vadeliBakiye=" + vadeliBakiye + ", faizOrani=" + faizOrani
				+ "]";
	}
	
	
	
	
}
