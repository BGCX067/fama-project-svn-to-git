<%@page import="java.io.PrintWriter"%>
<%@page import="fama.obj.UtenteRegistratoObj"%>
<%@page import="fama.obj.AbilitaObj"%>
<%@page import="java.util.ArrayList"%>
<%@page import="fama.sb.AccessoBeanRemote"%>
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
if(request.getSession().getAttribute("AccessoBeanRemote") == null){
	response.sendRedirect("/FamaWeb/home/home.jsp");
} 
	UtenteRegistratoObj uro = (UtenteRegistratoObj) request.getSession().getAttribute("UtenteRegistratoObj");
	String username = uro.getUsername();
	if(username.equals("admin")){
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
%>
<div id="templatemo_body_wrapper">
	<div id="templatemo_wrapper">
    	
        <div id="templatemo_header">
            <div id="site_title"><h1><a href="/FamaWeb/home/home.jsp">Swimv2</a></h1></div>
            <div id="templatemo_menu">
                <ul>
                  <li><a href="/FamaWeb/home/home.jsp">Home</a></li>
                  <li><a href="/FamaWeb/personale/dichiaraAbilita.jsp" class="current">Dichiara Abilita'</a></li>
                  <li><a href="/FamaWeb/personale/paginaPersonale.jsp" class="last">Personale</a></li>
                </ul>
            </div> <!-- end of templatemo_menu -->
        </div><!-- end of header -->
        
        <div id="templatemo_middle">
            <div id="mid_left">
                <div id="mid_title">SWIMv2: Small World hypothesIs Machine v2</div>
                <p>Puoi aggiungere delle abilita' a quelle gia' dichiarate.</p>
				<p>Dalla lista di abilita' sottostante scegli quelle che vorresti aggiungere alle tue abilita' personali.</p>
            </div>
            <img src="/FamaWeb/images/profilo.jpg" alt="nessuna immagine" />
        </div> <!-- end of middle -->
        
        <div id="templatemo_main">
        <div class="cbox_fw">
            	<div class="cbox_large float_l">
            	<p>
<b>Lista Abilita' presenti nel sistema</b>:<br><br>
<%
AccessoBeanRemote abr = (AccessoBeanRemote) request.getSession().getAttribute("AccessoBeanRemote");
ArrayList<AbilitaObj> listaAbilita = (ArrayList<AbilitaObj>) abr.listaAbilitaInSistema();
for(AbilitaObj a: listaAbilita){
	out.println("NOME: " + a.getNome() + "<br>" + 
				"SETTORE: "+a.getSettore() + "<br>" + 
				"DESCRIZIONE" + a.getDescrizione() + "<br>" +
				"<a href=\"/FamaWeb/DichiaraAbilitaServlet?nomeAbilita="+ a.getNome() + "&settore="+a.getSettore() + "&descrizione="+a.getDescrizione() + "\">dichiara</a>" + " come abilita' personale" +
				"<br><br>");
}

if(request.getSession().getAttribute("errore") != null){
	out.println("<span id=\"error\">"+request.getSession().getAttribute("errore")+"</span>"+ "<br>");
	request.getSession().removeAttribute("errore");
}

%>          	
            	</p>
            	</div>
        </div>
        </div>
        
	</div>
</div>
<%
}
%>
<div id="templatemo_footer_wrapper">
    <div id="templatemo_footer">
        Designed by FAMA PROJECT
        <div class="cleaner"></div>
    </div>
</div>
</body>
</html>