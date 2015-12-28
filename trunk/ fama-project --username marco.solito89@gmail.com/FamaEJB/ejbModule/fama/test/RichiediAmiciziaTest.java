package fama.test;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fama.sb.FamaBeanEccezione;

public class RichiediAmiciziaTest extends FamaTest{

	@Before
	public void before() throws Exception {
		init();
	}
	
	@After
	public void after() throws Exception {
		cleanEntities();
	}
	
	@Test
	public void testRichiediAmicizia() {
		rbr.registraUtente("tester", "tester", null, null, null, null);
		rbr.registraUtente("usernameProva", "passwordProva", null, null, null, null);
		try {
			accessoBeanRemote.login("tester", "tester");
			Assert.assertTrue(accessoBeanRemote.richiediAmicizia("usernameProva"));
			accessoBeanRemote.logout();
		} catch (FamaBeanEccezione e) {
			Assert.fail();
		}
	}

}
