package fama.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fama.obj.UtenteRegistratoObj;
import fama.sb.AccessoBeanRemote;

/**
 * Servlet implementation class RicercaUtenteServlet
 */
public class RicercaUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RicercaUtenteServlet() {
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
			response.sendRedirect("/FamaWeb/home/home.jsp");
			return;
		}
		if(request.getSession().getAttribute("AccessoBeanRemote") != null){
			AccessoBeanRemote abr = (AccessoBeanRemote) request.getSession().getAttribute("AccessoBeanRemote");
			ArrayList<UtenteRegistratoObj> listaUtenti = (ArrayList<UtenteRegistratoObj>) abr.ricercaUtente(request.getParameter("chiaveRicerca"));
			//settare attributo temporaneo per mostrare risultati nella pagina ricercaUtente.jsp
			request.getSession().setAttribute("risultatoRicerca", listaUtenti);
			response.sendRedirect("/FamaWeb/personale/ricercaUtente.jsp");
		}
	}

}
