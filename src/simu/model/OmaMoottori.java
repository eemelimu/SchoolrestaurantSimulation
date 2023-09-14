package simu.model;

import simu.framework.*;
import eduni.distributions.Negexp;
import eduni.distributions.Normal;

import java.util.ArrayList;
import java.util.Random;

public class OmaMoottori extends Moottori{

	private Saapumisprosessi saapumisprosessi;

	private Palvelupiste[] palvelupisteet;

	//private ArrayList<Double> testiLista = new ArrayList<>(); // testing

	public OmaMoottori(){

		palvelupisteet = new Palvelupiste[5];

		palvelupisteet[0]=new Palvelupiste(new Normal(10,6), tapahtumalista, TapahtumanTyyppi.DEP1, 10, "Tavallinen jono"); //tavallinen
		palvelupisteet[1]=new Palvelupiste(new Normal(10,6), tapahtumalista, TapahtumanTyyppi.DEP2, 6, "Grillijono"); //grilli
		palvelupisteet[2]=new Palvelupiste(new Normal(10,10), tapahtumalista, TapahtumanTyyppi.DEP3, 2, "Maksupääte"); //maksupaate
		palvelupisteet[3]=new Palvelupiste(new Normal(5,3), tapahtumalista, TapahtumanTyyppi.DEP4, 500, "Pöytä"); //poyta
		palvelupisteet[4]=new Palvelupiste(new Normal(5,3), tapahtumalista, TapahtumanTyyppi.DEP5, 6, "Astioidenpalautus"); //astioidenpalautus

		saapumisprosessi = new Saapumisprosessi(new Negexp(15,5), tapahtumalista, TapahtumanTyyppi.ARR1);

	}


	@Override
	protected void alustukset() {
		saapumisprosessi.generoiSeuraava(); // Ensimmäinen saapuminen järjestelmään
	}

	@Override
	protected void suoritaTapahtuma(Tapahtuma t){  // B-vaiheen tapahtumat

		Asiakas a;
		switch ((TapahtumanTyyppi)t.getTyyppi()){
			case ARR1:
				double randomNum = Math.random();
				//testiLista.add(randomNum); // testing
				// jos yli 0.7 niin asiakas menee grilli jonoon, muuten tavalliseen jonoon
				if (randomNum > 0.7) {
					// grilli jonoon
					palvelupisteet[1].lisaaJonoon(new Asiakas());
					System.out.println("Asiakas menee grilli jonoon");
				} else {
					// tavalliseen jonoon
					palvelupisteet[0].lisaaJonoon(new Asiakas());
					System.out.println("Asiakas menee tavalliseen jonoon");
				}
				saapumisprosessi.generoiSeuraava();
				break;

			// Asiakas otetaan pois tavallisesta jonosta ja laitetaan maksupäätteen jonoon
			case DEP1: a = (Asiakas)palvelupisteet[0].otaJonosta();
				palvelupisteet[2].lisaaJonoon(a);
				System.out.println("DEP1");
				break;

			// Asiakas otetaan pois grillin jonosta ja laitetaan maksupäätteen jonoon
			case DEP2: a = (Asiakas)palvelupisteet[1].otaJonosta();
				palvelupisteet[2].lisaaJonoon(a);
				System.out.println("DEP2");
				break;

			// Asiakas otetaan pois maksupäätteen jonosta ja laitetaan pöytä jonoon
			case DEP3: a = (Asiakas)palvelupisteet[2].otaJonosta();
				palvelupisteet[3].lisaaJonoon(a);
				System.out.println("DEP3");
				break;

			// Asiakas otetaan pois pöytä jonosta ja laitetaan astioiden palautus jonoon
			case DEP4: a = (Asiakas)palvelupisteet[3].otaJonosta();
				palvelupisteet[4].lisaaJonoon(a);
				System.out.println("DEP4");
				break;

			// Asiakas otetaan pois astioiden palautus jonosta.
			case DEP5:
				a = (Asiakas)palvelupisteet[4].otaJonosta();
				System.out.println("DEP5");
				a.setPoistumisaika(Kello.getInstance().getAika());
				a.raportti();
		}
	}

	@Override
	protected void yritaCTapahtumat(){
		for (Palvelupiste p: palvelupisteet){
			if (!p.onVarattu() && p.onJonossa()){
				p.aloitaPalvelu();
			}
		}
	}

	@Override
	protected void tulokset() {
		// Tänne kaikki tulokset:
		// Asiakas määrä, suoritustehot jne. Kaikki mitä halutaan tutkia simulaatiossa.
		System.out.println("Simulointi päättyi kello " + Kello.getInstance().getAika());
		System.out.println("Tulokset:");
		for (Palvelupiste p : palvelupisteet) {
			System.out.println(p.getPalvelupisteNimi() + ": " + p.getAsiakasLkm());
		}
		//System.out.println("RandomNum lista: " + testiLista);

	}


}