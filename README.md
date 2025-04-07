# 💳 BankAppJava

> This is a console-based Java banking simulation project developed for educational purposes by ***Aleks Dulda - 21360859025***.  
> It was created as part of a coursework submission on ***07.05.2023***.

"**BankAppJava**" simulates basic banking operations such as customer management, account transactions, and credit card handling. The app utilizes **object-oriented programming principles**, modular class structures, and interactive menu-driven interfaces.

---

## 🏦 Project Overview

BankAppJava allows users to:

- Create and manage customer accounts.
- Deposit, withdraw, and transfer money between accounts.
- Create and manage credit cards.
- List customer information by ID.
- Apply transaction fees and interest logic.
- Simulate various banking operations through a clean console interface.

---

## 📌 Features

### ✅ Core Functionalities
- 👥 **Customer Management**  
  - Create new customer  
  - List all customers  
  - Delete customer accounts  

- 💸 **Account Operations**  
  - Deposit, withdraw, transfer (with commission based on account type)  
  - List all bank accounts  
  - Filter by date or customer  

- 💳 **Credit Card Features**  
  - Create new credit cards  
  - List credit card information  
  - Pay credit card debt  

- 📈 **Investment & Interest Simulation** *(WIP)*  
  - Varying interest based on account type  
  - Investment account simulation (planned)  

### 🧠 OOP Structure
- Fully object-oriented design:  
  - `Kisi`, `Musteri`, `BankaPersonel`, `BankaHesap`, `KrediKarti`, `Krediler`  
- Usage of:  
  - `ArrayList` for dynamic collections  
  - Random generation for: TCKN, IBAN, card number, etc.  
  - Static class IDs for tracking account/card creation  

---

## 📂 File Structure

```
BankAppJava/
└── src/
    ├── Kisi.java            # Tüm kişi sınıflarının temel sınıfı
    ├── Musteri.java         # Banka müşterisi sınıfı
    ├── BankaPersonel.java   # Banka çalışanları sınıfı
    ├── BankaHesap.java      # Banka hesaplarının yönetildiği sınıf
    ├── KrediKarti.java      # Kredi kartı işlemlerini yöneten sınıf
    ├── Krediler.java        # Kredi hesaplama ve borç işlemleri sınıfı
    └── Main.java            # Uygulamanın başlangıç noktası (menü ve kontrol)
```



## ⚙️ How to Run?

> Make sure you have **Java JDK 11 or later** installed on your system.

---

### 🛠️ Compile the Project

```bash
javac Main.java
```

### ▶️ Run the Program

```bash
java Main
```


## 🧭 Main Menu Options

```
========== BANKA UYGULAMASI ==========
 1 - Müşterileri Listele
 2 - Yeni Müşteri Oluştur
 3 - Yeni Hesap Aç
 4 - Hesapları Listele
 5 - Hesap Sil
 6 - Para Yatır / Çek / Transfer
 7 - Kredi Kartı Oluştur
 8 - Kredi Kartı Borcu Öde
 9 - Tüm Bilgileri Listele (TC ile)
10 - 01.01.2000 sonrası açılan hesapları listele
 0 - Çıkış
```


## 🚧 Planned Improvements

- [ ] `krediKartiSil()` and `krediOdeme()` method implementations  
- [ ] Dynamic exchange rates for investment accounts  
- [ ] Credit scoring and loan approvals  
- [ ] Advanced UI (GUI or Web interface)  

---

## 🙋‍♂️ Developer Info

| Name            | Student No     | Role              |
|-----------------|----------------|-------------------|
| **Aleks Dulda** | 21360859025    | Developer & Author |

---

## 📃 License

This project is developed for educational purposes. No commercial use or redistribution is permitted without explicit permission from the author.

