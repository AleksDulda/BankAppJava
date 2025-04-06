import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class BankaPersonel extends Kisi {

	private int personelID;

	ArrayList<Musteri> musteriListesi = new ArrayList<Musteri>();

	public BankaPersonel() {
	}

	public BankaPersonel(long tcKimlikNo, String ad, String soyad, String email, long telefonNumarasi, int personelID) {
		super(tcKimlikNo, ad, soyad, email, telefonNumarasi);
		this.personelID = personelID;
	}

	public ArrayList<Musteri> getMusteriListesi() {
		return musteriListesi;
	}

	public void setMusteriListesi(ArrayList<Musteri> musteriListesi) {
		this.musteriListesi = musteriListesi;
	}

	public void musteriOlustur(long tcKimlikNo, String ad, String soyad, String email, long telefonNumarasi,//MÜŞTERİ VERİSİ OLUŞUMUNDA KULLANILAN BİR METOD
			int musteriNumarasi,long kartNumarasi,double limit,double guncelBorc,double kullanilabilirLimit) {
		Musteri musteri = new Musteri(tcKimlikNo, ad, soyad, email, telefonNumarasi, musteriNumarasi);

		Random random = new Random();

		long min = 1000000000000L;
		long max = 9999999999999L;				//İBAN BİLGİSİ SİSTEMDE VAR OLAN BİR İBANLA AYNI OLMASIN DİYE YAPILAN BİR ADIMDIR
		long iban = min + (long) (Math.random() * (max - min));

		while (true) {// Farklı iban oluşsun diye
			boolean IBANVarMi = false;
			for (int k = 0; k < musteriListesi.size(); k++) {
				for (int l = 0; l < musteriListesi.get(k).hesaplar.size(); l++) {
					if (musteriListesi.get(k).hesaplar.get(l).iban == iban) {
						IBANVarMi = true;
						break;
					}
				}
				if (IBANVarMi) {
					iban = min + (long) (Math.random() * (max - min));
					break;
				}
			}
			if (!IBANVarMi) {
				break;
			}
		}

		musteri.ilkHesapAc(tcKimlikNo, musteriNumarasi, ad, soyad, iban);//GEREKLİ ATAMALAR VE MÜŞTERİ OLUŞUMLARI
		musteri.ilkKrediKartiOlustur(kartNumarasi,limit,guncelBorc,kullanilabilirLimit);
		// long iban, Date hesapAcilisTarihi, double toplamBakiye, String hesapBilgisi,
		// double islemMiktar,
		// String hesapTuru
		musteriListesi.add(musteri);//LİSTEYE EKLENME

		// System.out.println(ad+" "+soyad+" "+tcKimlikNo+" "+email+"
		// "+telefonNumarasi+" "+musteriNumarasi);
		// System.out.println("Sisteme başarıyla eklendi!");
	}

	public void musterileriYazdir() {//KOD KALABALIĞI OLUŞMASIN DİYE YAZIŞAN BİR METOD
		System.out.println("*****Musteri Listesi*****");
		System.out.println("Ad/Soyad/Tc/Email/TelefonNumarası/MüşteriNumarası");
		System.out.println(" ");
		for (int i = 0; i < musteriListesi.size(); i++) {
			System.out.println(musteriListesi.get(i).getAd() + " " + musteriListesi.get(i).getSoyad() + " "
					+ musteriListesi.get(i).getTcKimlikNo() + " " + musteriListesi.get(i).getEmail() + " "
					+ musteriListesi.get(i).getTelefonNumarasi() + " " + musteriListesi.get(i).getMusteriNumarasi());
		}
	}

	
	
	
	
	public Musteri musteriBul(long tcKimlikNo) {//SİSTEMDE KOLAYCA MÜŞTERİ BULMAK İÇİN TC ÜZERİNDEN YAPILAN SORGULAMA
		for (int i = 0; i < musteriListesi.size(); i++) {
			if (musteriListesi.get(i).getTcKimlikNo() == tcKimlikNo) {
				return musteriListesi.get(i);
			}
		}
		Musteri olmayanAdam = new Musteri(000L, "XXX", "XXX", "XXX", 000, 000);//BÖYLE BİR MÜŞTERİ YOKSA YAPILACAK İŞLEM

		return olmayanAdam;
	}

	@Override
	public String toString() {
		return "BankaPersonel [personelID=" + personelID + ", musteriListesi=" + musteriListesi + "]";
	}

}
