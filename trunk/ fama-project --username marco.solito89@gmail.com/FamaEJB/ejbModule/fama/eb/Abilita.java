package fama.eb;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Abilita")
public class Abilita {
	
	@Id
	private String nome;
	
	private String settore;
	private String descrizione;
	private String richiedente;
	private boolean accettata;
	
	public String getNome() {
		return nome;
	}
	public String getSettore() {
		return settore;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setSettore(String settore) {
		this.settore = settore;
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
