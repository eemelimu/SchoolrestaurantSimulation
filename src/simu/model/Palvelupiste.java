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
	
	//JonoStartegia strategia; //optio: asiakkaiden järjestys
	
	private boolean varattu = false;


	public Palvelupiste(ContinuousGenerator generator, Tapahtumalista tapahtumalista, TapahtumanTyyppi tyyppi, int maksimiAsiakasKapasiteetti, String palvelupisteNimi){
		this.tapahtumalista = tapahtumalista;
		this.generator = generator;
		this.skeduloitavanTapahtumanTyyppi = tyyppi;
		this.maksimiAsiakasKapasiteetti = maksimiAsiakasKapasiteetti;
		this.palvelupisteNimi = palvelupisteNimi;
	}


	public void lisaaJonoon(Asiakas a){   // Jonon 1. asiakas aina palvelussa
		jono.add(a);
		
	}


	public Asiakas otaJonosta(){  // Poistetaan palvelussa ollut
		varattu = false;
		return jono.poll();
	}

	public String getPalvelupisteNimi() {
		return palvelupisteNimi;
	}

	public int getAsiakasLkm() {
		return this.asiakasLkm;
	}

	public void aloitaPalvelu(){  //Aloitetaan uusi palvelu, asiakas on jonossa palvelun aikana

		try {
			Trace.out(Trace.Level.INFO, "Aloitetaan uusi palvelu asiakkaalle " + jono.peek().getId());
			varattu = true;
			this.asiakasLkm++;
			double palveluaika = generator.sample();
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
