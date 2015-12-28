package fama.eb;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;


@Entity
@Table(name="DichiarazioneAbilita")
@IdClass(DichiarazioneAbilitaPK.class)
public class DichiarazioneAbilita {

	@Id private String utente;
	@Id private String nomeAbilita;
	
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
