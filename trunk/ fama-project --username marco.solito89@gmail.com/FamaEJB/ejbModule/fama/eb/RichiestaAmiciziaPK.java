package fama.eb;

import java.io.Serializable;

public class RichiestaAmiciziaPK implements Serializable{

	private static final long serialVersionUID = 1L;

	private String utente1;
	private String utente2;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((utente1 == null) ? 0 : utente1.hashCode());
		result = prime * result + ((utente2 == null) ? 0 : utente2.hashCode());
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
		RichiestaAmiciziaPK other = (RichiestaAmiciziaPK) obj;
		if (utente1.equals(other.getUtente1()) && utente2.equals(other.getUtente2())) {
			return true;
		}
		return false;
	}

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
	
	
}
