<%@page import="java.io.PrintWriter"%>
<%@page import="fama.obj.RichiestaAiutoObj"%>
<%@page import="fama.obj.RichiestaAmiciziaObj"%>
<%@page import="java.util.ArrayList"%>
<%@page import="fama.sb.AccessoBeanRemote"%>
<%@page import="fama.obj.UtenteRegistratoObj"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
 <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Swim v2</title>
<link href="/FamaWeb/cssTemplate/templatemo_style.css" rel="stylesheet" type="text/css" />

</head>
<body>

<%
if(request.getSession().getAttribute("login") == null){
	response.sendRedirect("/FamaWeb/home/home.jsp");
}
	UtenteRegistratoObj uro1 = (UtenteRegistratoObj) request.getSession().getAttribute("UtenteRegistratoObj");
	String username = uro1.getUsername();
	if(!username.equals("admin")){
		PrintWriter pr = response.getWriter();
		pr.println("<html>" +
					"<body>" +
					"Devi effettuare il logout se vuoi cambiare utente<br>" +
					"Redirect in 3 secondi...<br>");
		pr.println(	"<script type=\"text/javascript\">" +
					"setTimeout(\"document.location.href='/FamaWeb/home/home.jsp'\",3000);" +
					"</script>" );
		pr.println("</body>" +
					"</html>");
	} else  {

      AccessoBeanRemote abr = (AccessoBeanRemote) request.getSession().getAttribute("AccessoBeanRemote");
      UtenteRegistratoObj uro = (UtenteRegistratoObj) request.getSession().getAttribute("UtenteRegistratoObj");
      out.println( "<span id=\"error\">Pagina Personale dell'ADMIN: " + uro.getNome() + " " + uro.getCognome() + " (" +uro.getUsername() + ") " + "</span><br>" );
  
%>
<div id="templatemo_body_wrapper">
	<div id="templatemo_wrapper">
    	
        <div id="templatemo_header">
            <div id="site_title"><h1><a href="/FamaWeb/home/home.jsp">Swimv2</a></h1></div>
            <div id="templatemo_menu">
                <ul>
                  <li><a href="/FamaWeb/personale/paginaPersonaleAdmin.jsp" class="current">Pagina personale admin</a></li>
                  <li><a href="/FamaWeb/home/home.jsp">Home</a></li>
                  <li><a href="/FamaWeb/LogoutUtenteServlet">Logout</a></li>
                </ul>
            </div> <!-- end of templatemo_menu -->
        </div><!-- end of header -->
        
        <div id="templatemo_middle">
            <div id="mid_left">
                <div id="mid_title">SWIMv2: Small World hypothesIs Machine v2</div>
                <p><h2>PAGINA PERSONALE</h2></p><h2> AMMINISTRATORE:</h2>
   <p><h5>
<%
   out.println("<span id=\"error\">"+uro.getNome() + "  " + uro.getCognome()+"</span><br>" );
%>
   </h5></p>
            </div>
            <img src="/FamaWeb/images/admin.gif" alt="ADMIN" />
        </div> <!-- end of middle -->
        
        <div id="templatemo_main">
        
        	<div class="cbox_fw">
                <div class="cbox_3b fp_box"><span class="b1"></span>
                    <h4><a href="/FamaWeb/NuoveAbilitaAdminServlet">Richieste nuove abilita'</a></h4>
                    <p>Per visualizzare le richieste degli utenti di inserimento di abilita' non ancora presenti in Swim v2.</p>
                </div>
                <div class="cbox_3b fp_box"><span class="b1"></span>
                    <h4><a href="/FamaWeb/personale/inserisciNuoveAbilitaAdmin.jsp">Inserisci nuove abilita'</a></h4>
                    <p>Potrai inserire nuove abilita' da mettere a disposizione agli utenti.</p>
                </div>
                <div class="cbox_3b fp_box"><span class="b2"></span>
                    <h4><a href="/FamaWeb/personale/ricercaUtenteAdmin.jsp">Ricerca Utente</a></h4>
                    <p>Potrai ricercare un utente per username, nome o cognome.</p>
                </div>
                <div class="cbox_3b cbox_rm fp_box"><span class="b3"></span>
                    <h4><a href="/FamaWeb/DatabaseUtentiServlet">Database Utenti Registrati</a></h4>
                    <p>Per tenere sotto controllo tutti gli utenti registrati di Swim v2.</p>
                </div>
                <div class="cleaner"></div>
            </div>
            
            <div class="cbox_fw"></div>
            </div>
            </div>
            </div>

<div id="templatemo_footer_wrapper">
    <div id="templatemo_footer">
        Designed by FAMA PROJECT
        <div class="cleaner"></div>
    </div>
</div>
<%
}
%>
</body>
</html>