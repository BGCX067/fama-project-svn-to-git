package fama.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fama.sb.AccessoBeanRemote;

/**
 * Servlet implementation class LogoutUtenteServlet
 */
public class LogoutUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogoutUtenteServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("login") == null){
			//login non effettuato
			response.sendRedirect("/FamaWeb/access/login.jsp");
		}
		AccessoBeanRemote abr = (AccessoBeanRemote) request.getSession().getAttribute("AccessoBeanRemote");
		abr.logout();
		request.getSession().removeAttribute("AccessoBeanRemote");
		request.getSession().removeAttribute("UtenteRegistratoObj");
		request.getSession().removeAttribute("login");
		response.sendRedirect("/FamaWeb/home/home.jsp");
	}

}
