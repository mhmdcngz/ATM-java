import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main extends JFrame {

    private int bakiye = 10000;
    private int girisHakki = 3;

    static final String SIFRE_DOSYASI = "atmsifre.txt";
    static final String TRANSFER_DOSYASI = "transferhesap.txt";

    // Ana panel
    private JPanel anaSayfa;
    private JPanel girisPanel;
    private JPanel menuPanel;

    public Main() {
        setTitle("ATM Sistemi");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        girisEkraniOlustur();

        setVisible(true);
    }

    // GİRİŞ EKRANI
    private void girisEkraniOlustur() {
        girisPanel = new JPanel();
        girisPanel.setLayout(new GridBagLayout());
        girisPanel.setBackground(new Color(240, 240, 240));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel baslik = new JLabel("ATM SİSTEMİNE HOŞGELDİNİZ");
        baslik.setFont(new Font("Arial", Font.BOLD, 20));
        baslik.setForeground(new Color(0, 102, 204));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        girisPanel.add(baslik, gbc);

        JLabel sifreLabel = new JLabel("Şifrenizi Giriniz:");
        sifreLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        girisPanel.add(sifreLabel, gbc);

        JPasswordField sifreField = new JPasswordField(15);
        sifreField.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 1;
        girisPanel.add(sifreField, gbc);

        JLabel hakLabel = new JLabel("Kalan Hak: " + girisHakki);
        hakLabel.setFont(new Font("Arial", Font.BOLD, 12));
        hakLabel.setForeground(Color.RED);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        girisPanel.add(hakLabel, gbc);

        JButton girisButton = new JButton("Giriş Yap");
        girisButton.setFont(new Font("Arial", Font.BOLD, 14));
        girisButton.setBackground(new Color(0, 153, 76));
        girisButton.setForeground(Color.WHITE);
        girisButton.setFocusPainted(false);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        girisPanel.add(girisButton, gbc);

        girisButton.addActionListener(e -> {
            String sifre = new String(sifreField.getPassword());
            try {
                int girilenSifre = Integer.parseInt(sifre);
                if (sifreKontrol(girilenSifre)) {
                    JOptionPane.showMessageDialog(this, "Giriş Başarılı!", "Başarılı", JOptionPane.INFORMATION_MESSAGE);
                    menuEkraniGoster();
                } else {
                    girisHakki--;
                    if (girisHakki > 0) {
                        hakLabel.setText("Kalan Hak: " + girisHakki);
                        JOptionPane.showMessageDialog(this, "Hatalı Şifre!", "Hata", JOptionPane.ERROR_MESSAGE);
                        sifreField.setText("");
                    } else {
                        JOptionPane.showMessageDialog(this, "Kartınız Bloke Edildi!", "BLOKE",
                                JOptionPane.ERROR_MESSAGE);
                        System.exit(0);
                    }
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Lütfen sadece sayı giriniz!", "Hata", JOptionPane.ERROR_MESSAGE);
            }
        });

        add(girisPanel);
    }

    // MENÜ EKRANI
    private void menuEkraniGoster() {
        getContentPane().removeAll();

        menuPanel = new JPanel();
        menuPanel.setLayout(new BorderLayout());
        menuPanel.setBackground(new Color(240, 240, 240));

        JPanel ustPanel = new JPanel();
        ustPanel.setBackground(new Color(0, 102, 204));
        JLabel baslik = new JLabel("ATM MENÜSÜ");
        baslik.setFont(new Font("Arial", Font.BOLD, 24));
        baslik.setForeground(Color.WHITE);
        baslik.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        ustPanel.add(baslik);

        JPanel ortaPanel = new JPanel();
        ortaPanel.setLayout(new GridLayout(5, 1, 10, 10));
        ortaPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
        ortaPanel.setBackground(new Color(240, 240, 240));

        JButton bakiyeBtn = menuButonOlustur("💰 Bakiye Sorgulama");
        JButton yatirBtn = menuButonOlustur("💵 Para Yatırma");
        JButton cekBtn = menuButonOlustur("💸 Para Çekme");
        JButton transferBtn = menuButonOlustur("🔄 Para Transferi");
        JButton cikisBtn = menuButonOlustur("🚪 Çıkış");
        cikisBtn.setBackground(new Color(204, 0, 0));

        bakiyeBtn.addActionListener(e -> bakiyeSorgulama());
        yatirBtn.addActionListener(e -> paraYatirma());
        cekBtn.addActionListener(e -> paraCekme());
        transferBtn.addActionListener(e -> paraTransferi());
        cikisBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Çıkış yapılıyor. İyi günler dileriz!", "Güle Güle",
                    JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        });

        ortaPanel.add(bakiyeBtn);
        ortaPanel.add(yatirBtn);
        ortaPanel.add(cekBtn);
        ortaPanel.add(transferBtn);
        ortaPanel.add(cikisBtn);

        menuPanel.add(ustPanel, BorderLayout.NORTH);
        menuPanel.add(ortaPanel, BorderLayout.CENTER);

        add(menuPanel);
        revalidate();
        repaint();
    }

    private JButton menuButonOlustur(String metin) {
        JButton btn = new JButton(metin);
        btn.setFont(new Font("Arial", Font.BOLD, 16));
        btn.setBackground(new Color(0, 153, 76));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }

    // İŞLEM METODLARı
    private void bakiyeSorgulama() {
        JOptionPane.showMessageDialog(this,
                "Mevcut Bakiyeniz: " + bakiye + " TL",
                "Bakiye Bilgisi",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void paraYatirma() {
        String input = JOptionPane.showInputDialog(this, "Yatırmak istediğiniz tutarı giriniz:");
        if (input != null && !input.isEmpty()) {
            try {
                int tutar = Integer.parseInt(input);
                if (tutar <= 0) {
                    JOptionPane.showMessageDialog(this, "Geçersiz tutar!", "Hata", JOptionPane.ERROR_MESSAGE);
                } else {
                    bakiye += tutar;
                    JOptionPane.showMessageDialog(this,
                            "İşlem Başarılı!\nYeni Bakiye: " + bakiye + " TL",
                            "Başarılı",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Lütfen geçerli bir sayı giriniz!", "Hata",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void paraCekme() {
        String input = JOptionPane.showInputDialog(this, "Çekmek istediğiniz tutarı giriniz:");
        if (input != null && !input.isEmpty()) {
            try {
                int tutar = Integer.parseInt(input);
                if (tutar <= 0) {
                    JOptionPane.showMessageDialog(this, "Geçersiz tutar!", "Hata", JOptionPane.ERROR_MESSAGE);
                } else if (tutar > bakiye) {
                    JOptionPane.showMessageDialog(this,
                            "Yetersiz Bakiye!\nMevcut Bakiye: " + bakiye + " TL",
                            "Hata",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    bakiye -= tutar;
                    JOptionPane.showMessageDialog(this,
                            "Para çekme başarılı!\nKalan Bakiye: " + bakiye + " TL",
                            "Başarılı",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Lütfen geçerli bir sayı giriniz!", "Hata",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void paraTransferi() {
        String hesapInput = JOptionPane.showInputDialog(this, "Transfer edilecek hesap numarasını giriniz:");
        if (hesapInput != null && !hesapInput.isEmpty()) {
            try {
                int hedefHesap = Integer.parseInt(hesapInput);
                if (hesapDogrula(hedefHesap)) {
                    String tutarInput = JOptionPane.showInputDialog(this, "Transfer tutarını giriniz:");
                    if (tutarInput != null && !tutarInput.isEmpty()) {
                        int tutar = Integer.parseInt(tutarInput);
                        if (tutar <= 0) {
                            JOptionPane.showMessageDialog(this, "Geçersiz tutar!", "Hata", JOptionPane.ERROR_MESSAGE);
                        } else if (tutar > bakiye) {
                            JOptionPane.showMessageDialog(this, "Yetersiz Bakiye!", "Hata", JOptionPane.ERROR_MESSAGE);
                        } else {
                            bakiye -= tutar;
                            JOptionPane.showMessageDialog(this,
                                    hedefHesap + " nolu hesaba " + tutar + " TL gönderildi.\nKalan Bakiye: " + bakiye
                                            + " TL",
                                    "Transfer Başarılı",
                                    JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(this,
                            "Girilen hesap numarası sistemde bulunamadı!",
                            "Hata",
                            JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Lütfen geçerli bir sayı giriniz!", "Hata",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // DOSYA KONTROL METODLARı
    private boolean sifreKontrol(int girilenSifre) {
        try (Scanner fileInput = new Scanner(new File(SIFRE_DOSYASI))) {
            while (fileInput.hasNextInt()) {
                if (fileInput.nextInt() == girilenSifre) {
                    return true;
                }
            }
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this,
                    "HATA: Şifre dosyası (" + SIFRE_DOSYASI + ") bulunamadı!",
                    "Dosya Hatası",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return false;
    }

    private boolean hesapDogrula(int hesapNo) {
        try (Scanner fileInput = new Scanner(new File(TRANSFER_DOSYASI))) {
            while (fileInput.hasNextInt()) {
                if (fileInput.nextInt() == hesapNo) {
                    return true;
                }
            }
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this,
                    "HATA: Transfer hesap dosyası (" + TRANSFER_DOSYASI + ") bulunamadı!",
                    "Dosya Hatası",
                    JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main());
    }
}