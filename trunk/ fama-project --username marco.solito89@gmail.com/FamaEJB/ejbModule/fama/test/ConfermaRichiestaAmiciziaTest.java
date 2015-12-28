package fama.test;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fama.sb.FamaBeanEccezione;

public class ConfermaRichiestaAmiciziaTest extends FamaTest {

	@Before
	public void before() throws Exception {
		init();
	}
	
	@Test
	public void testConfermaRichiestaAmicizia() {
		rbr.registraUtente("tester", "tester", null, null, null, null);
		rbr.registraUtente("cavia", "cavia", null, null, null, null);
		try {
			accessoBeanRemote.login("tester", "tester");
			accessoBeanRemote.richiediAmicizia("cavia");
			accessoBeanRemote.logout();
			accessoBeanRemote.login("cavia", "cavia");
			Assert.assertTrue(accessoBeanRemote.confermaRichiestaAmicizia("tester"));
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
