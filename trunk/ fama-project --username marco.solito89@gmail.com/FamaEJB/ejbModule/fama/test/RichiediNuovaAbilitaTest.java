package fama.test;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RichiediNuovaAbilitaTest extends FamaTest {

	@Before
	public void before() throws Exception{
		init();
	}
	
	@Test
	public void testRichiediNuovaAbilita() {
		try {
			rbr.registraUtente("tester", "tester", null, null, null, null);
			accessoBeanRemote.login("tester", "tester");
			Assert.assertTrue(accessoBeanRemote.richiediNuovaAbilita("abilitaProva", "settoreProva", "descrizioneProva"));
			accessoBeanRemote.logout();
		} catch (Exception e) {
			Assert.fail();
		}
	}

	@After
	public void after() throws Exception{
		cleanEntities();
	}
	
}
