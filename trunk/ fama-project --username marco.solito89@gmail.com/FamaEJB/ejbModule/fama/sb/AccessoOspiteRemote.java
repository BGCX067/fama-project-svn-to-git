package fama.sb;
import java.io.Serializable;

import javax.ejb.Remote;

@Remote
public interface AccessoOspiteRemote {
	public Serializable ricercaUtenti(String chiaveRicerca);
}
