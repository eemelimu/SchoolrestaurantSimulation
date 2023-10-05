package simu.model;

import controller.Controller;
import controller.IControllerForM;
import simu.framework.*;
import eduni.distributions.Negexp;
import eduni.distributions.Normal;
import view.ISimulaattoriUI;
import view.SimulaattorinGUI;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class OmaMoottori extends Moottori{

	private Saapumisprosessi saapumisprosessi;

	private Palvelupiste[] palvelupisteet;
	private long viive;
	private SimulaattorinGUI ui;
	private Controller ctrl;

	//private ArrayList<Palvelupiste> testiLista = new ArrayList<>(); // testing

	public OmaMoottori(IControllerForM controller, SimulaattorinGUI ui, Controller ctrl) {
		super(controller);
		this.ui = ui;
		this.ctrl = ctrl;

		palvelupisteet = new Palvelupiste[5];

		// tavallinen jono
		palvelupisteet[0]=new Palvelupiste(new Normal(2,1), tapahtumalista, TapahtumanTyyppi.DEP1, this.ui.getTavallinenAsiakasKapasiteetti(), "Tavallinen jono");
		// grilli jono
		palvelupisteet[1]=new Palvelupiste(new Normal(8,5), tapahtumalista, TapahtumanTyyppi.DEP2, this.ui.getGrilliAsiakasKapasiteetti(), "Grillijono");
		// maksupääte
		palvelupisteet[2]=new Palvelupiste(new Normal(1,1), tapahtumalista, TapahtumanTyyppi.DEP3, this.ui.getMaksupaateAsiakasKapasiteetti(), "Maksupääte");
		// pöytä
		palvelupisteet[3]=new Palvelupiste(new Normal(15,5), tapahtumalista, TapahtumanTyyppi.DEP4, this.ui.getPoytaAsiakasKapasiteetti(), "Pöytä");
		// astioiden palautus
		palvelupisteet[4]=new Palvelupiste(new Normal(2,1), tapahtumalista, TapahtumanTyyppi.DEP5, this.ui.getAstioidenpalautusKapasiteetti(), "Astioidenpalautus");

		saapumisprosessi = new Saapumisprosessi(new Normal(2,1), tapahtumalista, TapahtumanTyyppi.ARR1);

		/*testiLista.add(palvelupisteet[0]);
		testiLista.add(palvelupisteet[1]);
		testiLista.add(palvelupisteet[2]);
		testiLista.add(palvelupisteet[3]);
		testiLista.add(palvelupisteet[4]);*/
	}

	@Override
	protected void alustukset() {
		saapumisprosessi.generoiSeuraava(); // Ensimmäinen saapuminen järjestelmään
	}

	@Override
	protected void suoritaTapahtuma(Tapahtuma t){  // B-vaiheen tapahtumat

		try {

			Asiakas a;

			switch ((TapahtumanTyyppi) t.getTyyppi()) {
				// Asiakas saapuu ruokalaan
				case ARR1:
					controller.getVisualisointi().uusiAsiakas(palvelupisteet);
					double randomNum = Math.random();
					System.out.println("Asiakas saapui ruokalaan.");
					//testiLista.add(randomNum); // testing
					// jos yli 0.7 niin asiakas menee grilli jonoon, muuten tavalliseen jonoon

					if (randomNum >= 0.7) {    // grilli jonoon
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
				case DEP1:
					a = (Asiakas) palvelupisteet[0].otaJonosta();
					palvelupisteet[0].lisaaPalveltavienJonoon(a); // lisätään asiakas palveltavien jonoon (tällöin asiakas on palveltavana)
					System.out.println("Asiakas poistuu tavallisesta jonosta.");
					palvelupisteet[2].lisaaJonoon(a);
					System.out.println("DEP1");
					break;

				// Asiakas otetaan pois grillin jonosta ja laitetaan maksupäätteen jonoon
				case DEP2:
					a = (Asiakas) palvelupisteet[1].otaJonosta();
					palvelupisteet[1].lisaaPalveltavienJonoon(a);
					System.out.println("Asiakas poistuu grillijonosta.");
					palvelupisteet[2].lisaaJonoon(a);
					System.out.println("DEP2");
					break;

				// Asiakas otetaan pois maksupäätteen jonosta ja laitetaan pöytä jonoon
				case DEP3:
					a = (Asiakas) palvelupisteet[2].otaJonosta();
					palvelupisteet[2].lisaaPalveltavienJonoon(a);
					palvelupisteet[3].lisaaJonoon(a);
					System.out.println("DEP3");
					break;

				// Asiakas otetaan pois pöytä jonosta ja laitetaan astioiden palautus jonoon
				case DEP4:
					a = (Asiakas) palvelupisteet[3].otaJonosta();
					palvelupisteet[3].lisaaPalveltavienJonoon(a);
					palvelupisteet[4].lisaaJonoon(a);
					System.out.println("DEP4");
					break;

				// Asiakas otetaan pois astioiden palautus jonosta.
				case DEP5:
					a = (Asiakas) palvelupisteet[4].otaJonosta();
					System.out.println("DEP5");
					a.setPoistumisaika(Kello.getInstance().getAika());
					a.raportti();
			}
		} catch (Exception e) {
			//e.printStackTrace();
			System.out.println("Exception e suoritaTapahtuma");
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

	public int getNykyisetAsiakkaat(int i) {
		return palvelupisteet[i].getNykyisetAsiakkaat();
	}

	public Palvelupiste[] getPalvelupisteet(){
		return palvelupisteet;
	}

	@Override
	protected void tulokset() {
		// Tänne kaikki tulokset:
		// Asiakas määrä, suoritustehot jne. Kaikki mitä halutaan tutkia simulaatiossa.
		System.out.println("\nSimulointi päättyi kello " + Kello.getInstance().getAika());
		System.out.println("\nTulokset:");
		for (Palvelupiste p : palvelupisteet) {
			System.out.println(p.getPalvelupisteNimi() + ":");
			ctrl.setTulostukset(p.getPalvelupisteNimi() + ":");

			// Palveluaika
			System.out.println("Palveluaika: " + p.getPalvelupisteenKokonaisAika());
			ctrl.setTulostukset("Palveluaika: " + String.valueOf(p.getPalvelupisteenKokonaisAika()));

			// Palvelupisteen asiakas lukumäärä: C(palveltujen asiakkaiden määrä)
			System.out.println("AsiakasLkm: " + p.getAsiakasLkm());
			ctrl.setTulostukset("Asiakas lukumäärä: " + String.valueOf(p.getAsiakasLkm()));

			// Palvelupisteen käyttöaste: U=B(palveltujen asiakkaiden määrä)/T(simuloinnin kokoamisaika)
			System.out.format("Käyttöaste: %.1f", (p.getPalvelupisteenKokonaisAika()/Kello.getInstance().getAika()*100));
			System.out.println("%");
			ctrl.setTulostukset(String.valueOf("Käyttöaste: " + p.getPalvelupisteenKokonaisAika()/Kello.getInstance().getAika()));

			// Palvelupisteen suoritusteho: X=C(palveltujen asiakkaiden määrä)/T(simuloinnin kokoamisaika)
			System.out.format("Suoritusteho: %.5f\n", (p.getAsiakasLkm()/Kello.getInstance().getAika()));

			// Palvelupisteen asiakkaiden määrä joita ei ole palveltu
			System.out.println("Ei palvellut asiakkaat: " + p.getJonoSize());
			ctrl.setTulostukset("Ei palvellut asiakkaat: " + String.valueOf(p.getJonoSize()));

			// Palvelupisteen suurin jono
			System.out.println("Suurin jono simun aikana: " + p.getSuurinJono());
			ctrl.setTulostukset(String.valueOf("Suurin jono simun aikana: " + p.getSuurinJono()) + "\n");

			// Nykyiset asiakkaat
			//System.out.println("Nykyiset asiakkaat: " + p.getNykyisetAsiakkaat());

			// Seuraava tähän
			// Newline
			System.out.println("\n");
		}
		//System.out.println("RandomNum lista: " + testiLista);

	}


	// UUDET
	@Override
	public void setViive(long aika) {
		this.viive = aika;
	}

	@Override
	public long getViive() {
		return this.viive;
	}
}