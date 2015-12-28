package fama.test;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fama.sb.FamaBeanEccezione;

public class RifiutaAbilitaAdminTest extends FamaTest{
	
	@Before
	public void before() throws Exception{
		init();
	}
	
	@After
	public void after() throws Exception{
		cleanEntities();
	}
	@Test
	public void testRifiutaAbilitaAdmin() {
		Assert.assertFalse(accessoBeanRemote.accettaAbilitaAdmin("abilitaNonEsistente"));
		try {
			rbr.registraUtente("primo", "primo", null, null, null, null);
			accessoBeanRemote.login("primo", "primo");
			accessoBeanRemote.richiediNuovaAbilita("abilitaProva1", null, null);
			accessoBeanRemote.logout();
			Assert.assertTrue(accessoBeanRemote.accettaAbilitaAdmin("abilitaProva1"));
		} catch (FamaBeanEccezione e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assert.assertNull(accessoBeanRemote.nuoveAbilitaAdmin());
	}

}
