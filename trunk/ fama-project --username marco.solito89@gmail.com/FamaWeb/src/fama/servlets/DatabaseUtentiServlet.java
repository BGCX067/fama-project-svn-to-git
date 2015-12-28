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
 * Servlet implementation class DatabaseUtentiServlet
 */
public class DatabaseUtentiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DatabaseUtentiServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getSession().getAttribute("login") == null){
		  response.sendRedirect("/FamaWeb/access/loginAdmin.jsp"); return; }
		 
		if (request.getSession().getAttribute("AccessoBeanRemote") != null) {
			AccessoBeanRemote abr = (AccessoBeanRemote) request.getSession().getAttribute("AccessoBeanRemote");
			ArrayList<UtenteRegistratoObj> databaseUtenti = (ArrayList<UtenteRegistratoObj>) abr.databaseUtentiAdmin();
			request.getSession().setAttribute("databaseUtenti", databaseUtenti);
			response.sendRedirect("/FamaWeb/personale/databaseUtenti.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
