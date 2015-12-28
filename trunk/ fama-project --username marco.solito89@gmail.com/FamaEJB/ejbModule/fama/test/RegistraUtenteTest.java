package fama.test;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fama.obj.UtenteRegistratoObj;

public class RegistraUtenteTest extends FamaTest {
	
	private UtenteRegistratoObj nuovoUtente;
	
	@Before
	public void before() throws Exception {
		init();
	}
	
	private void verificaUtente() {
		nuovoUtente = (UtenteRegistratoObj) tbr.esisteUtente("usernameProva");
		Assert.assertNotNull(nuovoUtente);
	}
	
	@Test
	public void testRegistraUtente() {
		rbr.registraUtente("usernameProva", "passwordProva", null, null, null, null);
		verificaUtente();
	}
	
	@After
	public void after() throws Exception{
		tbr.eliminaUtente(nuovoUtente);
		cleanEntities();
	}

}
