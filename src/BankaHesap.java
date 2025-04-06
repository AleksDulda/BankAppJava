import java.util.Date;

import javax.security.auth.login.CredentialException;

public class BankaHesap {

	public long iban;
	public Date hesapAcilisTarihi;
	public double toplamBakiye = 0.0;
	public String hesapBilgisi;
	public double islemMiktar;
	public String hesapTuru;

	public VadeliHesap Kvadelihesap;
	public VadesizHesap Kvadesizhesap;
	public YatirimHesap KyatirimHesap;

	public BankaHesap() {

	}

	public BankaHesap(long iban, Date hesapAcilisTarihi, double toplamBakiye, String hesapBilgisi, double islemMiktar,
			String hesapTuru) {
		this.iban = iban;
		this.hesapAcilisTarihi = hesapAcilisTarihi;
		this.toplamBakiye = toplamBakiye;
		this.hesapBilgisi = hesapBilgisi;
		this.islemMiktar = islemMiktar;
		this.hesapTuru = hesapTuru;
		switch (hesapTuru) {
		case "vadesiz_hesap":
			Kvadesizhesap = new VadesizHesap();
			randMus alex = new randMus();

			Kvadesizhesap.vadesizBakiye = alex.randBak();//RANDOM BİR BAKİYE ATAMASI YAPILMIŞTIR
			break;

		case "vadeli_hesap":
			randMus alex1 = new randMus();
			Kvadelihesap = new VadeliHesap();
			if (hesapBilgisi == "Maaş_hesap") {

				Date suankiTarih = new Date();//BU KISIM FAİZ İŞLETMESİ KISMIDIR
				long farkMillisaniye = suankiTarih.getTime() - hesapAcilisTarihi.getTime();//HESABIN OLUŞUMUNDAN BU YANA GEÇEN ZAMAN HESAPLANIR 
				long birYilMillisaniye = 1000L * 60L * 60L * 24L * 365L;
				int gecenYilSayisi = (int) (farkMillisaniye / birYilMillisaniye);
				double faizOrani = 0.20;		//PROJE ADIMLARINDA BELİRTİLEN FAİZ ORANI İŞLETİLİR
				double bakiyeYeni = alex1.randBak() * Math.pow(1 + faizOrani, gecenYilSayisi);//BU KISIM HESABIN OLUŞMA TARİHİNDEN KODUN RUN EDİLME ZAMANINA KADAR GEÇEN SÜREDE YILLIK İŞLENEN FAİZİ TOPLAM BAKİYE ÜZERİNE KAYDEDEN BİR KOD PARÇASIDIR
				Kvadelihesap.vadeliBakiye = bakiyeYeni;
			} else {

				Date suankiTarih = new Date();
				long farkMillisaniye = suankiTarih.getTime() - hesapAcilisTarihi.getTime();
				long birYilMillisaniye = 1000L * 60L * 60L * 24L * 365L;
				int gecenYilSayisi = (int) (farkMillisaniye / birYilMillisaniye);
				double faizOrani = 0.10;//PROJE ADIMLARINDA BELİRTİLEN FAİZ ORANI İŞLETİLİR
				double bakiyeYeni1 = alex1.randBak() * Math.pow(1 + faizOrani, gecenYilSayisi);//AYNI İŞLEMİN NORMAL HESAP ORANI ÜZERİNDEN YAPILAN İŞLEMDİR
				Kvadelihesap.vadeliBakiye = bakiyeYeni1;

			}
			break;

		case "yatirim_hesap":
			KyatirimHesap = new YatirimHesap();
			KyatirimHesap.yatirimBakiye = 0.0;
			break;
		}

	}

	public void hesapGuncelle() {//BU KISIMLAR BAKİYE DEĞİŞİMLERİNİ CLASSLARA ATAMAYA YARAR
		if (hesapTuru == "vadesiz_hesap") {
			toplamBakiye = Kvadesizhesap.vadesizBakiye;
		} else if (hesapTuru == "vadeli_hesap") {
			toplamBakiye = Kvadelihesap.vadeliBakiye;
		} else if (hesapTuru == "yatirim_hesap") {
			toplamBakiye = KyatirimHesap.yatirimBakiye;
		} else {
			toplamBakiye = 0.0;
		}

	}

	public void hesapGuncelleIc() {//BU KISIMLAR BAKİYE DEĞİŞİMLERİNİ CLASSLARA ATAMAYA YARAR
		if (hesapTuru == "vadesiz_hesap") {
			Kvadesizhesap.vadesizBakiye = toplamBakiye;
		} else if (hesapTuru == "vadeli_hesap") {
			Kvadelihesap.vadeliBakiye = toplamBakiye;
		} else if (hesapTuru == "yatirim_hesap") {
			KyatirimHesap.yatirimBakiye = toplamBakiye;
		} else {
			toplamBakiye = 0.0;
		}

	}

	public void hesapGoruntuleme(int musteriNumarasi, String ad, String soyad, long iban, double toplamBakiye,
			String hesapBilgisi) {

	}

	@Override
	public String toString() {
		return "BankaHesap [iban=" + iban + ", hesapAcilisTarihi=" + hesapAcilisTarihi + ", toplamBakiye="
				+ toplamBakiye + ", hesapBilgisi=" + hesapBilgisi + ", islemMiktar=" + islemMiktar + ", hesapTuru="
				+ hesapTuru + "]";
	}

}
