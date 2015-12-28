package fama.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fama.sb.AccessoBeanRemote;

/**
 * Servlet implementation class RichiestaAmiciziaServlet
 */
public class RichiestaAmiciziaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RichiestaAmiciziaServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("login") == null){
			response.sendRedirect("/FamaWeb/home/home.jsp");
			return;
		}
		if(request.getParameter("gestioneRichiestaAmicizia") != null){
			gestioneRichiestaAmicizia(request, response);
		} else {
			doPost(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getSession().getAttribute("AccessoBeanRemote") == null) {
			response.sendRedirect("/FamaWeb/access/login.jsp");
		}
		String to = request.getParameter("to");
		AccessoBeanRemote abr = (AccessoBeanRemote) request.getSession().getAttribute("AccessoBeanRemote");
		if (abr.richiediAmicizia(to)) {
			request.getSession().setAttribute("amico", to);
		}
		response.sendRedirect("/FamaWeb/personale/ricercaUtente.jsp");
	}
	
	private void gestioneRichiestaAmicizia(HttpServletRequest request, HttpServletResponse response) throws IOException {
		AccessoBeanRemote abr = (AccessoBeanRemote) request.getSession().getAttribute("AccessoBeanRemote");
		String to = request.getParameter("to");
		if(request.getParameter("gestioneRichiestaAmicizia").equals("conferma")){
			abr.confermaRichiestaAmicizia(to);
		} else {
			abr.rifiutaRichiestaAmicizia(to);
		}
		response.sendRedirect("/FamaWeb/personale/paginaPersonale.jsp");
	}

}
