package simu.model;

import controller.Controller;
import controller.IControllerForM;
import dao.PalvelupisteDataDao;
import dao.SimulointiDao;
import entity.PalvelupisteData;
import entity.Simulointi;
import simu.framework.*;
import eduni.distributions.*;
import view.SimulaattorinGUI;

public class OmaMoottori extends Moottori{

	private Saapumisprosessi saapumisprosessi;
	private PalvelupisteDataDao palvelupisteDataDao;

	private Palvelupiste[] palvelupisteet;
	private PalvelupisteData[] palvelupisteDatat;
	private SimulointiDao simulointiDao;
	private long viive;
	private SimulaattorinGUI ui;
	private Controller ctrl;

	public OmaMoottori(IControllerForM controller, SimulaattorinGUI ui, Controller ctrl) {
		super(controller);
		this.ui = ui;
		this.ctrl = ctrl;

		palvelupisteDataDao = new PalvelupisteDataDao();
		simulointiDao = new SimulointiDao();

		palvelupisteet = new Palvelupiste[5];

		// Tavallinen jono
		palvelupisteet[0]=new Palvelupiste(new Normal(ctrl.getTavallinenJonoKeskiarvo(), ctrl.getTavallinenJonoMuutos()), tapahtumalista, TapahtumanTyyppi.DEP1, ctrl.tavallinenKapasiteetti(), "Tavallinen jono");
		palvelupisteet[0].setEka_parametri(ctrl.getTavallinenJonoKeskiarvo());
		palvelupisteet[0].setToka_parametri(ctrl.getTavallinenJonoMuutos());
		// Grilli jono
		palvelupisteet[1]=new Palvelupiste(new Normal(ctrl.getGrillijonoKeskiarvo(),ctrl.getGrillijonoMuutos()), tapahtumalista, TapahtumanTyyppi.DEP2, ctrl.grilliKapasiteetti(), "Grillijono");
		palvelupisteet[1].setEka_parametri(ctrl.getTavallinenJonoKeskiarvo());
		palvelupisteet[1].setToka_parametri(ctrl.getTavallinenJonoMuutos());
		// Maksupääte
		palvelupisteet[2]=new Palvelupiste(new Normal(ctrl.getMaksupaateKeskiarvo(), ctrl.getGrillijonoMuutos()), tapahtumalista, TapahtumanTyyppi.DEP3, ctrl.maksupaateKapasiteetti(), "Maksupääte");
		palvelupisteet[2].setEka_parametri(ctrl.getTavallinenJonoKeskiarvo());
		palvelupisteet[2].setToka_parametri(ctrl.getTavallinenJonoMuutos());
		// Pöytä
		palvelupisteet[3]=new Palvelupiste(new Normal(ctrl.getPoytaKeskiarvo(), ctrl.getPoytaMuutos()), tapahtumalista, TapahtumanTyyppi.DEP4, ctrl.poytaKapasiteetti(), "Pöytä");
		palvelupisteet[3].setEka_parametri(ctrl.getTavallinenJonoKeskiarvo());
		palvelupisteet[3].setToka_parametri(ctrl.getTavallinenJonoMuutos());
		// Astioiden palautus
		palvelupisteet[4]=new Palvelupiste(new Normal(ctrl.getAstioidenpalautusKeskiarvo(), ctrl.getAstioidenpalautusMuutos()), tapahtumalista, TapahtumanTyyppi.DEP5, ctrl.astioidenpalautusKapasiteetti(), "Astioidenpalautus");
		palvelupisteet[4].setEka_parametri(ctrl.getTavallinenJonoKeskiarvo());
		palvelupisteet[4].setToka_parametri(ctrl.getTavallinenJonoMuutos());

		saapumisprosessi = new Saapumisprosessi(ctrl.getJakauma(), tapahtumalista, TapahtumanTyyppi.ARR1);
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

					// Jos arvottu luku on yli 0.9 niin asiakas menee grilli jonoon, muuten tavalliseen jonoon
					// Eli noin 10% asiakkaista simulaation aikana pitäisi mennä grilli jonoon
					// Loppujen 90% pitäisi mennä tavalliseen.
					if (randomNum >= 0.93) {
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
				case DEP1:
					a = (Asiakas) palvelupisteet[0].otaPalveltavienJonosta();
					System.out.println("Asiakas poistuu tavallisesta jonosta.");
					palvelupisteet[2].lisaaJonoon(a);
					break;

				// Asiakas otetaan pois grillin jonosta ja laitetaan maksupäätteen jonoon
				case DEP2:
					a = (Asiakas) palvelupisteet[1].otaPalveltavienJonosta();
					System.out.println("Asiakas poistuu grillijonosta.");
					palvelupisteet[2].lisaaJonoon(a);
					break;

				// Asiakas otetaan pois maksupäätteen jonosta ja laitetaan pöytä jonoon
				case DEP3:
					a = (Asiakas) palvelupisteet[2].otaPalveltavienJonosta();
					palvelupisteet[3].lisaaJonoon(a);
					break;

				// Asiakas otetaan pois pöytä jonosta ja laitetaan astioiden palautus jonoon
				case DEP4:
					a = (Asiakas) palvelupisteet[3].otaPalveltavienJonosta();
					palvelupisteet[4].lisaaJonoon(a);
					break;

				// Asiakas otetaan pois astioiden palautus jonosta.
				case DEP5:
					a = (Asiakas) palvelupisteet[4].otaPalveltavienJonosta();
					a.setPoistumisaika(Kello.getInstance().getAika());
					a.raportti();
			}
		} catch (Exception e) {
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

	@Override
	protected void tulokset() {
		// Tänne kaikki tulokset:
		// Asiakas määrä, suoritustehot jne. Kaikki mitä halutaan tutkia simulaatiossa.
		System.out.println("\nSimulointi päättyi kello " + Kello.getInstance().getAika());
		System.out.println("\nTulokset:");
		int totalAsiakkaat = 0;

		Simulointi simulointi = new Simulointi(ctrl.getAika(), getViive());
		simulointiDao.persist(simulointi);

		for (Palvelupiste p : palvelupisteet) {
			System.out.println(p.getPalvelupisteNimi() + ":");
			ctrl.setTulostukset("                        " + p.getPalvelupisteNimi() + "\n");

			totalAsiakkaat += p.getAsiakasLkm();

			// Palveluaika
			System.out.println("Palveluaika: " + p.getPalvelupisteenKokonaisAika());
			ctrl.setTulostukset("Palveluaika: " + String.valueOf(p.getPalvelupisteenKokonaisAika()));

			// Palvelupisteen asiakas lukumäärä: C(palveltujen asiakkaiden määrä)
			System.out.println("AsiakasLkm: " + p.getAsiakasLkm());
			ctrl.setTulostukset("Asiakas lukumäärä: " + String.valueOf(p.getAsiakasLkm()));

			// Palvelupisteen käyttöaste: U=B(palveltujen asiakkaiden määrä)/T(simuloinnin kokoamisaika)
			System.out.format("Käyttöaste: %.1f", (p.getAsiakasLkm()/Kello.getInstance().getAika()*100));
			System.out.println("%");
			ctrl.setTulostukset(String.valueOf("Käyttöaste: " + p.getAsiakasLkm()/Kello.getInstance().getAika()));

			// Palvelupisteen suoritusteho: X=C(palveltujen asiakkaiden määrä)/T(simuloinnin kokoamisaika)
			System.out.format("Suoritusteho: %.5f\n", (p.getAsiakasLkm()/Kello.getInstance().getAika()));

			// Palvelupisteen asiakkaiden määrä joita ei ole palveltu
			System.out.println("Ei palvellut asiakkaat: " + p.getJonoSize());
			ctrl.setTulostukset("Ei palvellut asiakkaat: " + String.valueOf(p.getJonoSize()));

			// Palvelupisteen suurin jono
			System.out.println("Suurin jono simun aikana: " + p.getSuurinJono());
			ctrl.setTulostukset(String.valueOf("Suurin jono simun aikana: " + p.getSuurinJono()) + "\n------------------------------------------------");

			PalvelupisteData palvelupisteData = new PalvelupisteData(simulointi.getId(), p.getPalvelupisteNimi(), p.getPalvelupisteenKokonaisAika(), p.getAsiakasLkm(), p.getAsiakasLkm()/Kello.getInstance().getAika()*100, p.getJonoSize(), p.getSuurinJono(), p.getMaksimiAsiakasKapasiteetti(), p.getEka_parametri(), p.getToka_parametri());
			palvelupisteDataDao.persist(palvelupisteData);
		}
		// Lähetä simulaation tulokset tietokantaan
		// Lähetä Simulointi


		// Total asiakkaat
		ctrl.setTulostukset("Asiakkaiden kokonaismäärä: " + String.valueOf(totalAsiakkaat) + "\n");
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