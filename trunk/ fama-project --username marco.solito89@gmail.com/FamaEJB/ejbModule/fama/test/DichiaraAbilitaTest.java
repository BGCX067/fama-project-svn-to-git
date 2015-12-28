package fama.test;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DichiaraAbilitaTest extends FamaTest {
	
	@Before
	public void before() throws Exception{
		init();
	}
	
	@After
	public void after() throws Exception{
		cleanEntities();
	}
	
	@Test
	public void testDichiaraAbilita() {
		try {
			rbr.registraUtente("tester", "tester", null, null, null, null);
			accessoBeanRemote.login("tester", "tester");
			accessoBeanRemote.richiediNuovaAbilita("abilitaProva", "settoreProva", "descrizioneProva");
			accessoBeanRemote.logout();
			accessoBeanRemote.loginAdmin("admin", "0000");
			accessoBeanRemote.accettaAbilitaAdmin("abilitaProva");
			accessoBeanRemote.logout();
			accessoBeanRemote.login("tester", "tester");
			Assert.assertTrue(accessoBeanRemote.dichiaraAbilita("abilitaProva"));
			accessoBeanRemote.logout();
		} catch (Exception e) {
			Assert.fail();
		}
		
	}

}
