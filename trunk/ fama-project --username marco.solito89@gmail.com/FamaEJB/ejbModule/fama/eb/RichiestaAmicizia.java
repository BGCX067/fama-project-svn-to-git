package fama.eb;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;


@Entity
@Table(name="RichiestaAmicizia")
@IdClass(RichiestaAmiciziaPK.class)
public class RichiestaAmicizia {
	
	@Id private String utente1;
	@Id private String utente2;
	private boolean accettata;
	
	public String getUtente1() {
		return utente1;
	}
	public String getUtente2() {
		return utente2;
	}
	public void setUtente1(String utente1) {
		this.utente1 = utente1;
	}
	public void setUtente2(String utente2) {
		this.utente2 = utente2;
	}
	
	public Boolean getAccettata() {
		return accettata;
	}
	
	public void setAccettata(Boolean accettata) {
		this.accettata = accettata;
	}

}
