package fama.test;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fama.sb.FamaBeanEccezione;

public class RichiediAiutoTest extends FamaTest{

	@Before
	public void before() throws Exception {
		init();
	}
	
	@Test
	public void testRichiediAiuto() {
		rbr.registraUtente("tester", "tester", null, null, null, null);
		rbr.registraUtente("cavia", "cavia", null, null, null, null);
		try {
			accessoBeanRemote.login("tester", "tester");
			Assert.assertTrue(accessoBeanRemote.richiediAiuto("cavia","aiutoProva"));
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
