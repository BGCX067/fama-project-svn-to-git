package fama.test;

import java.util.ArrayList;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fama.obj.AbilitaObj;
import fama.sb.FamaBeanEccezione;

public class InserisciNuoveAbilitaAdminTest extends FamaTest{
	
	@Before
	public void before() throws Exception{
		init();
	}
	
	@After
	public void after() throws Exception{
		cleanEntities();
	}
	@Test
	public void testInserisciNuoveAbilitaAdmin() {
		try {
			accessoBeanRemote.loginAdmin("admin", "0000");
		} catch (FamaBeanEccezione e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			accessoBeanRemote.inserisciNuoveAbilitaAdmin("abilitaProva1", "settoreProva1", "descrizioneProva1");
		} catch (FamaBeanEccezione e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ArrayList<AbilitaObj> a= (ArrayList<AbilitaObj>) accessoBeanRemote.listaAbilitaInSistema();
		Assert.assertEquals(a.size(), 1);
		Assert.assertEquals(a.get(0).getNome(), "abilitaProva1");
		Assert.assertEquals(a.get(0).getSettore(), "settoreProva1");
		Assert.assertEquals(a.get(0).getDescrizione(), "descrizioneProva1");
		Assert.assertEquals(a.get(0).getRichiedente(), "admin");
		Assert.assertTrue(a.get(0).isAccettata());
		
		try {
			accessoBeanRemote.inserisciNuoveAbilitaAdmin("abilitaProva1", "settoreProva1", "descrizioneProva1");
			//entra nel catch perchè già stata inserita
		} catch (FamaBeanEccezione e) {
			a= (ArrayList<AbilitaObj>) accessoBeanRemote.listaAbilitaInSistema();
			Assert.assertEquals(a.size(), 1);
			Assert.assertEquals(a.get(0).getNome(), "abilitaProva1");
			Assert.assertEquals(a.get(0).getSettore(), "settoreProva1");
			Assert.assertEquals(a.get(0).getDescrizione(), "descrizioneProva1");
			Assert.assertEquals(a.get(0).getRichiedente(), "admin");
			Assert.assertTrue(a.get(0).isAccettata());
		}
		
		try {
			accessoBeanRemote.inserisciNuoveAbilitaAdmin("abilitaProva2", "settoreProva2", "descrizioneProva2");
		} catch (FamaBeanEccezione e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		a= (ArrayList<AbilitaObj>) accessoBeanRemote.listaAbilitaInSistema();
		Assert.assertEquals(a.size(), 2);
		Assert.assertEquals(a.get(1).getNome(), "abilitaProva2");
		Assert.assertEquals(a.get(1).getSettore(), "settoreProva2");
		Assert.assertEquals(a.get(1).getDescrizione(), "descrizioneProva2");
		Assert.assertEquals(a.get(1).getRichiedente(), "admin");
		Assert.assertTrue(a.get(1).isAccettata());
		
	}

}
