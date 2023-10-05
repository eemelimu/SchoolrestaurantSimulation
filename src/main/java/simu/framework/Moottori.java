package simu.framework;

import controller.IControllerForM;

public abstract class Moottori extends Thread implements IMoottori {
	
	private double simulointiaika = 0;
	private long viive = 0;
	
	private Kello kello;
	protected IControllerForM controller;
	
	protected Tapahtumalista tapahtumalista;

	public Moottori(IControllerForM controller) {

		this.controller = controller;

		kello = Kello.getInstance(); // Otetaan kello muuttujaan yksinkertaistamaan koodia
		
		tapahtumalista = new Tapahtumalista();
		
		// Palvelupisteet luodaan simu.model-pakkauksessa Moottorin aliluokassa 
		
		
	}

	public void setSimulointiaika(double aika) {
		simulointiaika = aika;
	}

	@Override
	public void setViive(long aika) {
		this.viive = aika;
	}

	@Override
	public long getViive() {
		return this.viive;
	}

	public void viive() {
		try {
			Thread.sleep(this.getViive());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	public void run(){
		alustukset(); // luodaan mm. ensimmäinen tapahtuma
		while (simuloidaan()){

			viive();

			//Trace.out(Trace.Level.INFO, "\nA-vaihe: kello on " + nykyaika());
			kello.setAika(nykyaika());

			//Trace.out(Trace.Level.INFO, "\nB-vaihe:" );
			suoritaBTapahtumat();

			//Trace.out(Trace.Level.INFO, "\nC-vaihe:" );
			yritaCTapahtumat();

		}
		tulokset();
	}
	
	private void suoritaBTapahtumat(){
		while (tapahtumalista.getSeuraavanAika() == kello.getAika()){
			suoritaTapahtuma(tapahtumalista.poista());
		}
	}

	private double nykyaika(){
		return tapahtumalista.getSeuraavanAika();
	}
	
	private boolean simuloidaan(){
		return kello.getAika() < simulointiaika;
	}

	protected abstract void suoritaTapahtuma(Tapahtuma t);  // Määritellään simu.model-pakkauksessa Moottorin aliluokassa
	protected abstract void yritaCTapahtumat();	// Määritellään simu.model-pakkauksessa Moottorin aliluokassa

	protected abstract void alustukset(); // Määritellään simu.model-pakkauksessa Moottorin aliluokassa

	protected abstract void tulokset(); // Määritellään simu.model-pakkauksessa Moottorin aliluokassa
	
}