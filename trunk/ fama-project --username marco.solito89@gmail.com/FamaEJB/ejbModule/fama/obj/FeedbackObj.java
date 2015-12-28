package fama.obj;

import java.io.Serializable;

import fama.eb.Feedback;

public class FeedbackObj implements Serializable{
	private static final long serialVersionUID = 1L;

	private long id;
	private String utente1;
	private String utente2;
	
	private long idAiuto;
	private String valutazione;
	private String commento;
	
	public FeedbackObj(Feedback fb) {
		id = fb.getId();
		utente1 = fb.getUtente1();
		utente2 = fb.getUtente2();
		idAiuto = fb.getIdAiuto();
		valutazione = fb.getValutazione();
		commento = fb.getCommento();
	}

	public long getId() {
		return id;
	}

	public String getUtente1() {
		return utente1;
	}

	public String getUtente2() {
		return utente2;
	}

	public long getIdAiuto() {
		return idAiuto;
	}

	public String getValutazione() {
		return valutazione;
	}

	public String getCommento() {
		return commento;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setUtente1(String utente1) {
		this.utente1 = utente1;
	}

	public void setUtente2(String utente2) {
		this.utente2 = utente2;
	}

	public void setIdAiuto(long idAiuto) {
		this.idAiuto = idAiuto;
	}

	public void setValutazione(String valutazione) {
		this.valutazione = valutazione;
	}

	public void setCommento(String commento) {
		this.commento = commento;
	}
	
	
	
}
