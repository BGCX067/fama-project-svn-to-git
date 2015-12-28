package fama.test;
import java.io.Serializable;

import javax.ejb.Remote;

import fama.obj.UtenteRegistratoObj;

@Remote
public interface TestBeanRemote {

	public void eliminaUtente(UtenteRegistratoObj nuovoUtente);
	public Serializable esisteUtente(String username);
	public void cleanEntities();
	public Serializable creaAdminProva();
}
