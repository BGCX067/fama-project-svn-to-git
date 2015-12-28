package fama.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fama.obj.UtenteRegistratoObj;

public class DatabaseUtentiAdminTest extends FamaTest {
	
	@Before
	public void before() throws Exception{
		init();
	}
	
	@After
	public void after() throws Exception{
		cleanEntities();
	}
	
	@Test
	public void testDatabaseUtentiAdmin() {
		rbr.registraUtente("utente1", "1111", null, null, null, null);
		rbr.registraUtente("utente2", "2222", null, null, null, null);
		ArrayList<UtenteRegistratoObj> databaseUtenti= (ArrayList<UtenteRegistratoObj>) accessoBeanRemote.databaseUtentiAdmin();
		assertEquals(databaseUtenti.size(), 3); //perchè l'admin è gia' nel database.
	}
}
