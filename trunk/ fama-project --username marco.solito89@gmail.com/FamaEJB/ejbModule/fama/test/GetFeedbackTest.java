package fama.test;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fama.obj.FeedbackObj;
import fama.sb.FamaBeanEccezione;

public class GetFeedbackTest extends FamaTest{

	@Before
	public void before() throws Exception {
		init();
	}
	
	@Test
	public void testGetFeedback() {
		rbr.registraUtente("tester", "tester", null, null, null, null);
		rbr.registraUtente("cavia", "cavia", null, null, null, null);
		try {
			accessoBeanRemote.login("tester", "tester");
			accessoBeanRemote.richiediAiuto("cavia","aiutoProva");
			accessoBeanRemote.logout();
			accessoBeanRemote.login("cavia", "cavia");
			Assert.assertTrue(accessoBeanRemote.confermaRichiestaAiuto("tester",1,"aiutoProvaDaTester"));
			accessoBeanRemote.logout();
			accessoBeanRemote.login("tester", "tester");
			Assert.assertTrue(accessoBeanRemote.rilasciaFeedback("cavia", 1, "valutazioneProva", "commentoProva"));
			FeedbackObj fb = (FeedbackObj) accessoBeanRemote.getFeedback(1);
			Assert.assertEquals(1, fb.getId());
			Assert.assertEquals(1, fb.getIdAiuto());
			Assert.assertEquals("tester", fb.getUtente1());
			Assert.assertEquals("cavia", fb.getUtente2());
			Assert.assertEquals("valutazioneProva", fb.getValutazione());
			Assert.assertEquals("commentoProva", fb.getCommento());
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
