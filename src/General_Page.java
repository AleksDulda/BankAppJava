import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

import javax.print.attribute.AttributeSetUtilities;

import java.awt.desktop.PrintFilesEvent;
import java.io.Closeable;
import java.nio.Buffer;
import java.text.SimpleDateFormat;

public class General_Page {

	public static void main(String[] args) {
		BankaPersonel Personel_1 = new BankaPersonel(55138377784L, "Aleks", "Dulda", "aleksdulda@btubank.com",
				5324363556L, 123);

		randMus randMus = new randMus();
		System.out.println("BTU Banka Hosgeldiniz!");
		System.out.println("**********************");
		Personel_1 = randMus.musteriVerisi(Personel_1);
		boolean flag_0 = false;
		int menu;
		while (!flag_0) {
			System.out.println(" ");
			System.out.println("|________________________|ANA MENU|________________________|");
			System.out.println(" ");

			System.out.printf(
					  "1) Musterileri görüntülemek için\n"
					+ "2)Hesap oluşturmak için\n"
					+ "3)Hesapları görüntülemek için \n" 
					+ "4)Hesap silmek için\n" 
					+ "5)Para işlemleri için\n"
					+ "6)Musteri numarasından hesaplara ve KrediKartlarına ulaşmak için\n"
					+ "7)1 Ocak 2000 Tarihinden sonra açılan hesapların bakiyeleri için\n"
					+ "8)Yeni Kredi Kartı oluşturmak için\n" 
					+ "9)Kredi Kartı borcu ödemek için\n"
					+ "10)Müşteri temsilcisine bağlanmak için\n" 
					+ "Tuşlamasını Yapınız!\n");
			Scanner scanner = new Scanner(System.in);
			menu = scanner.nextInt();

			switch (menu) {
			case 1: { // Bu kısımda sistemde otomatik oluşturulmuş birkaç müşteri bulunmakta
				Personel_1.musterileriYazdir();
				System.out.println(" ");
				System.out.println("**********************");

				break;
			}
			case 2: { // Bu kısımda ise eğerki yeni bir müşteri mesela siz kendinizi eklemek
						// isterseniz bu kısımda kendi bilgilerinize ait hesap oluşturabilirsiniz
				Random random = new Random();

				System.out.println("Lütfen istenen bilgileri giriniz!");
				System.out.println("Lütfen Adınızı Giriniz");
				scanner.nextLine();
				String isim = scanner.nextLine();
				System.out.println("Lütfen Soyadıdınızı Giriniz");
				String soyad = scanner.nextLine();
				System.out.println("Lütfen Mailinizi Giriniz");
				String email = scanner.nextLine();
				System.out.println("Lütfen TCNo Giriniz");
				Long tcKimlik = scanner.nextLong();
				System.out.println("Lütfen TelefonNo Giriniz");
				Long telefonNo = scanner.nextLong();

				int musteriNumarasi = randMus.musteriNumarasiOlustur(Personel_1);
				int tut = -1;
				int q = 0;
				for (q = 0; q < Personel_1.musteriListesi.size(); q++) {
					if (tcKimlik == Personel_1.musteriListesi.get(q).getTcKimlikNo()) {
						tut = q;
					}
				}

				if (tut != -1) {
					System.out.println("Bu TcKimlikNo sahip bir müşteri kaydı bulunmaktadır");
					System.out.println(
							"Bu TcKimlikNo ile yeni hesap açmak için 1'i, yeni TcKimlikNo girmek için 2'i tuşlayınız");

					int cevap = scanner.nextInt();
					switch (cevap) {// eğer girdiğiniz tc sistemde var olan bir tc ile uyuşuyorsa o tcye ait
									// bilgiler (ad,soyad,vs...) sistemde var olanla değişir ve o tc sahip müşteriye
									// seçilen hesap açılır
					case 1:

						System.out.println("Bilgilerde gerekli düzenlemeler yapılıyor....");
						isim = Personel_1.musteriListesi.get(tut).getAd();
						soyad = Personel_1.musteriListesi.get(tut).getSoyad();
						email = Personel_1.musteriListesi.get(tut).getEmail();
						telefonNo = Personel_1.musteriListesi.get(tut).getTelefonNumarasi();
						musteriNumarasi = Personel_1.musteriListesi.get(tut).getMusteriNumarasi();
						long min = 1000000000000L;
						long max = 9999999999999L;
						long iban = min + (long) (Math.random() * (max - min));

						while (true) {// Farklı iban oluşsun diye
							boolean IBANVarMi = false;
							for (int k = 0; k < Personel_1.musteriListesi.size(); k++) {
								for (int l = 0; l < Personel_1.musteriListesi.get(k).hesaplar.size(); l++) {
									if (Personel_1.musteriListesi.get(k).hesaplar.get(l).iban == iban) {
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

						Personel_1.musteriBul(tcKimlik).hesapEkle(tcKimlik, isim, soyad, iban);// ekleme yapılır
						break;

					case 2:

						System.out.println("Lütfen yeni bir TcKimlikNo giriniz!");// eğer girdiğiniz tc sistemdeki bir
																					// müşteriyle çelişirse sizin tc
																					// kısmını yanlış yazdığınız
																					// düşünülerek kendi girdiğiniz ad
																					// soy vs bilgiler yeni tc bilgisine
																					// taşınır
						tcKimlik = scanner.nextLong();
						for (int u = 0; u < Personel_1.musteriListesi.size(); u++) {
							if (musteriNumarasi == Personel_1.musteriListesi.get(u).getMusteriNumarasi()) {
								musteriNumarasi = randMus.musteriNumarasiOlustur(Personel_1);

							}

						}
						Musteri musteri1 = new Musteri(tcKimlik, isim, soyad, email, telefonNo, musteriNumarasi);
						long min1 = 1000000000000L;
						long max1 = 9999999999999L;
						long iban1 = min1 + (long) (Math.random() * (max1 - min1));

						while (true) {// Farklı iban oluşsun diye
							boolean IBANVarMi = false;
							for (int k = 0; k < Personel_1.musteriListesi.size(); k++) {
								for (int l = 0; l < Personel_1.musteriListesi.get(k).hesaplar.size(); l++) {
									if (Personel_1.musteriListesi.get(k).hesaplar.get(l).iban == iban1) {
										IBANVarMi = true;
										break;
									}
								}
								if (IBANVarMi) {
									iban1 = min1 + (long) (Math.random() * (max1 - min1));
									break;
								}
							}
							if (!IBANVarMi) {
								break;
							}
						}

						musteri1.hesapEkle(tcKimlik, isim, soyad, iban1);
						Personel_1.musteriListesi.add(musteri1);// gerekli eklemeler yapılır
						break;
					}
				}

				else {// herhangi bir çelişki oluşmadan yapılan bilgi girişi esnasında
						// yönlendirildiğiniz kısımdır
					for (int u = 0; u < Personel_1.musteriListesi.size(); u++) {
						if (musteriNumarasi == Personel_1.musteriListesi.get(u).getMusteriNumarasi()) {
							musteriNumarasi = randMus.musteriNumarasiOlustur(Personel_1);
						}
					}

					Musteri musteri1 = new Musteri(tcKimlik, isim, soyad, email, telefonNo, musteriNumarasi);
					long min2 = 1000000000000L;
					long max2 = 9999999999999L;
					long iban2 = min2 + (long) (Math.random() * (max2 - min2));

					while (true) {// Farklı iban oluşsun diye
						boolean IBANVarMi = false;
						for (int k = 0; k < Personel_1.musteriListesi.size(); k++) {
							for (int l = 0; l < Personel_1.musteriListesi.get(k).hesaplar.size(); l++) {
								if (Personel_1.musteriListesi.get(k).hesaplar.get(l).iban == iban2) {
									IBANVarMi = true;
									break;
								}
							}
							if (IBANVarMi) {
								iban2 = min2 + (long) (Math.random() * (max2 - min2));
								break;
							}
						}
						if (!IBANVarMi) {
							break;
						}
					}

					musteri1.hesapEkle(tcKimlik, isim, soyad, iban2);
					Personel_1.musteriListesi.add(musteri1);// gerekli yönlendirme ve listelere ekleme
				}

			}

				break;
			case 3: {// Bu kısımda sistemde var olan tüm hesapları önünüze serer

				System.out.println("**********Müşterilerin hesapları**********");
				System.out.println("  ");
				System.out.println("Adı/Soyadı/TC/MusteriNumarasi");
				System.out.println("HesapTuru/HesapBilgisi/Açiliş Tarihi/IBAN");
				System.out.println(" ");
				for (int z = 0; z < Personel_1.musteriListesi.size(); z++) {
					System.out.println(
							Personel_1.musteriListesi.get(z).getAd() + "/" + Personel_1.musteriListesi.get(z).getSoyad()
									+ "/" + Personel_1.musteriListesi.get(z).getTcKimlikNo() + "/"
									+ Personel_1.musteriListesi.get(z).getMusteriNumarasi());
					for (int m = 0; m < Personel_1.musteriListesi.get(z).hesaplar.size(); m++) {

						if (Personel_1.musteriListesi.get(z).hesaplar.get(m).hesapTuru == "vadesiz_hesap") {
							System.out.println(Personel_1.musteriListesi.get(z).hesaplar.get(m).hesapTuru + "/"
									+ Personel_1.musteriListesi.get(z).hesaplar.get(m).hesapBilgisi + "/"
									+ Personel_1.musteriListesi.get(z).hesaplar.get(m).hesapAcilisTarihi + "/"
									+ Personel_1.musteriListesi.get(z).hesaplar.get(m).iban + "/"
									+ Personel_1.musteriListesi.get(z).hesaplar.get(m).toplamBakiye + "TL");
						}
						if (Personel_1.musteriListesi.get(z).hesaplar.get(m).hesapTuru == "vadeli_hesap") {
							System.out.println(Personel_1.musteriListesi.get(z).hesaplar.get(m).hesapTuru + "/"
									+ Personel_1.musteriListesi.get(z).hesaplar.get(m).hesapBilgisi + "/"
									+ Personel_1.musteriListesi.get(z).hesaplar.get(m).hesapAcilisTarihi + "/"
									+ Personel_1.musteriListesi.get(z).hesaplar.get(m).iban + "/"
									+ Personel_1.musteriListesi.get(z).hesaplar.get(m).toplamBakiye + "TL");

						}
						if (Personel_1.musteriListesi.get(z).hesaplar.get(m).hesapTuru == "yatirim_hesap") {
							System.out.println(Personel_1.musteriListesi.get(z).hesaplar.get(m).hesapTuru + "/"
									+ Personel_1.musteriListesi.get(z).hesaplar.get(m).hesapBilgisi + "/"
									+ Personel_1.musteriListesi.get(z).hesaplar.get(m).hesapAcilisTarihi + "/"
									+ Personel_1.musteriListesi.get(z).hesaplar.get(m).iban + "/"
									+ Personel_1.musteriListesi.get(z).hesaplar.get(m).toplamBakiye + "TL");
						}

					}
					System.out.println(" ");
				}
			}

				break;

			case 4: {// Bu kısımda var olan hesabınızı silmek için yapılmıştır
				System.out.println(" ");
				System.out.println("*****Hesap silme moduna hoşgeldiniz*****");
				System.out.println("Öncelikle TcKimlikNo giriniz!");
				long tctut = scanner.nextLong();

				int tut1 = -1;
				for (int z = 0; z < Personel_1.musteriListesi.size(); z++) {
					if (tctut == Personel_1.musteriListesi.get(z).getTcKimlikNo()) {
						tut1 = z;
					}

				}
				int hesapVarmi = 0;
				while (tut1 != -1) {
					if (tctut == Personel_1.musteriListesi.get(tut1).getTcKimlikNo()) {
						System.out.println(tctut + " Nolu müşteri'nin hesapları:");
						System.out.println("**********************");
						for (int s = 0; s < Personel_1.musteriListesi.get(tut1).hesaplar.size(); s++) {
							System.out.println(
									(s + 1) + ")" + " " + Personel_1.musteriBul(tctut).hesaplar.get(s).hesapTuru + "/"
											+ "IBAN: " + Personel_1.musteriBul(tctut).hesaplar.get(s).iban + "/"
											+ Personel_1.musteriBul(tctut).hesaplar.get(s).toplamBakiye);
							hesapVarmi = s + 1;
						}

					}

					break;

				}

				if (tut1 == -1) {// gerekli kontroller

					System.out.println(tctut + " TcKimlikNolu müşteri bulunamadı");
					System.out.println("**********************");

				}

				if (hesapVarmi != 0) { // silmek istenilen hesabın ibanı istenir
					int tut2 = -1;
					System.out.println("Silmek istediğiniz hesabın IBAN bilgilerini giriniz!");
					long tutiban = scanner.nextLong();
					for (int r = 0; r < Personel_1.musteriListesi.get(tut1).hesaplar.size(); r++) {
						if (Personel_1.musteriListesi.get(tut1).hesaplar.get(r).iban == tutiban) {
							tut2 = r;
						}
					}
					while (tut2 != -1) {// vadesiz hesapsa
						if (Personel_1.musteriListesi.get(tut1).hesaplar.get(tut2).hesapTuru == "vadesiz_hesap") {

							if (Personel_1.musteriListesi.get(tut1).hesaplar.get(tut2).toplamBakiye > 0) {
								System.out.println("Lütfen hesaptaki bakiyenizi başka hesaba aktarınız");
								Personel_1.musteriListesi.get(tut2).paraIslemleriHesapSil(tutiban, // bu kısım hesap
																									// silme metoduna
																									// yönlendirilir
																									// orada bakiye
																									// şartları ve
																									// kontrolleri
																									// yapılır
										Personel_1.musteriListesi);

							} else {

								System.out.println(tutiban + " IBAN bilgisine sahip hesap başarıyla silinmiştir");// gerekli
																													// şartlar
																													// tutarsa
																													// hesap
																													// silinir
								Personel_1.musteriListesi.get(tut1).hesaplar.remove(tut2);

							}

							break;
						}

						if (Personel_1.musteriListesi.get(tut1).hesaplar.get(tut2).hesapTuru == "vadeli_hesap") {// vadeli
																													// hesabı
																													// silme
																													// koşulları
																													// ve
																													// gerekli
																													// şartlar
																													// kontrol
																													// edilir

							if (Personel_1.musteriListesi.get(tut1).hesaplar.get(tut2).toplamBakiye > 0) {
								System.out.println("Lütfen hesaptaki bakiyenizi başka hesaba aktarınız");
								Personel_1.musteriListesi.get(tut2).paraIslemleriHesapSil(tutiban,
										Personel_1.musteriListesi);

							} else if (Personel_1.musteriListesi.get(tut1).hesaplar.get(tut2).toplamBakiye < 0) {
								System.out.println("Hesap silme işlemi başarısız!");
								System.out.println("Lütfen hesabınızdaki borcu ödeyiniz!");
								System.out.println("Para işlemleri menüsünden Nakit Para yatırabilirsiniz!");
							} else {
								System.out.println(tutiban + " IBAN bilgisine sahip hesap başarıyla silinmiştir");
								Personel_1.musteriListesi.get(tut1).hesaplar.remove(tut2);// bu kısım hesap silme
																							// metoduna yönlendirilir
																							// orada bakiye şartları ve
																							// kontrolleri yapılır
							}
							break;
						}
						if (Personel_1.musteriListesi.get(tut1).hesaplar.get(tut2).hesapTuru == "yatirim_hesap") {
							if (Personel_1.musteriListesi.get(tut1).hesaplar.get(tut2).toplamBakiye > 0) {
								System.out.println("Lütfen hesaptaki bakiyenizi başka hesaba aktarınız");
								Personel_1.musteriListesi.get(tut2).paraIslemleriHesapSil(tutiban,
										Personel_1.musteriListesi);

							} else {
								System.out.println(tutiban + " IBAN bilgisine sahip hesap başarıyla silinmiştir");
								Personel_1.musteriListesi.get(tut1).hesaplar.remove(tut2);
							}
						}
						break;

					}
					if (tut2 == -1) {
						System.out.println("Bu IBAN bilgili müşteriye ait bir hesap bulunamadı! ");// eğer girilen ıban
																									// sistemdeki
																									// herhangi bir
																									// ibanla
																									// eşleşmiyorsa
																									// verilen uyarıdır
					}
				}
				if (hesapVarmi == 0) {
					System.out.println("Bu bilgilere ait müşterinin hesabı bulunmamaktadır!");// girilen bilgilere göre
																								// müşterinin hesabı
																								// yoksa verilen
																								// uyarıdır
				}

			}

				break;

			case 5:// bu kısımda para transferleri söz konusudur
				System.out.println("Lütfen MusteriNo bilginizi giriniz!");
				int musteriNoTut = scanner.nextInt();
				int musteriNoTutFlag = -1;
				for (int i = 0; i < Personel_1.musteriListesi.size(); i++) {
					if (Personel_1.musteriListesi.get(i).getMusteriNumarasi() == musteriNoTut) {
						musteriNoTutFlag = i;
					}
				}
				if (musteriNoTutFlag == -1) {
					System.out.println("Kayıtlarda bu MusteriNo bilgisine ait bir müşteri bulunamadı!");
				}

				if (musteriNoTutFlag != -1) {
					Personel_1.musteriListesi.get(musteriNoTutFlag).paraIslemleri(Personel_1.musteriListesi);// gerekli
																												// şartlar
																												// sağlandığında
																												// kesintilerin
																												// yapıldığı
																												// konrol
																												// edilmiştir

				}

				break;
			case 6: {// Bu kısım müşteri nosu girilerek o müşteri nosuna sahip müşterinin kredi
						// kartları ve hesapları listelenir
				System.out.println("Lütfen hesapları görüntülemek istediğiniz müşterinin müşteri numarasını giriniz!");
				int tutMusteriNumarasi = scanner.nextInt();
				int goruntule = -1;
				int krediKartıGoruntule = -1;
				int tutIndexofMusteriNumarasi1 = 0;

				for (int k = 0; k < Personel_1.musteriListesi.size(); k++) {
					if (tutMusteriNumarasi == Personel_1.musteriListesi.get(k).getMusteriNumarasi()) {
						tutIndexofMusteriNumarasi1 = k;
						goruntule = 1;
						krediKartıGoruntule = 1;
					}

				}

				while (goruntule != -1) {
					System.out.println("---------------------Hesaplar---------------------");// grekli bilgiler
																								// listelerden çekilip
																								// yazdırılır
					System.out.println(" ");
					System.out.println("Adı/Soyadı/TC/MusteriNumarasi");
					System.out.println("HesapTuru/HesapBilgisi/Açiliş Tarihi/IBAN");
					System.out.println(" ");
					System.out.println(Personel_1.musteriListesi.get(tutIndexofMusteriNumarasi1).getAd() + "/"
							+ Personel_1.musteriListesi.get(tutIndexofMusteriNumarasi1).getSoyad() + "/"
							+ Personel_1.musteriListesi.get(tutIndexofMusteriNumarasi1).getTcKimlikNo() + "/"
							+ Personel_1.musteriListesi.get(tutIndexofMusteriNumarasi1).getMusteriNumarasi());

					for (int q = 0; q < Personel_1.musteriListesi.get(tutIndexofMusteriNumarasi1).hesaplar
							.size(); q++) {
						System.out.println(" ");

						if (Personel_1.musteriListesi.get(tutIndexofMusteriNumarasi1).hesaplar// grekli bilgiler
																								// listelerden çekilip
																								// yazdırılır
								.get(q).hesapTuru == "vadesiz_hesap") {
							System.out.println((q + 1) + ")"
									+ Personel_1.musteriListesi.get(tutIndexofMusteriNumarasi1).hesaplar
											.get(q).hesapTuru
									+ "/"
									+ Personel_1.musteriListesi.get(tutIndexofMusteriNumarasi1).hesaplar
											.get(q).hesapBilgisi
									+ "/"
									+ Personel_1.musteriListesi.get(tutIndexofMusteriNumarasi1).hesaplar
											.get(q).hesapAcilisTarihi
									+ "/"
									+ Personel_1.musteriListesi.get(tutIndexofMusteriNumarasi1).hesaplar.get(q).iban
									+ "/" + Personel_1.musteriListesi.get(tutIndexofMusteriNumarasi1).hesaplar
											.get(q).toplamBakiye
									+ "TL");

							break;
						}
						if (Personel_1.musteriListesi.get(tutIndexofMusteriNumarasi1).hesaplar// grekli bilgiler
																								// listelerden çekilip
																								// yazdırılır
								.get(q).hesapTuru == "vadeli_hesap") {
							System.out.println((q + 1) + ")"
									+ Personel_1.musteriListesi.get(tutIndexofMusteriNumarasi1).hesaplar
											.get(q).hesapTuru
									+ "/"
									+ Personel_1.musteriListesi.get(tutIndexofMusteriNumarasi1).hesaplar
											.get(q).hesapBilgisi
									+ "/"
									+ Personel_1.musteriListesi.get(tutIndexofMusteriNumarasi1).hesaplar
											.get(q).hesapAcilisTarihi
									+ "/"
									+ Personel_1.musteriListesi.get(tutIndexofMusteriNumarasi1).hesaplar.get(q).iban
									+ "/" + Personel_1.musteriListesi.get(tutIndexofMusteriNumarasi1).hesaplar
											.get(q).toplamBakiye
									+ "TL");

							break;
						}
						if (Personel_1.musteriListesi.get(tutIndexofMusteriNumarasi1).hesaplar// grekli bilgiler
																								// listelerden çekilip
																								// yazdırılır
								.get(q).hesapTuru == "yatirim_hesap") {
							System.out.println((q + 1) + ")"
									+ Personel_1.musteriListesi.get(tutIndexofMusteriNumarasi1).hesaplar
											.get(q).hesapTuru
									+ "/"
									+ Personel_1.musteriListesi.get(tutIndexofMusteriNumarasi1).hesaplar
											.get(q).hesapBilgisi
									+ "/"
									+ Personel_1.musteriListesi.get(tutIndexofMusteriNumarasi1).hesaplar
											.get(q).hesapAcilisTarihi
									+ "/"
									+ Personel_1.musteriListesi.get(tutIndexofMusteriNumarasi1).hesaplar.get(q).iban
									+ "/" + Personel_1.musteriListesi.get(tutIndexofMusteriNumarasi1).hesaplar
											.get(q).toplamBakiye
									+ "TL");

							break;
						}

					}
					break;
				}
				while (krediKartıGoruntule != -1) {// grekli bilgiler listelerden çekilip yazdırılır
					System.out.println(" ");
					System.out.println("---------------------Kredi Kart Bilgileri---------------------");
					System.out.println(" ");

					System.out.println("KartNumarası/Limit/GuncelBorc/KullanılabilirLimit");
					System.out.println(" ");
					for (int t = 0; t < Personel_1.musteriListesi.get(tutIndexofMusteriNumarasi1).krediKartlari// grekli
																												// bilgiler
																												// listelerden
																												// çekilip
																												// yazdırılır
							.size(); t++) {
						System.out.println((t + 1) + ")"
								+ Personel_1.musteriListesi.get(tutIndexofMusteriNumarasi1).krediKartlari.get(t)
										.getKartNumarasi()
								+ "/"
								+ Personel_1.musteriListesi.get(tutIndexofMusteriNumarasi1).krediKartlari.get(t).limit
								+ "/"
								+ Personel_1.musteriListesi.get(tutIndexofMusteriNumarasi1).krediKartlari
										.get(t).guncelBorc
								+ "/" + Personel_1.musteriListesi.get(tutIndexofMusteriNumarasi1).krediKartlari
										.get(t).kullanılabilirLimit);

						if (Personel_1.musteriListesi.get(tutIndexofMusteriNumarasi1).krediKartlari// grekli bilgiler
																									// listelerden
																									// çekilip
																									// yazdırılır
								.get(t).kullanılabilirLimit < 5000.0) {
							System.out.println(Personel_1.musteriListesi.get(tutIndexofMusteriNumarasi1).krediKartlari
									.get(t).guncelBorc + "TL " + "Borcunuz bulunmaktadır. Lütfen ödeme yapınız!");
						}
					}
					break;

				}
				if (goruntule == -1) {
					System.out.println("Bu müşteri numarasına sahip bir müşteri bulunamadı!");
				}
				if (krediKartıGoruntule == -1) {
					System.out.println("Bu müşterinin Kredi Kartı Bulunmamaktadır!");

				}

			}
				break;
			case 7: {// bu kısımda 1 Ocak 2000 den sonra oluşturulan hesapların listelenmesi söz
						// konusudur
				System.out.println("AD/SOYAD/MUSTERİ_NUMARASİ/TCKİMLİK");
				System.out.println("HESAPTURU/HESAPBİLGİSİ/ACILIS_TARIHI/TOPLAM_BAKİYE");
				Boolean hesapVarMi = false;
				Date thresholdDate = new Date(100, 0, 1); // 1 Ocak 2000
				for (int i = 0; i < Personel_1.musteriListesi.size(); i++)

				{

					for (int j = 0; j < Personel_1.musteriListesi.get(i).hesaplar.size(); j++)// grekli bilgiler
																								// listelerden çekilip
																								// yazdırılır
					{
						Date hesapAcilisTarihi = Personel_1.musteriListesi.get(i).hesaplar.get(j).hesapAcilisTarihi;
						if (hesapAcilisTarihi.getYear() >= thresholdDate.getYear()) {
							hesapVarMi = true;
							System.out.println(" ");
							System.out.println(Personel_1.musteriListesi.get(i).getAd() + "/"
									+ Personel_1.musteriListesi.get(i).getSoyad() + "/"
									+ Personel_1.musteriListesi.get(i).getMusteriNumarasi() + "/"
									+ Personel_1.musteriListesi.get(i).getTcKimlikNo());
							System.out.println(Personel_1.musteriListesi.get(i).hesaplar.get(j).hesapTuru + "/"
									+ Personel_1.musteriListesi.get(i).hesaplar.get(j).hesapBilgisi + "/"
									+ hesapAcilisTarihi + "/"
									+ Personel_1.musteriListesi.get(i).hesaplar.get(j).toplamBakiye);
						}

					}
				}
				if (!hesapVarMi) {// sistemde hiç 1 ocak 2000 den sonra açılan hesap yoksa bu uyarı verilir
					System.out.println("1 Ocak 2000'den sonra açılmış bir hesap yoktur!");
				}

				break;

			}
			case 8:// bu kısımda herhangi bir müşteriye müşteri numarası girilerek random limitli
					// bir kredi kartı oluşturulur
				System.out.println(" ");
				System.out.println("Kredi Kartı Oluşturma Menüsüne hoşgeldiniz");
				System.out.println("Lütfen müşteri numaranızı giriniz");
				int kmusterinumtut = scanner.nextInt();
				int flag_kmusterinumtut = -1;
				int indexTut = 0;
				for (int y = 0; y < Personel_1.musteriListesi.size(); y++) {
					if (kmusterinumtut == Personel_1.musteriListesi.get(y).getMusteriNumarasi()) {
						flag_kmusterinumtut = 1;
						indexTut = y;
					}
				}
				while (flag_kmusterinumtut != -1) {
					long min7 = 100000000000L;
					long max7 = 999999999999L;
					long kartNumarasi = min7 + (long) (Math.random() * (max7 - min7));

					boolean kartNumarasiEslesmeVarMi = true;
					while (kartNumarasiEslesmeVarMi) {
						kartNumarasi = min7 + (long) (Math.random() * (max7 - min7));
						kartNumarasiEslesmeVarMi = false;
						for (int x = 0; x < Personel_1.musteriListesi.size(); x++) {
							for (int j = 0; j < Personel_1.musteriListesi.get(x).krediKartlari.size(); j++) {
								if (kartNumarasi == Personel_1.musteriListesi.get(x).krediKartlari.get(j)
										.getKartNumarasi()) {
									kartNumarasiEslesmeVarMi = true;
									break;
								}
							}
							if (kartNumarasiEslesmeVarMi) {
								break;
							}
						}
					}
					Personel_1.musteriListesi.get(indexTut).krediKartiEkle(kartNumarasi);
					System.out.println("Kreti kartınız başarıyla oluşturuldu!!");
					break;
				}
				if (flag_kmusterinumtut == -1) {
					System.out.println("Böyle bir müşteri bulunamadı!");
				}

				break;
			case 9:// Bu kısımda kredi kartı borcunu ödemek isteyen kişiler için açılan bir menüdür
					// gerekli veriler istenir ve metodlara yönlendirilir
			{
				System.out.println(" ");
				System.out.println("Kredi Kartı Borc Ödeme Menüsüne hoşgeldiniz");
				System.out.println("Lütfen müşteri numaranızı giriniz");
				int kmusterinumtut1 = scanner.nextInt();
				int flag_kmusterinumtut1 = -1;
				int indexTut1 = 0;
				for (int y = 0; y < Personel_1.musteriListesi.size(); y++) {
					if (kmusterinumtut1 == Personel_1.musteriListesi.get(y).getMusteriNumarasi()) {
						flag_kmusterinumtut1 = 1;
						indexTut1 = y;
					}
				}
				System.out.println("AD/SOYAD/MUSTERİ_NUMARASİ/TCKİMLİK");
				System.out.println("Kart_Numarası/Guncel_Borc/Limit/Kullanılabilir_Limit");
				System.out.println(" ");
				for (int u = 0; u < Personel_1.musteriListesi.get(indexTut1).krediKartlari.size(); u++)// girdiğiniz
																										// müşteri
																										// nosuna göre
																										// var olan
																										// müşterinin
																										// var olan
																										// kartları ve
																										// bilgileri
																										// listelenir
				{

					System.out.println(Personel_1.musteriListesi.get(indexTut1).getAd() + "/"
							+ Personel_1.musteriListesi.get(indexTut1).getSoyad() + "/"
							+ Personel_1.musteriListesi.get(indexTut1).getMusteriNumarasi() + "/"
							+ Personel_1.musteriListesi.get(indexTut1).getTcKimlikNo());
					System.out.println(Personel_1.musteriListesi.get(indexTut1).krediKartlari.get(u).getKartNumarasi()
							+ "/" + Personel_1.musteriListesi.get(indexTut1).krediKartlari.get(u).guncelBorc + "/"
							+ Personel_1.musteriListesi.get(indexTut1).krediKartlari.get(u).limit + "/"
							+ Personel_1.musteriListesi.get(indexTut1).krediKartlari.get(u).kullanılabilirLimit);
					System.out.println(" ");

				}

				System.out.println("Kart borcunu ödemek istediğiniz kart numarasini giriniz!");
				long tutnumara = scanner.nextLong();
				boolean varMi = false;
				for (int i = 0; i < Personel_1.musteriListesi.size(); i++) {
					for (int j = 0; j < Personel_1.musteriListesi.get(i).krediKartlari.size(); j++)// gerekli kontroller
																									// sağlanır
					{
						if (tutnumara == Personel_1.musteriListesi.get(i).krediKartlari.get(j).getKartNumarasi()) {
							varMi = true;
							System.out.println("Hesap Bulundu! Yönlendiriliyorsunuz...");

							Personel_1.musteriListesi.get(i).KrediKartiBorcuOde(tutnumara);

						}

					}
				}
				if (!varMi) {
					System.out.println("girilen kart numarasına ait hesap bulunamadı!");
				}
				break;
			}
			case 10:// Bu kısımda aklınıza bir şey takıldıysa bizzat bu kodu yazan kişiyle
					// görüşebilirsiniz
				System.out.println("********************************************************************");
				System.out.println(" ");
				System.out.println("Bu kod satırlarında anlamadığınız herhangi bir şey için");
				System.out.println("Müşteri temsilcisi telefon no: +905324363556 /Aleks Dulda ÖğrenciNo:21360859025");
				System.out.println(" ");
				System.out.println("********************************************************************");

				break;
			default:
				System.out.println("Hatali Tuslama Yaptiniz! Lutfen uygun olan bir tuslama yapiniz!");

			}
		}

	}

}
