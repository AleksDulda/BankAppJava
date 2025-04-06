import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

import javax.swing.plaf.multi.MultiInternalFrameUI;

public class Musteri extends Kisi {

	Scanner scanner = new Scanner(System.in);

	private int musteriNumarasi;

	public Musteri() {

	}

	public Musteri(long tcKimlikNo, String ad, String soyad, String email, long telefonNumarasi, int musteriNumarasi) {
		super(tcKimlikNo, ad, soyad, email, telefonNumarasi);
		this.musteriNumarasi = musteriNumarasi;

	}

	public ArrayList<BankaHesap> hesaplar = new ArrayList<BankaHesap>();
	public ArrayList<KrediKarti> krediKartlari = new ArrayList<KrediKarti>();

	public void hesapEkle(long tcKimlikNo, String ad, String soyad,long iban) {//bu kısımda menu 2 kısmında kendi bilgilerinizi girerek oluşturduğunuz müşteri hesabı bilgileridir

		Random random = new Random();
		
		long maxMillis = 1672521599000L; // 31 Aralık 2023 saat 23:59:59 UTC, maksimum zaman
		long randomMillis = random.nextLong() % maxMillis;
		if (randomMillis < 0) {
			randomMillis += maxMillis; // negatif değerleri pozitife çevirir
		}
		Date randomDate = new Date(randomMillis);

		double toplamBakiye = 0.0;
		boolean flag_1 = false;
		String hesapBilgisi = " ";

		while (!flag_1) {
			System.out.println("Acmak istediginiz HesapBilgisini seciniz!");
			System.out.println("Maaş hesap icin 1'yi ,Normal hesap için 2'i");
			String secim1 = scanner.nextLine();

			switch (secim1) {//hesap bilgisi seçimi
			case "1":
				hesapBilgisi = "Maaş_Hesap";
				flag_1 = true;
				break;
			case "2":
				hesapBilgisi = "Normal_Hesap";
				flag_1 = true;
				break;
			default:
				System.out.println("Hatali Tuslama Yaptiniz! Lutfen uygun olan bir tuslama yapiniz!");

			}
		}
		double islemMiktar = 0.0;

		System.out.println("Acmak istediginiz hesap turunu seciniz!");
		System.out.println("Vadesiz hesap icin 1'i , Vadeli hesap için 2'i , Yatitim hesap icin 3'ü seciniz!");
		String secim = scanner.nextLine();

		boolean flag_2 = false;
		BankaHesap BankaHesap_1;

		while (!flag_2) {
			switch (secim) {//hesap turu seçimi
			case "1":
				String hesapTuru = "vadesiz_hesap";
				BankaHesap_1 = new BankaHesap(iban, randomDate, toplamBakiye, hesapBilgisi, islemMiktar, hesapTuru);
				hesaplar.add(BankaHesap_1);
				System.out.println(tcKimlikNo + " tcnolu bireyin vadesiz hesabı acilmistir!");
				flag_2 = true;
				break;
			case "2":
				hesapTuru = "vadeli_hesap";
				BankaHesap_1 = new BankaHesap(iban, randomDate, toplamBakiye, hesapBilgisi, islemMiktar, hesapTuru);
				hesaplar.add(BankaHesap_1);

				System.out.println(tcKimlikNo + " tcnolu bireyin vadeli hesabı acilmistir!");
				flag_2 = true;
				break;
			case "3":
				hesapTuru = "yatirim_hesap";
				BankaHesap_1 = new BankaHesap(iban, randomDate, toplamBakiye, hesapBilgisi, islemMiktar, hesapTuru);

				hesaplar.add(BankaHesap_1);
				System.out.println(tcKimlikNo + " tcnolu bireyin Yatirim hesabı acilmistir!");
				flag_2 = true;
				break;
			default:
				System.out.println("Hatali Tuslama Yaptiniz! Lutfen uygun olan bir tuslama yapiniz!");

			}
		}
	}

	public void ilkHesapAc(long tcKimlikNo, int musteriNumarasi, String ad, String soyad,long iban)//Bu kısım menüden ulaşılamaz çünkü sistemdeki müşteri verisi için oluşturulan kısımdır
//gerekli bilgiler random olarak oluşturulur ve müşteri oluşur
	{
		Random random = new Random();
		Random random2 = new Random();
		randMus alex = new randMus();
		Date randomDate = null;

		int maasMiNormalMi = random.nextInt(2);
		int vadeliMiVadesizMi = random2.nextInt(2);
		String secilenIfade = (maasMiNormalMi == 0) ? "Maaş_Hesap" : "Normal_Hesap";
		String secilenIfade1 = (vadeliMiVadesizMi == 0) ? "vadesiz_hesap" : "vadeli_hesap";

		

		long maxMillis = 1672521599000L; // 31 Aralık 2023 saat 23:59:59 UTC, maksimum zaman
		long randomMillis = random.nextLong() % maxMillis;
		if (randomMillis < 0) {
			randomMillis += maxMillis; // negatif değerleri pozitife çevirir
		}
		randomDate = new Date(randomMillis);
		String hesapBilgisi = secilenIfade;
		double islemMiktar = 0.0;
		String hesapTuru = secilenIfade1;
		long toplamBakiye = 0;
		BankaHesap BankaHesap_1 = new BankaHesap(iban, randomDate, toplamBakiye, hesapBilgisi, islemMiktar, hesapTuru);
		
		BankaHesap_1.hesapGuncelle();
		hesaplar.add(BankaHesap_1);

	}

	public void ilkKrediKartiOlustur(long kartNumarasi,double limit,double guncelBorc,double kullanilabilirLimit) {//bu kısım menüden oluşamaz sistemdeki müşteri verisine ait müşterilerin random kart oluşturulmuştur
		//çünkü gerekli işlemleri deneyebilmeniz için
		KrediKarti krediKarti_1 = new KrediKarti(kartNumarasi, limit, guncelBorc, kullanilabilirLimit);
		krediKartlari.add(krediKarti_1);
	}
	public void krediKartiEkle(long kartNumarasi) {//bu kısım istediğiniz müşteriye kart oluşturmanıza yarar menü 8den ulaşabilirsiniz
		Random r=new Random();
		randMus alex = new randMus();
		double limit = r.nextInt(40001) + 1000; // 1000-50000 arasında rastgele bir değer

		// Rastgele borç oluşturma
		double guncelBorc =0; // Limit ile aynı ya da daha az bir değer

		// Kullanılabilir limiti hesaplama
		double kullanilabilirLimit = limit - guncelBorc;
		
		KrediKarti krediKarti_1 = new KrediKarti(kartNumarasi, limit, guncelBorc, kullanilabilirLimit);
		krediKartlari.add(krediKarti_1);
		
		
		
		
	}
	
	public void KrediKartiBorcuOde(long kartNumarasi) {//bu kısım menu 9dan ulaşılabilir ve krediKartınızdaki borcu ödemenize yarar
		boolean varMi=false;
		int indextut=-1;
		
		
		for(int i=0; i<krediKartlari.size();i++)
		{
			if(krediKartlari.get(i).getKartNumarasi()==kartNumarasi)
			{
				varMi=true;
				indextut=i;
				
			}
		}
		if(!varMi) {
			System.out.println("Hesap Bulunamadı");
		}
		
		double odenenMiktar=0;
		while(indextut!=-1) {
			
			System.out.println("Ödemek istediğiniz miktarı giriniz! (Nokta yerine virgül kullanınız!)");//gerekli koşşullar kontrol edilir
			odenenMiktar=scanner.nextDouble();
			
			if (odenenMiktar<=0) {
				System.out.println("Lütfen negatif veya 0 tuşlamayınız");
			}
			else {
				if(odenenMiktar>krediKartlari.get(indextut).guncelBorc)
				{
					System.out.println("Lütfen güncel borcunuzdan yüksek bir miktar girmeyiniz!");
				}
				else {
					krediKartlari.get(indextut).guncelBorc=krediKartlari.get(indextut).guncelBorc-odenenMiktar;
					System.out.println("Borcunuz ödenmiştir!");
					
					break;
				}
			}
			
		}
	}
	
	
	public void hesapSil(int tcKimlikNo, int musteriNumarasi, String ad, String soyad) {//hesap sil metodu menu 4den ulaşılabilir ve bu sizin hesabınızdaki bakiyeyi kontrol ederk silip silemiyeceğinize karar verdirir
		System.out.println("Silmek istediginiz hesabı seciniz !");
		System.out.println("Vadesiz hesap icin 1'i , Vadeli hesap için 2'i , Yatitim hesap icin 3'ü seciniz!");
		String secim1 = scanner.nextLine();
		boolean flag = false;
		while (!flag) {
			switch (secim1) {
			case "1":
				for (int i = 0; i < hesaplar.size(); i++) {
					if (hesaplar.get(i).hesapBilgisi.equals("vadesiz_hesap")) {
						if (hesaplar.get(i).Kvadesizhesap.vadesizBakiye > 0) {
							System.out.println("Lütfen hesabınızdaki bakiyeyi çekiniz!");
						} else {
							hesaplar.remove(i);
							System.out.println("Hesabınız başarı ile silinmiştir!");
						}
					} else {
						System.out.println("Bu müşteriye ait bir vadesiz hesap bulunamadı !!!");
					}
				}
				flag = true;
				break;

			case "2":
				for (int i = 0; i < hesaplar.size(); i++) {
					if (hesaplar.get(i).hesapBilgisi.equals("vadeli_hesap")) {
						if (hesaplar.get(i).Kvadelihesap.vadeliBakiye > 0) {
							System.out.println("Lütfen hesabınızdaki bakiyeyi çekiniz!");
						} else if (hesaplar.get(i).Kvadelihesap.vadeliBakiye < 0) {
							System.out.println(
									"Hesabınızın kapatılabilmesi için hesabınızın borcunun bulunmaması gerekir!");
							System.out.println(
									"Seçili hesaba ait bir borç bilgisi bulunmaktadır. Lütfen borcunuzu ödeyiniz !!!");
						} else {
							hesaplar.remove(i);
							System.out.println("Hesabınız başarı ile silinmiştir!");
						}
					}

					else {
						System.out.println("Bu müşteriye ait bir vadeli hesap bulunamadı !!!");
					}
				}
				flag = true;
				break;
			case "3":
				for (int i = 0; i < hesaplar.size(); i++) {
					if (hesaplar.get(i).hesapBilgisi.equals("yatirim_hesap")) {
						if (hesaplar.get(i).KyatirimHesap.yatirimBakiye > 0) {
							System.out.println("Lütfen hesabınızdaki bakiyeyi çekiniz!");
						} else {
							hesaplar.remove(i);
							System.out.println("Hesabınız başarı ile silinmiştir!");
						}
					}

					else {
						System.out.println("Bu müşteriye ait bir yatırım hesabı bulunamadı !!!");
					}
				}
				flag = true;
				break;
			default:
				System.out.println("Hatali Tuslama Yaptiniz! Lutfen uygun olan bir tuslama yapiniz!");

			}
		}

	}

	public void paraIslemleri(ArrayList<Musteri> musteriler) {//bu kısım menu 5ten ulaşılabilir ve size hesaplar arası transferler yapmanıza sağlar

		int dongu = -1;
		while (dongu == -1) {
			System.out.println(
					"Nakit çekmek için 1'i Nakit yatırmak için 2'i Başka hesaba Havale yapmak için 3'ü tuşlayınız");//menü üçte proje adımlarında belirtilen kısımlar kulanılmıştır!! GEREKLİ KESİNTİLER!!!!
			int cevap1 = scanner.nextInt();
			switch (cevap1) {

			case 1:
				System.out.println("Çekmek istediğiniz miktarı tuşlayınız!");
				double cekMiktar = scanner.nextDouble();
				if (cekMiktar <= 0) {
					System.out.println("Lütfen negatif veya 0 TL çekmeyi denemeyeniz!");
					System.out.println("İşlem iptal edilmiştir!");
				} else {

					System.out.println("*****Hesaplar*****");
					System.out.println("HesapTuru   /   IBAN   /   ToplamBakiye");
					for (int p = 0; p < hesaplar.size(); p++) {
						System.out.println((p + 1) + ")" + " " + hesaplar.get(p).hesapTuru + "/" + "IBAN: "
								+ hesaplar.get(p).iban + "/" + hesaplar.get(p).toplamBakiye);
					}
					System.out.println("Para çekmek istediğiniz hesabın IBAN bilgisini giriniz!");
					long tutIBAN = scanner.nextLong();
					int flag = -1;
					for (int u = 0; u < hesaplar.size(); u++) {
						if (tutIBAN == hesaplar.get(u).iban) {
							flag = u;
						}

					}
					if (flag == -1) {
						System.out.println("Girdiğiniz IBAN bilgilerine ait bir hesap bulunamadı ");
						System.out.println("Para çekme işlemi sonlandırılmıştır!");

					}
					while (flag != -1) {

						if (hesaplar.get(flag).toplamBakiye >= cekMiktar) {
							hesaplar.get(flag).toplamBakiye = (hesaplar.get(flag).toplamBakiye) - (cekMiktar);
							System.out.println("Bakiyenizden " + cekMiktar + " TL çekilmiştir");
							break;
						} else {
							System.out.println("Yetersiz bakiye!");
							System.out.println("Para çekme işlemi sonlandırılmıştır!");
							dongu = 1;
							break;
						}
					}

				}
				dongu = 1;
				break;
			case 2:
				System.out.println("Yatırmak istediğiniz miktarı tuşlayınız!");
				double yatirMiktar = scanner.nextDouble();
				if (yatirMiktar <= 0) {
					System.out.println("Lütfen negatif veya 0 TL çekmeyi denemeyeniz!");
					System.out.println("İşlem iptal edilmiştir!");
				} else {
					System.out.println("*****Hesaplar*****");
					System.out.println("HesapTuru   /   IBAN   /   ToplamBakiye");
					for (int p = 0; p < hesaplar.size(); p++) {
						System.out.println((p + 1) + ")" + " " + hesaplar.get(p).hesapTuru + "/" + "IBAN: "
								+ hesaplar.get(p).iban + "/" + hesaplar.get(p).toplamBakiye);
					}
					System.out.println("Para Yatırmak istediğiniz hesabın IBAN bilgisini giriniz!");
					long tutIBAN1 = scanner.nextLong();
					int flag1 = -1;
					for (int u = 0; u < hesaplar.size(); u++) {
						if (tutIBAN1 == hesaplar.get(u).iban) {
							flag1 = u;
						}

					}
					if (flag1 == -1) {
						System.out.println("Girdiğiniz IBAN bilgilerine ait bir hesap bulunamadı ");
						System.out.println("Para Yatırma işlemi sonlandırılmıştır!");
					}
					while (flag1 != -1) {
						if (yatirMiktar < 0) {
							System.out.println("Yatırılacak miktar 0dan küçük olamaz!");
							System.out.println("Para yatırma işlemi sonlandırılmıştır!");
							break;
						} else {
							hesaplar.get(flag1).toplamBakiye = (hesaplar.get(flag1).toplamBakiye) + (yatirMiktar);
							System.out.println("Para yatırma işleminiz başarıyla tamamlanmıştır!");
							break;
						}
					}
				}

				dongu = 1;
				break;
			case 3:
				System.out.println("Lütfen göndermek istedğiniz miktarı giriniz !");
				double transfer = scanner.nextDouble();
				if (transfer <= 0) {
					System.out.println("Lütfen negatif veya 0 TL göndermeyi denemeyeniz!");
					System.out.println("İşlem iptal edilmiştir!");
				}

				else {
					System.out.println("Alıcı hesabın IBAN numarasını giriniz!");
					long aliciIBAN = scanner.nextLong();
					System.out.println("Gönderen hesabın IBAN numarasını giriniz!");
					long gondericiIBAN = scanner.nextLong();

					int aliciFlag = -1;
					int gondericiFlag = -1;
					int alicindex = 0;
					int gondericindex = 0;

					for (int y = 0; y < musteriler.size(); y++) {
						for (int i = 0; i < musteriler.get(y).hesaplar.size(); i++) {
							if (musteriler.get(y).hesaplar.get(i).iban == aliciIBAN) {
								aliciFlag = y;
								alicindex = i;
							}
						}
					}

					for (int y = 0; y < musteriler.size(); y++) {
						for (int i = 0; i < musteriler.get(y).hesaplar.size(); i++) {
							if (musteriler.get(y).hesaplar.get(i).iban == gondericiIBAN) {
								gondericiFlag = y;
								gondericindex = i;
							}
						}
					}

					if (aliciFlag != -1 && gondericiFlag != -1) {

						int dongu2 = -1;
						while (dongu2 == -1) {
							if (musteriler.get(gondericiFlag).hesaplar.get(gondericindex).hesapBilgisi == "Maaş_Hesap"
									&& musteriler.get(gondericiFlag).hesaplar
											.get(gondericindex).hesapTuru == "vadesiz_hesap") {

								System.out.println("Para gönderim işleminden kesinti alınmayacaktır!!");//GEREKLİ KESİNTİNİN YAPILMADIĞI YER
								System.out.println("Para transferi gerçekleştiriliyor....");

								if (musteriler.get(gondericiFlag).hesaplar
										.get(gondericindex).toplamBakiye >= transfer) {
									musteriler.get(gondericiFlag).hesaplar
											.get(gondericindex).toplamBakiye = musteriler.get(gondericiFlag).hesaplar
													.get(gondericindex).toplamBakiye - transfer;
									musteriler.get(gondericiFlag).hesaplar.get(gondericindex).hesapGuncelleIc();
									musteriler.get(aliciFlag).hesaplar
											.get(alicindex).toplamBakiye = musteriler.get(aliciFlag).hesaplar
													.get(alicindex).toplamBakiye + transfer;
									musteriler.get(aliciFlag).hesaplar.get(alicindex).hesapGuncelleIc();
									System.out.println(
											"Para have işlemi başarıyla tamamlanmıştır! Güncel bakiyenizi Hesapları görüntüle menüsünden kontrol edebilirsiniz!");
									dongu2 = 1;
								} else {
									System.out.println("Gönderici hesapta yeterli bakiye yok !");
								}
							} else if (musteriler.get(gondericiFlag).hesaplar
									.get(gondericindex).hesapBilgisi == "Normal_Hesap"
									&& musteriler.get(gondericiFlag).hesaplar
											.get(gondericindex).hesapTuru == "vadesiz_hesap") {
								System.out.println("Para gönderim işleminden 8TL kesinti alınacaktır!!");//GEREKLİ KESİNTİNİN YAPILDIĞI YER
								System.out.println("Para transferi gerçekleştiriliyor....");

								if (musteriler.get(gondericiFlag).hesaplar.get(gondericindex).toplamBakiye >= (transfer
										+ 8)) {
									musteriler.get(gondericiFlag).hesaplar
											.get(gondericindex).toplamBakiye = musteriler.get(gondericiFlag).hesaplar
													.get(gondericindex).toplamBakiye - (transfer + 8);
									musteriler.get(gondericiFlag).hesaplar.get(gondericindex).hesapGuncelleIc();
									musteriler.get(aliciFlag).hesaplar
											.get(alicindex).toplamBakiye = musteriler.get(aliciFlag).hesaplar
													.get(alicindex).toplamBakiye + transfer;
									musteriler.get(aliciFlag).hesaplar.get(alicindex).hesapGuncelleIc();
									System.out.println(
											"Para have işlemi başarıyla tamamlanmıştır! Güncel bakiyenizi Hesapları görüntüle menüsünden kontrol edebilirsiniz!");
									dongu2 = 1;
								} else {
									System.out.println("Gönderici hesapta yeterli bakiye yok !");
								}
							} else {
								System.out.println("Para gönderim işleminden kesinti alınmayacaktır!!");//PROJE ADIMLARINDA BU KOŞULA DÜŞENLER İÇİN BİR ŞEY BELİRTİLMEDİĞİ İÇİN KESİNTİ ALINMAMIŞTIR
								System.out.println("Para transferi gerçekleştiriliyor....");

								if (musteriler.get(gondericiFlag).hesaplar
										.get(gondericindex).toplamBakiye >= transfer) {
									musteriler.get(gondericiFlag).hesaplar
											.get(gondericindex).toplamBakiye = musteriler.get(gondericiFlag).hesaplar
													.get(gondericindex).toplamBakiye - transfer;
									musteriler.get(gondericiFlag).hesaplar.get(gondericindex).hesapGuncelleIc();
									musteriler.get(aliciFlag).hesaplar
											.get(alicindex).toplamBakiye = musteriler.get(aliciFlag).hesaplar
													.get(alicindex).toplamBakiye + transfer;
									musteriler.get(aliciFlag).hesaplar.get(alicindex).hesapGuncelleIc();
									System.out.println(
											"Para have işlemi başarıyla tamamlanmıştır! Güncel bakiyenizi Hesapları görüntüle menüsünden kontrol edebilirsiniz!");
									dongu2 = 1;
								} else {
									System.out.println("Gönderici hesapta yeterli bakiye yok !");
								}
							}
						}
					} else {
						System.out.println("Yanlış iban girişi yaptınız.");
					}
				}

				dongu = 1;
				break;
			default: {
				System.out.println("Hatalı tuşlama yaptınız !");
			}

			}

		}

	}

	public void paraIslemleriHesapSil(long iban, ArrayList<Musteri> musteriler) {//HESABI SİLMEYE KALKIŞTIĞINIZDA İÇİNDEKİ BAKİYEYİ ÇEKMEK VEYA BAŞKA HESABA AKTARMAK İSTEDİĞİNİZDE GERÇEKLEŞEN METOD

		System.out.println(
				"Hesabinizdaki tüm parayı nakit olarak çekmek için 1'i, Başka hesaba havale için 2'i tuşlayınız");
		int cevap = scanner.nextInt();
		int ibanFlag = -1;
		int ibanIndex = -1;
		switch (cevap) {

		case 1:
			for (int i = 0; i < musteriler.size(); i++) {//HESAP KONTROLLERİ
				for (int j = 0; j < musteriler.get(i).hesaplar.size(); j++) {
					if (iban == musteriler.get(i).hesaplar.get(j).iban) {
						ibanFlag = i;
						ibanIndex = j;
					}
				}

			}
			if (ibanFlag != -1 && ibanIndex != -1) {//VERİLEN IBANLARIN BİR HESAPLA UYUŞTUYSA GERÇEKLEŞECEK ADIM
				musteriler.get(ibanFlag).hesaplar
						.get(ibanIndex).toplamBakiye = musteriler.get(ibanFlag).hesaplar.get(ibanIndex).toplamBakiye
								- musteriler.get(ibanFlag).hesaplar.get(ibanIndex).toplamBakiye;
				musteriler.get(ibanFlag).hesaplar.get(ibanIndex).hesapGuncelleIc();
				musteriler.get(ibanFlag).hesaplar.remove(ibanIndex);
				System.out.println("Hesabınızdaki tüm parayı çektiniz!");
				System.out.println(iban + " IBAN bilgisine sahip hesap başarıyla silinmiştir");
			} else {
				System.out.println("Böyle bir hesap bulunamadı");
			}

			break;

		case 2:
			System.out.println("Alıcı hesabın IBAN bilgilerini giriniz!");
			long alıcıIBAN = scanner.nextLong();
			int alıcıIBANFlag = -1;
			int alıcıIBANIndex = -1;
			for (int k = 0; k < musteriler.size(); k++) {
				for (int l = 0; l < musteriler.get(k).hesaplar.size(); l++) {
					if (alıcıIBAN == musteriler.get(k).hesaplar.get(l).iban) {
						alıcıIBANFlag = k;
						alıcıIBANIndex = l;

					}
				}
			}
			int gondericiIBANFlag = -1;
			int gondericiIBANIndex = -1;

			for (int m = 0; m < musteriler.size(); m++) {
				for (int n = 0; n < musteriler.get(m).hesaplar.size(); n++) {
					if (iban == musteriler.get(m).hesaplar.get(n).iban) {
						gondericiIBANFlag = m;
						gondericiIBANIndex = n;

					}
				}
			}
			if (alıcıIBANFlag != -1 && alıcıIBANIndex != -1) {//GEREKLİ BAKİYE DÜZENLEMELERİ YAPILMIŞTIR
				System.out.println("Para transferi gerçekleştiriliyor...");
				musteriler.get(alıcıIBANFlag).hesaplar
						.get(alıcıIBANIndex).toplamBakiye += musteriler.get(gondericiIBANFlag).hesaplar
								.get(gondericiIBANIndex).toplamBakiye;
				musteriler.get(alıcıIBANFlag).hesaplar.get(alıcıIBANIndex).hesapGuncelleIc();

				musteriler.get(gondericiIBANFlag).hesaplar
						.get(gondericiIBANIndex).toplamBakiye -= musteriler.get(gondericiIBANFlag).hesaplar
								.get(gondericiIBANIndex).toplamBakiye;
				musteriler.get(gondericiIBANFlag).hesaplar.get(gondericiIBANIndex).hesapGuncelleIc();

				musteriler.get(gondericiIBANFlag).hesaplar.remove(gondericiIBANIndex);
				System.out.println(iban + " IBAN bilgisine sahip hesap başarıyla silinmiştir");

			} else {
				System.out.println("Bu iban bilgilerine sahip bir hesap bulunamadı ve Hesap silinemedi!");
			}

			break;
		}
	}

	public int getMusteriNumarasi() {
		return musteriNumarasi;
	}

	public void setMusteriNumarasi(int musteriNumarasi) {
		this.musteriNumarasi = musteriNumarasi;
	}

	@Override
	public String toString() {
		return "Musteri [musteriNumarasi=" + musteriNumarasi + "]";
	}

}
