package fama.eb;

import java.io.Serializable;

import javax.persistence.Id;

public class DichiarazioneAbilitaPK implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id private String utente;
	@Id private String nomeAbilita;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((utente == null) ? 0 : utente.hashCode());
		result = prime * result + ((nomeAbilita == null) ? 0 : nomeAbilita.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DichiarazioneAbilitaPK other = (DichiarazioneAbilitaPK) obj;
		if (utente.equals(other.getUtente()) && nomeAbilita.equals(other.getNomeAbilita())) {
			return true;
		}
		return false;
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
