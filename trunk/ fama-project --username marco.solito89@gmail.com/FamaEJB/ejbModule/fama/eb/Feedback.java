package fama.eb;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="Feedback")
public class Feedback {

	@Id @GeneratedValue(strategy=GenerationType.AUTO) private long id;
	private String utente1;
	private String utente2;
	
	private long idAiuto;
	private String valutazione;
	private String commento;
	
	public long getId() {
		return id;
	}
	public String getUtente1() {
		return utente1;
	}
	public String getUtente2() {
		return utente2;
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
	public void setValutazione(String valutazione) {
		this.valutazione = valutazione;
	}
	public void setCommento(String commento) {
		this.commento = commento;
	}
	
	public long getIdAiuto() {
		return idAiuto;
	}
	
	public void setIdAiuto(long idAiuto) {
		this.idAiuto = idAiuto;
	}
	
}
