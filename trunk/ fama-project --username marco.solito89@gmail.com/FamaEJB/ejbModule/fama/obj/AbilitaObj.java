package fama.obj;

import java.io.Serializable;

import fama.eb.Abilita;

public class AbilitaObj implements Serializable {

	private static final long serialVersionUID = 1L;
	
    private String nome;
	private String settore;
	private String descrizione;
	private String richiedente;
	private boolean accettata;
	
	
	public AbilitaObj(Abilita abilita) {
		nome = abilita.getNome();
		settore = abilita.getSettore();
		descrizione = abilita.getDescrizione();
		richiedente = abilita.getRichiedente();
		accettata = abilita.isAccettata();
	}
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSettore() {
		return settore;
	}
	public void setSettore(String settore) {
		this.settore = settore;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public String getRichiedente() {
		return richiedente;
	}
	public void setRichiedente(String richiedente) {
		this.richiedente = richiedente;
	}
	public boolean isAccettata() {
		return accettata;
	}
	public void setAccettata(boolean accettata) {
		this.accettata = accettata;
	}
}
