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
│   └── ATM.java
├── gui/
│   ├── src/
│   │   └── Main.java
│   ├── atmsifre.txt
│   └── transferhesap.txt
├── atmsifre.txt
├── transferhesap.txt
├── .gitignore
└── README.md
```

* `src/ATM.java` : Konsol sürümünün ana dosyası
* `gui/` : Aynı uygulamanın Swing arayüzlü sürümü (aşağıya bakın)
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

2. Proje klasörüne girin (txt dosyaları burada arandığı için programı bu klasörden çalıştırın):

```bash
cd ATM-java
```

3. Java dosyasını derleyin:

```bash
javac -encoding UTF-8 -d out src/ATM.java
```

4. Programı çalıştırın:

```bash
java -cp out ATM
```

---

## 🖥 Arayüzlü Sürüm (Swing)

`gui/` klasöründe aynı uygulamanın Java Swing ile yazılmış pencereli sürümü bulunur. Şifre girişi, bakiye sorgulama, para yatırma/çekme ve transfer işlemleri butonlarla yapılır. 3 hatalı şifre denemesinde kart bloke edilir.

```bash
cd ATM-java/gui
javac -encoding UTF-8 -d out src/Main.java
java -cp out Main
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
