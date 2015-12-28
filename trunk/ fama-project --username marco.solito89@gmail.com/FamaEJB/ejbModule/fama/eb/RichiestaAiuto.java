package fama.eb;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="RichiestaAiuto")
//@IdClass(RichiestaAiutoPK.class)
public class RichiestaAiuto {
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO) private long id;
	private String utente1;
	private String utente2;
	private boolean accettata;
	private boolean feedbackRicevuto;
	
	private String nomeAbilita;
	private String descrizione;
	private String rispostaAiuto;
	
	public long getId() {
		return id;
	}
	public String getUtente1() {
		return utente1;
	}
	public String getUtente2() {
		return utente2;
	}
	public String getNomeAbilita() {
		return nomeAbilita;
	}
	public String getDescrizione() {
		return descrizione;
	}
	
	public boolean isFeedbackRicevuto() {
		return feedbackRicevuto;
	}
	
	public void setUtente1(String utente1) {
		this.utente1 = utente1;
	}
	public void setUtente2(String utente2) {
		this.utente2 = utente2;
	}
	public void setNomeAbilita(String nomeAbilita) {
		this.nomeAbilita = nomeAbilita;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public Boolean getAccettata() {
		return accettata;
	}
	public void setAccettata(Boolean accettata) {
		this.accettata = accettata;
	}
	
	public void setFeedbackRicevuto(boolean feedbackRicevuto) {
		this.feedbackRicevuto = feedbackRicevuto;
	}
	
	public String getRispostaAiuto() {
		return rispostaAiuto;
	}
	
	public void setRispostaAiuto(String risposta) {
		this.rispostaAiuto = risposta;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
}
