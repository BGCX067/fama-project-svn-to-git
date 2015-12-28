package fama.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fama.obj.AbilitaObj;
import fama.sb.AccessoBeanRemote;

/**
 * Servlet implementation class NuoveAbilitaAdminServlet
 */
public class NuoveAbilitaAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NuoveAbilitaAdminServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<AbilitaObj> abilitaDaAccettare;
		AccessoBeanRemote abr = (AccessoBeanRemote) request.getSession().getAttribute("AccessoBeanRemote");
		
		if(request.getSession().getAttribute("login") == null){
            response.sendRedirect("/FamaWeb/access/loginAdmin.jsp");
            return;
		}
		
		if(abr != null && request.getParameter("nomeAbilita")==null){
			abilitaDaAccettare = (ArrayList<AbilitaObj>) abr.nuoveAbilitaAdmin();
			request.getSession().setAttribute("abilitaDaAccettare", abilitaDaAccettare);
			response.sendRedirect("/FamaWeb/personale/nuoveAbilitaAdmin.jsp");
		}
		
		else{
			if(request.getParameter("isAccettata").equals("si")){
				abr.accettaAbilitaAdmin(request.getParameter("nomeAbilita"));
				response.sendRedirect("/FamaWeb/NuoveAbilitaAdminServlet");
				
			} else {
				abr.rifiutaAbilitaAdmin(request.getParameter("nomeAbilita"));
				response.sendRedirect("/FamaWeb/NuoveAbilitaAdminServlet");
			}
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
