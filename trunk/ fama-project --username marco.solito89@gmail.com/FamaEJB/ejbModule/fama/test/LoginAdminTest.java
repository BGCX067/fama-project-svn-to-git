package fama.test;

import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fama.obj.UtenteRegistratoObj;

public class LoginAdminTest extends FamaTest{

	@Before
	public void before() throws Exception {
		init();
	}

	@After
	public void after() throws Exception {
		cleanEntities();
	}

	@Test
	public void testLoginAdmin() {
		UtenteRegistratoObj adminProva = (UtenteRegistratoObj) tbr.creaAdminProva();
		Assert.assertEquals(adminProva.getUsername(), "admin");
		Assert.assertEquals(adminProva.getPassword(), "0000");
		Assert.assertTrue(adminProva.getAdmin());
	}

}
