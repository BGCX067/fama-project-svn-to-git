package fama.test;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fama.sb.FamaBeanEccezione;

public class RifiutaRichiestaAmiciziaTest extends FamaTest {

	@Before
	public void before() throws Exception {
		init();
	}
	
	@Test
	public void testRifiutaRichiestaAmicizia() {
		rbr.registraUtente("tester", "tester", null, null, null, null);
		rbr.registraUtente("cavia", "cavia", null, null, null, null);
		try {
			accessoBeanRemote.login("tester", "tester");
			accessoBeanRemote.richiediAmicizia("cavia");
			accessoBeanRemote.logout();
			accessoBeanRemote.login("cavia", "cavia");
			Assert.assertTrue(accessoBeanRemote.rifiutaRichiestaAmicizia("tester"));
			accessoBeanRemote.logout();
		} catch (FamaBeanEccezione e) {
			Assert.fail();
		}
	}
	
	@After
	public void after() throws Exception {
		cleanEntities();
	}

}
