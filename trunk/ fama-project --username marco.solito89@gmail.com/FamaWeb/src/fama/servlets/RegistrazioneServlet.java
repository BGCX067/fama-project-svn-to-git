package fama.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fama.sb.RegistrazioneBeanRemote;

/**
 * Servlet implementation class RegistrazioneServlet
 */
public class RegistrazioneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrazioneServlet() {
        super();
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
		try {
			PrintWriter pr = response.getWriter();
			RegistrazioneBeanRemote rbr = (RegistrazioneBeanRemote) getInitialContext().lookup("RegistrazioneBeanRemote");
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String nome = request.getParameter("nome");
			String cognome = request.getParameter("cognome");
			String email = request.getParameter("email");
			String tel = request.getParameter("tel");
			if (rbr.registraUtente(username, password, nome, cognome, email, tel)) {
				pr.println("<html>" +
							"<body>" +
							"Registrazione effettuata con successo<br>" +
							"Redirect in 3 secondi...<br>");
				pr.println(	"<script type=\"text/javascript\">" +
							"setTimeout(\"document.location.href='/FamaWeb/home/home.jsp'\",3000);" +
							"</script>" );
				pr.println("</body>" +
							"</html>");
				//response.sendRedirect("/FamaWeb/home/home.jsp");
			} else {
				//pr.println("Errore nella registrazione, riprova");
				request.getSession().setAttribute("errore","username occupato");
				response.sendRedirect("/FamaWeb/registrazione/registrazione.jsp");
				//request.getRequestDispatcher("/registrazione/registrazione.jsp").forward( request, response );
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
