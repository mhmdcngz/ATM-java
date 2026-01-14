# ATM-java

Bu proje, Java dili kullanılarak geliştirilmiş **konsol tabanlı bir ATM (Automated Teller Machine) simülasyonudur**. Amaç, temel bankacılık işlemlerinin Java ile nasıl modellenebileceğini göstermek ve dosya okuma/yazma, koşullar, döngüler ve metot kullanımını pekiştirmektir.

---

## 🚀 Özellikler

* Kullanıcı şifre doğrulama sistemi
* Bakiye görüntüleme
* Para yatırma
* Para çekme
* Hesaplar arası para transferi
* Dosya üzerinden veri okuma ve yazma
* Menü tabanlı kullanıcı etkileşimi

---

## 🛠 Kullanılan Teknolojiler

* **Java (JDK 8+)**
* Konsol (CLI) tabanlı arayüz
* Dosya işlemleri (`File`, `Scanner`)

---

## 📁 Proje Yapısı

```
ATM-java/
├── src/
│   └── AtmKopya.java
├── atmsifre.txt
├── transferhesap.txt
├── .gitignore
└── README.md
```

* `AtmKopya.java` : Ana uygulama dosyası
* `atmsifre.txt` : Kullanıcı şifre bilgilerinin tutulduğu dosya
* `transferhesap.txt` : Transfer işlemleri için kullanılan hesap verileri

---

## ▶️ Nasıl Çalıştırılır?

### Gereksinimler

* Java Development Kit (JDK 8 veya üzeri)
* Terminal / Komut İstemcisi

### Adımlar

1. Repoyu klonlayın:

```bash
git clone https://github.com/mhmdcngz/ATM-java.git
```

2. Proje klasörüne girin:

```bash
cd ATM-java/src
```

3. Java dosyasını derleyin:

```bash
javac AtmKopya.java
```

4. Programı çalıştırın:

```bash
java AtmKopya
```

---

## 🧑‍💻 Kullanım

* Program çalıştığında kullanıcıdan **şifre girişi** istenir
* Doğru şifre girildiğinde menü açılır
* Menü üzerinden bankacılık işlemleri yapılabilir
* Çıkış seçeneği ile program sonlandırılır

---

## 🤝 Katkıda Bulunma

Katkıda bulunmak istersen:

1. Bu repoyu **fork**la
2. Yeni bir branch oluştur (`feature/yeni-ozellik`)
3. Değişikliklerini commit et
4. Pull Request gönder

---

## 📄 Lisans

Bu proje eğitim amaçlı geliştirilmiştir. İstenirse ileride bir açık kaynak lisansı eklenebilir.

---

## 👤 Geliştirici

* GitHub: [https://github.com/mhmdcngz](https://github.com/mhmdcngz)

---

> Bu proje Java öğrenme sürecinde geliştirilmiş bir ATM simülasyonudur.
