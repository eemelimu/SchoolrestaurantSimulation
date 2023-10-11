package entity;

import jakarta.persistence.*;

@Entity
@Table(name="palvelupiste")
public class PalvelupisteData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="name")
    private String nimi;

    @Column(name="simulointiId")
    private int simulointiId;

    @Column(name="palveluaika")
    private double palveluaika;

    @Column(name="asiakaslkm")
    private int asiakaslkm;

    @Column(name="kayttoaste")
    private double kayttoaste;

    @Column(name="ei_palvelleet_asiakkaat")
    private int ei_palvelleet_asiakkaat;

    @Column(name="maksimi_jono")
    private int maksimi_jono;

    @Column(name="asiakas_kapasiteetti")
    private int asiakas_kapasiteetti;

    @Column(name="eka_parametri")
    private int eka_parametri;

    @Column(name="toka_parametri")
    private int toka_parametri;

    public PalvelupisteData (int simulointiId, String nimi, double palveluaika, int asiakaslkm, double kayttoaste, int ei_palvelleet_asiakkaat, int maksimi_jono, int asiakas_kapasiteetti, int eka_parametri, int toka_parametri) {
        this.simulointiId = simulointiId;
        this.nimi = nimi;
        this.palveluaika = palveluaika;
        this.asiakaslkm = asiakaslkm;
        this.kayttoaste = kayttoaste;
        this.ei_palvelleet_asiakkaat = ei_palvelleet_asiakkaat;
        this.maksimi_jono = maksimi_jono;
        this.asiakas_kapasiteetti = asiakas_kapasiteetti;
        this.eka_parametri = eka_parametri;
        this.toka_parametri = toka_parametri;
    }

    public String toString() {
        return "Nimi: " + this.nimi + "\nPalveluaika: " + this.palveluaika + "\nAsiakaslkm: " + this.asiakaslkm + "\nKäyttöaste: " + this.kayttoaste + "\nEi palvelleet asiakkaat: " + this.ei_palvelleet_asiakkaat + "\nMaksimi jono: " + this.maksimi_jono + "\nAsiakas kapasiteetti: " + this.asiakas_kapasiteetti + "\nEka parametri: " + this.eka_parametri + "\nToka parametri: " + this.toka_parametri + "\n";
    }

    public PalvelupisteData() {}
}
