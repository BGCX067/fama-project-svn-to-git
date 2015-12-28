package fama.obj;

import java.io.Serializable;

import fama.eb.RichiestaAiuto;

public class RichiestaAiutoObj implements Serializable{
	private static final long serialVersionUID = 1L;

	private long id;
	private String utente1;
	private String utente2;
	private boolean accettata;
	private boolean feedbackRicevuto;
	
	private String nomeAbilita;
	private String descrizione;
	private String rispostaAiuto;
	
	public RichiestaAiutoObj(RichiestaAiuto ra) {
		id = ra.getId();
		utente1 = ra.getUtente1();
		utente2 = ra.getUtente2();
		accettata = ra.getAccettata();
		feedbackRicevuto = ra.isFeedbackRicevuto();
		
		nomeAbilita = ra.getNomeAbilita();
		descrizione = ra.getDescrizione();
		rispostaAiuto = ra.getRispostaAiuto();
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
	
	public boolean isFeedbackRicevuto() {
		return feedbackRicevuto;
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
	
	public void setFeedbackRicevuto(boolean feedbackRicevuto) {
		this.feedbackRicevuto = feedbackRicevuto;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	public String getNomeAbilita() {
		return nomeAbilita;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public String getRispostaAiuto() {
		return rispostaAiuto;
	}

	public void setNomeAbilita(String nomeAbilita) {
		this.nomeAbilita = nomeAbilita;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public void setRispostaAiuto(String rispostaAiuto) {
		this.rispostaAiuto = rispostaAiuto;
	}
	
	

}
