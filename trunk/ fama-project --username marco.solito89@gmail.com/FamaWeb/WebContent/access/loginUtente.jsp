<%@page import="java.io.PrintWriter"%>
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
if(request.getSession().getAttribute("login") != null){
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
	} else {
		response.sendRedirect("/FamaWeb/personale/paginaPersonale.jsp");
	}
} else {
%>
<div id="templatemo_body_wrapper">
	<div id="templatemo_wrapper">
    	
        <div id="templatemo_header">
            <div id="site_title"><h1><a href="/FamaWeb/home/home.jsp">Swimv2</a></h1></div>
            <div id="templatemo_menu">
                <ul>
                  <li><a href="/FamaWeb/home/home.jsp">Home</a></li>
                  <li><a href="/FamaWeb/registrazione/registrazione.jsp" >Registrati</a></li>
                  <li><a href="/FamaWeb/access/loginUtente.jsp" class="current">Login</a></li>
                  <li><a href="/FamaWeb/access/ospite.jsp">Ospite</a></li>
                  <li><a href="/FamaWeb/access/loginAdmin.jsp" class="last">Admin</a></li>
                </ul>
            </div> <!-- end of templatemo_menu -->
        </div><!-- end of header -->
        
        <div id="templatemo_middle">
            <div id="mid_left">
                <div id="mid_title">SWIMv2: Small World hypothesIs Machine v2</div>
                <p>Una rete di amici generalmente puo' essere un buon modo per trovare supporto e aiuto da persone fidate.</p>
		<p>Con Swim v2 puoi creare la tua rete di conoscenze che ti permettera' di trovare la persona giusta per il lavoro giusto tramite un sistema di amicizie e richieste di aiuto.</p>
            </div>
            <img src="/FamaWeb/images/templatemo_icon_01.png" alt="free for job" />
        </div> <!-- end of middle -->
        
        <div id="templatemo_main">
        <div class="cbox_fw">
            	<div class="cbox_large float_l">
                	<h2>Login</h2>
                	<p>Loggati nel sistema e potrai ottenere aiuto da altri utenti che posseggono le abilita' a cui sei interessato.
                	   Sarai inoltre in grado di dichiarare le abilita' per cui sei esperto e aiutare tutti quelli che a loro volta
                	   chideranno aiuto a te.
                	   Un sistema di Feedback ti permette di rilasciare una votazione e un commento sull'aiuto ricevuto in modo da
                	   far conoscere la tua opinione anche a chi ti ha aiutato, allo stesso modo riceverai dei Feedback da tutti quelli
                	   che aiuterai. Compila i campi ed entra in Swim.</p>
                <div id="fama_form">
                
                <form action="/FamaWeb/LoginUtenteServlet" method="post">
                        
                        <label for="username">Username:</label> <input type="text" name="username" class="input_field" />
<% 
if(request.getSession().getAttribute("errore") != null){
	if(request.getSession().getAttribute("errore").equals("username errato")){
		out.println(" <span id=\"error\" >" + request.getSession().getAttribute("errore") + "</span>");
		request.getSession().removeAttribute("errore");
	}
}
%>
                        <div class="cleaner h10"></div>
                        <label for="password">Password:</label> <input type="password" name="password" class="input_field" />
<%
if(request.getSession().getAttribute("errore") != null){
	if(request.getSession().getAttribute("errore").equals("password errata")){
		out.println(" <span id=\"error\" >" + request.getSession().getAttribute("errore") + "</span>");
		request.getSession().removeAttribute("errore");
	}
}
%>
                        <div class="cleaner h10"></div>
                        <input type="submit" value="Login" id="submit" name="submit" class="submit_btn float_l" />                  
                </form>
                </div>
                    
				<div class="cleaner h20"></div>
				</div>
				</div>
		</div> <!-- end of main -->
        
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