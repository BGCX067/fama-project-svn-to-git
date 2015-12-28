package fama.test;

import java.util.ArrayList;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fama.obj.AbilitaObj;

public class ListaAbilitaDichiarateTest extends FamaTest{

	@Before
	public void before() throws Exception{
		init();
	}
	
	@After
	public void after() throws Exception{
		cleanEntities();
	}
	
	@Test
	public void testListaAbilitaDichiarate() {
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
			ArrayList<AbilitaObj> arrayAbilita = (ArrayList<AbilitaObj>) accessoBeanRemote.listaAbilitaDichiarate();
			Assert.assertEquals("abilitaProva", arrayAbilita.get(0).getNome());
			accessoBeanRemote.logout();
		} catch (Exception e) {
			Assert.fail();
		}
	}

}
