package fama.test;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;

import fama.sb.AccessoBeanRemote;
import fama.sb.AccessoOspiteRemote;
import fama.sb.RegistrazioneBeanRemote;

public abstract class FamaTest {

	protected static Context context;
	protected static AccessoBeanRemote accessoBeanRemote;
	protected static RegistrazioneBeanRemote rbr;
	protected static AccessoOspiteRemote aor;
	protected static TestBeanRemote tbr;
	
	public static void init() throws Exception {
		context = getInitialContext();
		accessoBeanRemote = (AccessoBeanRemote)context.lookup( "AccessoBeanRemote" );
		rbr = (RegistrazioneBeanRemote)context.lookup( "RegistrazioneBeanRemote" );
		aor = (AccessoOspiteRemote)context.lookup( "AccessoOspiteRemote" );
		tbr = (TestBeanRemote) context.lookup("TestBeanRemote");
		
		tbr.cleanEntities();
	}
	
	static private Context getInitialContext() throws Exception {
		Hashtable<String,String> env = new Hashtable<String,String>();
		env.put( Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory" );
		env.put( Context.PROVIDER_URL, "localhost:1099" );

		return new InitialContext( env );
	}
	
	public static void cleanEntities() throws Exception{
		tbr.cleanEntities();
	}
	
}
