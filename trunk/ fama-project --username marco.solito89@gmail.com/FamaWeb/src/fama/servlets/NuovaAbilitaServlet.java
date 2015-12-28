package fama.servlets;

import java.io.IOException;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fama.sb.AccessoBeanRemote;
import fama.sb.FamaBeanEccezione;

/**
 * Servlet implementation class NuovaAbilitaServlet
 */
public class NuovaAbilitaServlet extends HttpServlet {
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
    public NuovaAbilitaServlet() {
        super();
    }

        /**
         * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
         */
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                if(request.getSession().getAttribute("login") == null){
                        response.sendRedirect("/FamaWeb/access/loginAdmin.jsp");
                } else {
                        doPost(request, response);
                }
        }

        /**
         * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
         */
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                if(request.getSession().getAttribute("login") == null){
                        response.sendRedirect("/FamaWeb/home/home.jsp");
                        return;
                }
                try {
                        AccessoBeanRemote abr = (AccessoBeanRemote) request.getSession().getAttribute("AccessoBeanRemote");
                        String nome = request.getParameter("nomeNuovaAbilita");
                        String settore = request.getParameter("settore");
                        String descrizione = request.getParameter("descrizione");
                        try{
                                abr.richiediNuovaAbilita(nome,settore,descrizione);
                                request.setAttribute("nuovaAbilita", "si");
                        } catch (FamaBeanEccezione e) {
                                request.getSession().setAttribute("errore",e.getMessage());
                        }
                        response.sendRedirect("/FamaWeb/personale/nuovaAbilita.jsp");
                } catch ( Exception e) {
                        e.printStackTrace();
                }
                
                
        }

}
