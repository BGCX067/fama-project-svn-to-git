package fama.obj;

import java.io.Serializable;

import fama.eb.DichiarazioneAbilita;

public class DichiarazioneAbilitaObj implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String utente;
	private String nomeAbilita;
	
	public DichiarazioneAbilitaObj(DichiarazioneAbilita da) {
		utente = da.getUtente();
		nomeAbilita = da.getNomeAbilita();
	}
	
	public String getUtente() {
		return utente;
	}
	public String getNomeAbilita() {
		return nomeAbilita;
	}
	public void setUtente(String utente) {
		this.utente = utente;
	}
	public void setNomeAbilita(String nomeAbilita) {
		this.nomeAbilita = nomeAbilita;
	}

}
