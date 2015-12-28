package fama.servlets;

import java.io.IOException;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fama.obj.UtenteRegistratoObj;
import fama.sb.AccessoBeanRemote;
import fama.sb.FamaBeanEccezione;

/**
 * Servlet implementation class loginAdminServlet
 */
public class LoginAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	static private Context getInitialContext() throws Exception {
		Hashtable<String,String> env = new Hashtable<String,String>();
		env.put( Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory" );
		env.put( Context.PROVIDER_URL, "localhost:1099" );

		return new InitialContext( env );
	}
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginAdminServlet() {
        super();
        // TODO Auto-generated constructor stub
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
		try {
			UtenteRegistratoObj uro;
			if (request.getSession().getAttribute("login") == null) {
				String username = request.getParameter("username");
				String password = request.getParameter("password");
				AccessoBeanRemote abr = (AccessoBeanRemote) getInitialContext().lookup("AccessoBeanRemote");
				try {
					uro = (UtenteRegistratoObj) abr.loginAdmin(username, password);
					request.getSession().setAttribute("login", "si");
					request.getSession().setAttribute("UtenteRegistratoObj", uro);
					request.getSession().setAttribute("AccessoBeanRemote", abr);
				} catch (FamaBeanEccezione e) {
					//redirect alla pagina di login
					request.getSession().setAttribute("errore", e.getMessage());
					response.sendRedirect("/FamaWeb/access/loginAdmin.jsp");
					return;
				}
			}
			//redirect verso la pagina personale
			response.sendRedirect("/FamaWeb/personale/paginaPersonaleAdmin.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
