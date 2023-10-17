package simu.model;

import simu.framework.ITapahtumanTyyppi;

//* TapahtumanTyyppi luokka, joka sisältää tapahtumien tyypit
public enum TapahtumanTyyppi implements ITapahtumanTyyppi{
	ARR1, DEP1, DEP2, DEP3, DEP4, DEP5;
	// ARR1 = Asiakas saapuu ruokalaan, sitten arvotaan meneekö grilli jonoon vai tavalliseen jonoon

	// DEP1 = Tavallinen jono
	// DEP2 = Grillijono
	// DEP3 = Maksupääte
	// DEP4 = Pöytä
	// DEP5 = Astioiden palautus
}