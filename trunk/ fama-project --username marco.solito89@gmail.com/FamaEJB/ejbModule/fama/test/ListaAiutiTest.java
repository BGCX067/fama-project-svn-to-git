package fama.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fama.obj.RichiestaAiutoObj;
import fama.sb.FamaBeanEccezione;

public class ListaAiutiTest extends FamaTest{

	@Before
	public void before() throws Exception {
		init();
	}
	
	@Test
	public void testListaAiuti() {
		rbr.registraUtente("tester", "tester", null, null, null, null);
		rbr.registraUtente("cavia", "cavia", null, null, null, null);
		try {
			accessoBeanRemote.login("tester", "tester");
			accessoBeanRemote.richiediAiuto("cavia","aiutoProva");
			accessoBeanRemote.logout();
			accessoBeanRemote.login("cavia", "cavia");
			Assert.assertTrue(accessoBeanRemote.confermaRichiestaAiuto("tester",1,"aiutoProvaDaTester"));
			accessoBeanRemote.logout();
			accessoBeanRemote.login("tester", "tester");
			ArrayList<RichiestaAiutoObj> arrayAiuti = (ArrayList<RichiestaAiutoObj>) accessoBeanRemote.listaAiuti();
			Assert.assertEquals(1, arrayAiuti.get(0).getId());
			Assert.assertEquals("tester", arrayAiuti.get(0).getUtente1());
			Assert.assertEquals("cavia", arrayAiuti.get(0).getUtente2());
			Assert.assertEquals("aiutoProva", arrayAiuti.get(0).getDescrizione());
			Assert.assertEquals("aiutoProvaDaTester", arrayAiuti.get(0).getRispostaAiuto());
		} catch (FamaBeanEccezione e) {
			Assert.fail();
		}
	}

	@After
	public void after() throws Exception {
		cleanEntities();
	}
	
}
