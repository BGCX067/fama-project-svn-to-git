package fama.test;

import java.util.ArrayList;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fama.obj.AbilitaObj;
import fama.sb.FamaBeanEccezione;

public class NuoveAbilitaAdminTest extends FamaTest{

	@Before
	public void before() throws Exception{
		init();
	}
	
	@After
	public void after() throws Exception{
		cleanEntities();
	}
	@Test
	public void testNuoveAbilitaAdmin() {
		Assert.assertNull(accessoBeanRemote.nuoveAbilitaAdmin());
		
		try {
			rbr.registraUtente("primo", "primo", null, null, null, null);
			accessoBeanRemote.login("primo", "primo");
			accessoBeanRemote.richiediNuovaAbilita("abilitaProva1", null, null);
			accessoBeanRemote.logout();
			accessoBeanRemote.accettaAbilitaAdmin("abilitaProva1");
			
			rbr.registraUtente("secondo", "secondo", null, null, null, null);
			accessoBeanRemote.login("secondo", "secondo");
			accessoBeanRemote.richiediNuovaAbilita("abilitaProva2", null, null);
			accessoBeanRemote.logout();
			
			rbr.registraUtente("terzo", "terzo", null, null, null, null);
			accessoBeanRemote.login("terzo", "terzo");
			accessoBeanRemote.richiediNuovaAbilita("abilitaProva3", null, null);
			accessoBeanRemote.logout();
		} catch (FamaBeanEccezione e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<AbilitaObj> abilitaDaAccettare= (ArrayList<AbilitaObj>) accessoBeanRemote.nuoveAbilitaAdmin();
		for(AbilitaObj a:abilitaDaAccettare){
			if(a.getNome().equals("abilitaProva1")){
				Assert.assertTrue(a.isAccettata());
			}
			if(a.getNome().equals("abilitaProva2") || a.getNome().equals("abilitaProva3")){
				Assert.assertFalse(a.isAccettata());
			}
		}
		
	}

}
