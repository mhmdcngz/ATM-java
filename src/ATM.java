import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;

public class ATM {

    public static final Scanner keyboard = new Scanner(System.in);

    static int bakiye = 10000;

    static final String SIFRE_DOSYASI = "atmsifre.txt";
    static final String TRANSFER_DOSYASI = "transferhesap.txt";

    public static void main(String[] args) {
        if (girisYap()) {
            menuDongusu();
        }
    }

    public static boolean girisYap() {
        int hak = 3;

        while (hak > 0) {
            System.out.print("Şifrenizi Giriniz (Kalan Hak: " + hak + "): ");
            int girilenSifre = guvenliSayiAl();

            if (sifreKontrol(girilenSifre)) {
                System.out.println("Şifre Doğru, Başarılı Giriş.");
                return true;
            } else {
                System.out.println("Hatalı şifre!");
                hak--;
            }
        }

        System.out.println("Hatalı girişler nedeniyle kartınız BLOKE edilmiştir.");
        return false;
    }

    public static boolean sifreKontrol(int girilenSifre) {
        try (Scanner fileInput = new Scanner(new File(SIFRE_DOSYASI))) {
            while (fileInput.hasNextInt()) {
                if (fileInput.nextInt() == girilenSifre) {
                    return true;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("HATA: Şifre dosyası (" + SIFRE_DOSYASI + ") bulunamadı!");
            return false;
        }
        return false;
    }

    public static void menuDongusu() {
        boolean devam = true;
        while (devam) {
            islemSecimMenuGoster();
            int secim = guvenliSayiAl();

            switch (secim) {
                case 1:
                    bakiyeSorgulama();
                    break;
                case 2:
                    paraYatirma();
                    break;
                case 3:
                    paraCekme();
                    break;
                case 4:
                    paraTransferi();
                    break;
                case 5:
                    cikisYap();
                    devam = false;
                    break;
                default:
                    System.out.println("Geçersiz seçim, tekrar deneyiniz.");
            }

            if (devam) {
                devam = baskaIslemSor();
            }
        }
    }

    public static void islemSecimMenuGoster() {
        System.out.println("\n==================================");
        System.out.println("1) Bakiye Sorgulama");
        System.out.println("2) Para Yatırma");
        System.out.println("3) Para Çekme");
        System.out.println("4) Para Transferi");
        System.out.println("5) Çıkış");
        System.out.print("Seçiminiz: ");
    }

    public static boolean baskaIslemSor() {
        System.out.println("\nBaşka İşlem Yapmak İster misiniz? \n1) Evet \n2) Hayır");
        int secim = guvenliSayiAl();
        if (secim == 1)
            return true;
        else {
            cikisYap();
            return false;
        }
    }

    public static void bakiyeSorgulama() {
        System.out.println("Mevcut Bakiyeniz: " + bakiye + " TL");
    }

    public static void paraYatirma() {
        System.out.print("Yatırmak istediğiniz tutarı giriniz: ");
        int tutar = guvenliSayiAl();

        if (tutar <= 0) {
            System.out.println("Geçersiz tutar! Pozitif bir sayı girmelisiniz.");
        } else {
            bakiye += tutar;
            System.out.println("İşlem Başarılı. Yeni Bakiye: " + bakiye);
        }
    }

    public static void paraCekme() {
        System.out.print("Çekmek istediğiniz tutarı giriniz: ");
        int tutar = guvenliSayiAl();

        if (tutar <= 0) {
            System.out.println("Geçersiz tutar!");
        } else if (tutar > bakiye) {
            System.out.println("Yetersiz Bakiye! Mevcut: " + bakiye);
        } else {
            bakiye -= tutar;
            System.out.println("Para çekme başarılı. Kalan Bakiye: " + bakiye);
        }
    }

    public static void paraTransferi() {
        System.out.print("Transfer edilecek hesap numarasını giriniz: ");
        int hedefHesap = guvenliSayiAl();

        if (hesapDogrula(hedefHesap)) {
            System.out.print("Transfer tutarını giriniz: ");
            int tutar = guvenliSayiAl();

            if (tutar <= 0) {
                System.out.println("Geçersiz tutar!");
            } else if (tutar > bakiye) {
                System.out.println("Yetersiz Bakiye!");
            } else {
                bakiye -= tutar;
                System.out.println(hedefHesap + " nolu hesaba " + tutar + " TL gönderildi.");
                System.out.println("Kalan Bakiye: " + bakiye);
            }
        } else {
            System.out.println("Girilen hesap numarası sistemde bulunamadı!");
        }
    }

    public static boolean hesapDogrula(int hesapNo) {
        try (Scanner fileInput = new Scanner(new File(TRANSFER_DOSYASI))) {
            while (fileInput.hasNextInt()) {
                if (fileInput.nextInt() == hesapNo) {
                    return true;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("HATA: Transfer hesap dosyası (" + TRANSFER_DOSYASI + ") bulunamadı!");
        }
        return false;
    }

    public static void cikisYap() {
        System.out.println("======================================");
        System.out.println("Çıkış yapılıyor. İyi günler dileriz.");
        System.out.println("======================================");
    }

    public static int guvenliSayiAl() {
        int deger = -1;
        try {
            deger = keyboard.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Hata: Lütfen sadece sayı giriniz!");
            keyboard.nextLine();
        }
        return deger;
    }
}