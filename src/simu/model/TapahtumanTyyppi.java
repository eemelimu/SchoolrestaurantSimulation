package simu.model;

import simu.framework.ITapahtumanTyyppi;

// TODO:
// Tapahtumien tyypit määritellään simulointimallin vaatimusten perusteella
public enum TapahtumanTyyppi implements ITapahtumanTyyppi{
	ARR1, ARR2, DEP1, DEP2, DEP3, DEP4, DEP5;
	// ARR1 = saapuu tavalliseen jonoon ? Eikö asiakas saavu ruokalaan?
	// ARR2 = saapuu grilli jonoon

	// DEP1 = Tavallinen jono
	// DEP2 = Grillijono
	// DEP3 = Maksupääte
	// DEP4 = Pöytä
	// DEP5 = Astioiden palautus
}