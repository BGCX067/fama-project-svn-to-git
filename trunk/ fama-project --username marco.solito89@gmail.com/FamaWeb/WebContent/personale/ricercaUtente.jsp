<%@page import="java.io.PrintWriter"%>
<%@page import="fama.obj.UtenteRegistratoObj"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Swim v2</title>
<link href="/FamaWeb/cssTemplate/templatemo_style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
function validaForm(){
	var descrizione = document.ricercaUtente.descrizione.value;
	
	if(descrizione==""){
		alert('La richiesta aiuto non può essere vuota');
		return;
	}
	
	document.ricercaUtente.action = "/FamaWeb/RichiestaAiutoServlet";
	document.ricercaUtente.submit();
}
</script>
</head>
<body>

<%
if(request.getSession().getAttribute("login") == null){
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
                  <li><a href="/FamaWeb/personale/ricercaUtente.jsp" class="current">Cerca</a></li>
                  <li><a href="/FamaWeb/personale/paginaPersonale.jsp" class="last">Pagina Personale</a></li>
                </ul>
            </div> <!-- end of templatemo_menu -->
        </div><!-- end of header -->

        <div id="templatemo_middle">
            <div id="mid_left">
                <div id="mid_title">SWIMv2: Small World hypothesIs Machine v2</div>
                <p>Pagina di ricerca degli utenti.</p>
				<p>Compila i campi sottostanti per poter effettuare una ricerca tra gli utenti registrati tramite nome, cognome e username. </p>
            </div>
            <img src="/FamaWeb/images/profilo.jpg" alt="nessuna immagine" />
        </div> <!-- end of middle -->
        
        <div id="templatemo_main">
        <div class="cbox_fw">
            	<div class="cbox_large float_l">
            		<div id="fama_form">
                		<form action="/FamaWeb/RicercaUtenteServlet" method="post">                     
                       		 <label for="ChiaveRicerca">Inserisci Chiave di Ricerca:</label> <input type="text" name="chiaveRicerca" class="input_field" />
                        	 <div class="cleaner h10"></div>
                        	 <input type="submit" value="Cerca Utente" id="submit" name="submit" class="submit_btn float_l" />                  
                		</form>        	
            		</div><br><br><br><br><br>
<%
if(request.getSession().getAttribute("risultatoRicerca") != null){
	UtenteRegistratoObj io = (UtenteRegistratoObj) request.getSession().getAttribute("UtenteRegistratoObj");
	ArrayList<UtenteRegistratoObj> listaUtenti = (ArrayList<UtenteRegistratoObj>) request.getSession().getAttribute("risultatoRicerca");
	out.println("<b>RISULTATO RICERCA</b>:<br><br>");
	for(UtenteRegistratoObj utente: listaUtenti){
		out.println("<b>"+utente.getUsername()+ "</b>" + " chiedi ");
		out.println("<a href=\"/FamaWeb/RichiestaAmiciziaServlet?from=" + io.getUsername() + "&to="+ utente.getUsername() + "\"" +
					"title=\"invia richiesta di amicizia\">" + "amicizia" + "</a>");
%>
					<div id="fama_form">
						<form name="ricercaUtente" method="post">
							<input type="hidden" name="from" value=<%=io.getUsername()%>>
							<input type="hidden" name="to" value=<%=utente.getUsername()%>>
							<label for="richiediAiuto">Richiedi Aiuto:</label><input type="text" name="descrizione" class="input_field"/>
							<div class="cleaner h10"></div>
							<input type="submit" value="Richiedi Aiuto" onClick="validaForm()" id="submit" name="submit" class="submit_btn float_l" />
						</form>	
					</div><br><br><br>
<%
		out.println("<br>");
	}
	request.getSession().removeAttribute("risultatoRicerca");
}
if(request.getSession().getAttribute("amico") != null){
	String amico = (String) request.getSession().getAttribute("amico");
	out.println("<b>" + amico + "</b>" + ": richiesta inviata");
	request.getSession().removeAttribute("amico");
}
if(request.getSession().getAttribute("aiuto") != null){
	String aiuto = (String) request.getSession().getAttribute("aiuto");
	out.println(aiuto + ": richiesta aiuto inviata");
	request.getSession().removeAttribute("aiuto");
}
%>
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