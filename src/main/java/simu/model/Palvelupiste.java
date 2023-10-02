package simu.model;

import simu.framework.*;
import java.util.LinkedList;
import eduni.distributions.ContinuousGenerator;

// TODO:
// Palvelupistekohtaiset toiminnallisuudet, laskutoimitukset (+ tarvittavat muuttujat) ja raportointi koodattava
public class Palvelupiste {

	private final LinkedList<Asiakas> jono = new LinkedList<>(); // Tietorakennetoteutus
	private final ContinuousGenerator generator;
	private final Tapahtumalista tapahtumalista;
	private final TapahtumanTyyppi skeduloitavanTapahtumanTyyppi;
	private final String palvelupisteNimi;
	private final int maksimiAsiakasKapasiteetti; // kuinka monta asiakasta voi olla palvelupisteellä samaan aikaan
	private int asiakasLkm; // kuinka monta asiakasta on käynyt palvelupisteellä
	private int nykyisetAsiakkaat; // kuinka monta asiakasta on tällä hetkellä palvelupisteellä
	private double palvelupisteenKokonaisAika; // kuinka kauan palvelupiste on ollut käytössä

	//JonoStartegia strategia; //optio: asiakkaiden järjestys

	private boolean varattu = false;
	private int suurinJono = 0;

	public Palvelupiste(ContinuousGenerator generator, Tapahtumalista tapahtumalista, TapahtumanTyyppi tyyppi, int maksimiAsiakasKapasiteetti, String palvelupisteNimi){
		this.tapahtumalista = tapahtumalista;
		this.generator = generator;
		this.skeduloitavanTapahtumanTyyppi = tyyppi;
		this.maksimiAsiakasKapasiteetti = maksimiAsiakasKapasiteetti;
		this.palvelupisteNimi = palvelupisteNimi;
	}

	public void lisaaJonoon(Asiakas a){   // Jonon 1. asiakas aina palvelussa
		jono.add(a);
		checkSuurinJono();
	}

	public void checkSuurinJono() {
		if (jono.size() > this.suurinJono) {
			this.suurinJono = jono.size();
		}
	}

	public int getSuurinJono() {
		return this.suurinJono;
	}

	public Asiakas otaJonosta(){  // Poistetaan palvelussa ollut
		if (this.nykyisetAsiakkaat >= this.maksimiAsiakasKapasiteetti) {
			System.out.println("Palvelupiste " + this.palvelupisteNimi + " on täynnä.");
			return null;
		}
		this.nykyisetAsiakkaat++;
		varattu = false;
		return jono.poll();
	}

	public String getPalvelupisteNimi() {
		return palvelupisteNimi;
	}

	public double getPalvelupisteenKokonaisAika() {
		return palvelupisteenKokonaisAika;
	}

	public int getAsiakasLkm() {
		return this.asiakasLkm;
	}

	public int getJonoSize() {
		return this.jono.size();
	}

	public void aloitaPalvelu(){  // Aloitetaan uusi palvelu, asiakas on jonossa palvelun aikana

		try  {
			//Trace.out(Trace.Level.INFO, "Aloitetaan uusi palvelu asiakkaalle " + jono.peek().getId());
            assert jono.peek() != null;
			System.out.println("Aloitetaan uusi palvelu asiakkaalle " + jono.peek().getId());
			varattu = true;
			this.asiakasLkm++;
			this.nykyisetAsiakkaat--;
			double palveluaika = generator.sample();
			this.palvelupisteenKokonaisAika += palveluaika;
			tapahtumalista.lisaa(new Tapahtuma(skeduloitavanTapahtumanTyyppi,Kello.getInstance().getAika()+palveluaika));
		} catch (NullPointerException e) {
			//e.printStackTrace();
			System.out.println("Jono tyhjä.");
		}
	}

	public boolean onVarattu(){
		return varattu;
	}

	public boolean onJonossa(){
		return jono.size() != 0;
	}

}