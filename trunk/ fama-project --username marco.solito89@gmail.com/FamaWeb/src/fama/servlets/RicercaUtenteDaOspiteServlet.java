package fama.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fama.obj.UtenteRegistratoObj;
import fama.sb.AccessoOspiteRemote;

/**
 * Servlet implementation class RicercaUtenteDaOspiteServlet
 */
public class RicercaUtenteDaOspiteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RicercaUtenteDaOspiteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    static private Context getInitialContext() throws Exception {
		Hashtable<String,String> env = new Hashtable<String,String>();
		env.put( Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory" );
		env.put( Context.PROVIDER_URL, "localhost:1099" );

		return new InitialContext( env );
	}
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("/FamaWeb/home/home.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			AccessoOspiteRemote aor = (AccessoOspiteRemote) getInitialContext().lookup("AccessoOspiteRemote");
			String chiave = request.getParameter("chiaveRicercaUtenti");
			ArrayList<UtenteRegistratoObj> arrayUtentiTrovati = (ArrayList<UtenteRegistratoObj>) aor.ricercaUtenti(chiave);
			if (arrayUtentiTrovati.size() == 0) {
				request.getSession().setAttribute("errore", "lista vuota");
			} else {

				request.getSession().setAttribute("listaUtentiTrovati", arrayUtentiTrovati);

			}
		} catch (Exception e) {
		}
		response.sendRedirect("/FamaWeb/access/ospite.jsp");
	}

}
