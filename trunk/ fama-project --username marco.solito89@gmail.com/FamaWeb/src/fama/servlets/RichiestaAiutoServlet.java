package fama.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fama.sb.AccessoBeanRemote;

/**
 * Servlet implementation class RichiestaAiutoServlet
 */
public class RichiestaAiutoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RichiestaAiutoServlet() {
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
		if(request.getParameter("gestisciFeedback") != null){
			gestioneRilascioFeedback(request, response);
			return;
		}
		if(request.getParameter("gestioneRichiestaAiuto") != null){
			gestioneRichiestaAiuto(request, response);
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
		String descrizione = request.getParameter("descrizione");
		AccessoBeanRemote abr = (AccessoBeanRemote) request.getSession().getAttribute("AccessoBeanRemote");
		if (abr.richiediAiuto(to,descrizione)) {
			request.getSession().setAttribute("aiuto", to);
		}
		response.sendRedirect("/FamaWeb/personale/ricercaUtente.jsp");
	}

	private void gestioneRichiestaAiuto(HttpServletRequest request, HttpServletResponse response) throws IOException {
		AccessoBeanRemote abr = (AccessoBeanRemote) request.getSession().getAttribute("AccessoBeanRemote");
		String to = request.getParameter("to");
		String rispostaAiuto = request.getParameter("rispostaAiuto");
		long idAiuto = Long.parseLong(request.getParameter("idAiuto"));
		if(request.getParameter("gestioneRichiestaAiuto").equals("conferma")){
			abr.confermaRichiestaAiuto(to,idAiuto,rispostaAiuto);
		} else {
			abr.rifiutaRichiestaAiuto(to,idAiuto);
		}
		response.sendRedirect("/FamaWeb/personale/paginaPersonale.jsp");
	}
	
	private void gestioneRilascioFeedback(HttpServletRequest request, HttpServletResponse response) throws IOException {
		AccessoBeanRemote abr = (AccessoBeanRemote) request.getSession().getAttribute("AccessoBeanRemote");
		String to = request.getParameter("to");
		long idAiuto = Long.parseLong(request.getParameter("idAiuto"));
		String valutazione = request.getParameter("valutazione");
		String commento = request.getParameter("commento");
		abr.rilasciaFeedback(to, idAiuto, valutazione, commento);
		response.sendRedirect("/FamaWeb/personale/paginaPersonale.jsp");
	}
	
}
