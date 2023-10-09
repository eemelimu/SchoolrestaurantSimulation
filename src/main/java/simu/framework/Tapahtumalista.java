package simu.framework;

import java.util.PriorityQueue;

public class Tapahtumalista {
	private PriorityQueue<Tapahtuma> lista = new PriorityQueue<Tapahtuma>();
	
	public Tapahtumalista(){
	 
	}
	
	public Tapahtuma poista(){
		System.out.println("Tapahtumalistasta poisto " + lista.peek().getTyyppi() + " " + lista.peek().getAika());
		return lista.remove();
	}
	
	public void lisaa(Tapahtuma t){
		System.out.println("Tapahtumalistaan lisätään uusi " + t.getTyyppi() + " " + t.getAika());
		lista.add(t);
	}
	
	public double getSeuraavanAika(){
		return lista.peek().getAika();
	}
	
}
