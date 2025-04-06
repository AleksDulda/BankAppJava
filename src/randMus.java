import java.util.Iterator;
import java.util.Random;

public class randMus {

	// BU CLASS MÜŞTERİ VERİSİ İÇİN KISA YOLLAR DENETLEMELERDEN SONRA VAR OLAN
	// BİLGİLERİN YANİ TCKİMLİK TELEFON NO VEYA İBAN NUMARALARI GİBİ BİLGİLERİN
	// BAŞKA HESAPLARLA AYNI OLMADAN OŞUUŞTURULMASI VE ATANMASI İŞLEMİNİ
	// GERÇEKLEŞTİRRİ

	public BankaPersonel musteriVerisi(BankaPersonel Personel_1) {

		Random random = new Random();

		String[] turkIsimleri = { "Ahmet", "Mehmet", "Şevval", "Fatma", "Ali", "Mustafa", "Doritos", "Hasan", "İbrahim",
				"İsmail", "Zeynep", "Emine", "Cemal", "Meral", "Murat", "Serkan", "Özge", "Gizem", "Cengiz", "Burak" };
		String[] turkSoyadlari = { "Yılmaz", "Demir", "Çelik", "Kara", "Kılıç", "Aydın", "Doğan", "Cubun", "Erdoğan",
				"Güneş", "Baş", "Karahan", "Şimşek", "Yavuz", "Korkmaz", "Yalçın", "Gültekin", "Arslan", "Bozkurt",
				"Bayraktar" };

		for (int i = 0; i < 10; i++) {

			Random rand = new Random();

			int randomIndex = rand.nextInt(turkIsimleri.length);
			int randomIndex1 = rand.nextInt(turkIsimleri.length);

			String ad = turkIsimleri[randomIndex];
			String soyad = turkSoyadlari[randomIndex1];
			String email = turkIsimleri[randomIndex] + turkSoyadlari[randomIndex1] + "@" + "btubank.com";

			long min = 10000000000L;
			long max = 99999999999L;
			long tcKimlikNo = rand.nextLong() % (max - min) + max;

			while (true) {// Aynı tckimlik numarası oluşmasın diye
				boolean tcKimlikNoVarMi = false;
				for (int k = 0; k < Personel_1.musteriListesi.size(); k++) {
					if (tcKimlikNo == Personel_1.musteriListesi.get(k).getTcKimlikNo()) {
						tcKimlikNoVarMi = true;
						break;
					}
				}
				if (tcKimlikNoVarMi) {
					tcKimlikNo = rand.nextLong() % (max - min) + max;
				} else {
					break;
				}
			}

			long min1 = 100000L;
			long max1 = 999999L;
			long telefonNumarasi = rand.nextLong() % (max1 - min1) + max1;

			while (true) {// Aynı telefon numarası oluşmasın diye
				boolean telefonNumarasiVarMi = false;
				for (int k = 0; k < Personel_1.musteriListesi.size(); k++) {
					if (telefonNumarasi == Personel_1.musteriListesi.get(k).getTelefonNumarasi()) {
						telefonNumarasiVarMi = true;
						break;
					}
				}
				if (telefonNumarasiVarMi) {
					telefonNumarasi = rand.nextLong() % (max1 - min1) + max1;
				} else {
					break;
				}
			}

			telefonNumarasi = Long.parseLong("532" + String.format("%07d", telefonNumarasi));

			int min2 = 1;
			int max2 = 10000;
			int musteriNumarasi = rand.nextInt(max2 - min2 + 1) + min2;

			while (true) {// Aynı müşteri numarası oluşmasın diye
				boolean musteriNumarasiVarMi = false;
				for (int k = 0; k < Personel_1.musteriListesi.size(); k++) {
					if (musteriNumarasi == Personel_1.musteriListesi.get(k).getMusteriNumarasi()) {
						musteriNumarasiVarMi = true;
						break;
					}
				}
				if (musteriNumarasiVarMi) {
					musteriNumarasi = rand.nextInt(max2 - min2 + 1) + min2;
				} else {
					break;
				}
			}
			Random r = new Random();
			long min7 = 100000000000L;
			long max7 = 999999999999L;
			long kartNumarasi = min7 + (long) (Math.random() * (max7 - min7));

			boolean kartNumarasiEslesmeVarMi = true;// AYNI KART NUMARASI OLUŞMASIN DİYE
			while (kartNumarasiEslesmeVarMi) {
				kartNumarasi = min7 + (long) (Math.random() * (max7 - min7));
				kartNumarasiEslesmeVarMi = false;
				for (int x = 0; x < Personel_1.musteriListesi.size(); x++) {
					for (int j = 0; j < Personel_1.musteriListesi.get(x).krediKartlari.size(); j++) {
						if (kartNumarasi == Personel_1.musteriListesi.get(x).krediKartlari.get(j).getKartNumarasi()) {
							kartNumarasiEslesmeVarMi = true;
							break;
						}
					}
					if (kartNumarasiEslesmeVarMi) {
						break;
					}
				}
			}
			double limit = r.nextInt(40001) + 1000; // 1000-50000 arasında rastgele bir değer

			// Rastgele borç oluşturma
			double guncelBorc = r.nextDouble() * limit; // Limit ile aynı ya da daha az bir değer

			// Kullanılabilir limiti hesaplama
			double kullanilabilirLimit = limit - guncelBorc;

			Personel_1.musteriOlustur(tcKimlikNo, ad, soyad, email, telefonNumarasi, musteriNumarasi, kartNumarasi,
					limit, guncelBorc, kullanilabilirLimit);

		}
		return Personel_1;

	}

	public int musteriNumarasiOlustur(BankaPersonel Personel_1) {// BU KISIM ELLE BİLGİ GİRİLİP (YANİ MENU 2) YENİ
																	// MÜŞTERİ GİRİLEN MÜŞTERİ İÇİN HİÇ VAR OLMAYAN BİR
																	// MÜŞTERİ NUMARASI ATAMASI YAPAR

		Random random = new Random();
		int min2 = 1;
		int max2 = 10000;
		int musteriNumarasi = random.nextInt(max2 - min2 + 1) + min2;

		while (true) {
			boolean musteriNumarasiVarMi = false;
			for (int k = 0; k < Personel_1.musteriListesi.size(); k++) {
				if (musteriNumarasi == Personel_1.musteriListesi.get(k).getMusteriNumarasi()) {
					musteriNumarasiVarMi = true;
					break;
				}
			}
			if (musteriNumarasiVarMi) {
				musteriNumarasi = random.nextInt(max2 - min2 + 1) + min2;
			} else {
				break;
			}
		}
		return musteriNumarasi;
	}

	public long KartNumarasiOlustur(BankaPersonel Personel_1) {// BU KISIM ELLE BİLGİ GİRİLİP (YANİ MENU 2) YENİ
																// MÜŞTERİ GİRİLEN MÜŞTERİ İÇİN HİÇ VAR OLMAYAN BİR
																// KART NUMARASI ATAMASI YAPAR
		long min7 = 100000000000L;
		long max7 = 999999999999L;
		long kartNumarasi = min7 + (long) (Math.random() * (max7 - min7));

		boolean kartNumarasiEslesmeVarMi = true;
		while (kartNumarasiEslesmeVarMi) {
			kartNumarasi = min7 + (long) (Math.random() * (max7 - min7));
			kartNumarasiEslesmeVarMi = false;
			for (int x = 0; x < Personel_1.musteriListesi.size(); x++) {
				for (int j = 0; j < Personel_1.musteriListesi.get(x).krediKartlari.size(); j++) {
					if (kartNumarasi == Personel_1.musteriListesi.get(x).krediKartlari.get(j).getKartNumarasi()) {
						kartNumarasiEslesmeVarMi = true;
						break;
					}
				}
				if (kartNumarasiEslesmeVarMi) {
					break;
				}
			}
		}
		return kartNumarasi;
	}

	public int randBak() {// SİSTEMDEKİ MÜŞTERİLER İÇİN RANDOM BAKİYE ATAMASIDIR!
		Random rand = new Random();

		int min3 = 10;
		int max3 = 1000;
		int randomBakiye = rand.nextInt((max3 - min3) + 1) + min3;
		return randomBakiye;
	}

}
