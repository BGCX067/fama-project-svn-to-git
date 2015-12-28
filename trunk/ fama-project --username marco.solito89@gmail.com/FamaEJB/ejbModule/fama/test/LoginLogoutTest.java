package fama.test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import fama.obj.UtenteRegistratoObj;
import fama.sb.FamaBeanEccezione;

public class LoginLogoutTest extends FamaTest{

	@Before
	public void before() throws Exception {
		init();
	}
	
	@After
	public void after() throws Exception{
		cleanEntities();
	}
	
	@Test
	public void testLogin() {
		rbr.registraUtente("usernameProva", "passwordProva", null, null, null, null);
		try {
			UtenteRegistratoObj ur = (UtenteRegistratoObj) accessoBeanRemote.login("usernameProva", "passwordProva");
			Assert.assertNotNull(ur);
			Assert.assertEquals("usernameProva", ur.getUsername());
			Assert.assertEquals("passwordProva", ur.getPassword());
			accessoBeanRemote.logout();
		} catch (FamaBeanEccezione e) {
			Assert.fail();
		}
	}

}
