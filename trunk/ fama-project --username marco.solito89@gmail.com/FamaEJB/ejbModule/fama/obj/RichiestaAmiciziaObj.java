package fama.obj;

import java.io.Serializable;

import fama.eb.RichiestaAmicizia;

public class RichiestaAmiciziaObj implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String utente1;
	private String utente2;
	private boolean accettata;
	
	public RichiestaAmiciziaObj(RichiestaAmicizia ra) {
		utente1 = ra.getUtente1();
		utente2 = ra.getUtente2();
		accettata = ra.getAccettata();
	}
	
	public String getUtente1() {
		return utente1;
	}
	public String getUtente2() {
		return utente2;
	}
	public boolean isAccettata() {
		return accettata;
	}
	public void setUtente1(String utente1) {
		this.utente1 = utente1;
	}
	public void setUtente2(String utente2) {
		this.utente2 = utente2;
	}
	public void setAccettata(boolean accettata) {
		this.accettata = accettata;
	}

}
