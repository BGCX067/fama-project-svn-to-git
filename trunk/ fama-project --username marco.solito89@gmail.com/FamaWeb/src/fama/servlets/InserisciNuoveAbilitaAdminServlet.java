package fama.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fama.sb.AccessoBeanRemote;
import fama.sb.FamaBeanEccezione;

/**
 * Servlet implementation class InserisciNuoveAbilitaAdminServlet
 */
public class InserisciNuoveAbilitaAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InserisciNuoveAbilitaAdminServlet() {
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
		if(request.getSession().getAttribute("AccessoBeanRemote") !=null){
			AccessoBeanRemote abr = (AccessoBeanRemote) request.getSession().getAttribute("AccessoBeanRemote");
			String nome = request.getParameter("nomeAbilita");
			if(request.getParameter("nomeAbilita").equals("")){
				request.getSession().setAttribute("nomeAbilitaVuoto", "nomeAbilitaVuoto");
				response.sendRedirect("/FamaWeb/personale/inserisciNuoveAbilitaAdmin.jsp");
				return;
			}
			String settore = request.getParameter("settore");
			String descrizione = request.getParameter("descrizione");
			try {
				abr.inserisciNuoveAbilitaAdmin(nome,settore,descrizione);
			} catch (FamaBeanEccezione e) {
				request.getSession().setAttribute("errore", e.getMessage());
			}
			response.sendRedirect("/FamaWeb/personale/inserisciNuoveAbilitaAdmin.jsp");
		} else {
			response.sendRedirect("/FamaWeb/access/loginAdmin.jsp");
		}
	}

}
