package fama.test;

import java.util.ArrayList;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fama.obj.UtenteRegistratoObj;

public class RicercaUtentiOspiteTest extends FamaTest {

	@Before
	public void before() throws Exception {
		init();
	}
	
	@Test
	public void testRicercaUtenti() {
		//registra 3 utenti
		rbr.registraUtente("chiaveRicerca", "password1", "nome1", "cognome1", "email1", "tel1");
		rbr.registraUtente("username2", "password2", "chiaveRicerca", "cognome2", "email2", "tel2");
		rbr.registraUtente("username3", "password3", "nome3", "chiaveRicerca", "email3", "tel3");
		UtenteRegistratoObj ut1;
		UtenteRegistratoObj ut2;
		UtenteRegistratoObj ut3;
		try {
			// effettua ricerca per username nome cognome
			ArrayList<UtenteRegistratoObj> arrayUtRegObj = (ArrayList<UtenteRegistratoObj>) aor.ricercaUtenti("chiaveRicerca");

			// ottieni i risultati della ricerca
			ut1 = arrayUtRegObj.get(0);
			ut2 = arrayUtRegObj.get(1);
			ut3 = arrayUtRegObj.get(2);

			// verifica risultati della ricerca
			Assert.assertEquals("chiaveRicerca", ut1.getUsername());
			Assert.assertEquals("chiaveRicerca", ut2.getNome());
			Assert.assertEquals("chiaveRicerca", ut3.getCognome());

		} catch (Exception e) {
			Assert.fail();
		}
	}

	@After
	public void after() throws Exception {
		cleanEntities();
	}
	
}
