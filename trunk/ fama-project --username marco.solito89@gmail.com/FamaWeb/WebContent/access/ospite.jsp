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

</head>
<body>

<div id="templatemo_body_wrapper">
	<div id="templatemo_wrapper">
    	
        <div id="templatemo_header">
            <div id="site_title"><h1><a href="/FamaWeb/home/home.jsp">Swimv2</a></h1></div>
            <div id="templatemo_menu">
                <ul>
                  <li><a href="/FamaWeb/access/ospite.jsp" class="current">Ospite</a></li>
                  <li><a href="/FamaWeb/home/home.jsp" >Home</a></li>
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
                	<h2>Trova utenti registrati</h2>
                	<p>Grazie alla possibilita' di entrare come ospite, puoi cercare persone registrate in Swim.
                	   Ma se vuoi un farci amicizia o chiedergli un aiuto devi registrarti.
                	</p>
                <div id="fama_form">
                
                <form action="/FamaWeb/RicercaUtenteDaOspiteServlet" method="post">
                        
                        <label for="chiaveRicercaUtenti">Username/Nome/Cognome utente:</label> <input type="text" name="chiaveRicercaUtenti" class="input_field" />
                        
                        <div class="cleaner h10"></div>
                        <input type="submit" value="Cerca" id="submit" name="submit" class="submit_btn float_l" />                  
                </form>
                </div>
          <div class="cleaner h20"></div>
				</div>
				</div>
		</div> <!-- end of main -->	
    <div class="cbox_fw"><h5><b>
   <%
	if (request.getSession().getAttribute("errore") != null )
	{
		out.println(request.getSession().getAttribute("errore"));
		request.getSession().removeAttribute("errore");
		
	}
   
	if(request.getSession().getAttribute("listaUtentiTrovati") != null ){
		ArrayList<UtenteRegistratoObj> listaUtentiTrovati = (ArrayList<UtenteRegistratoObj>) request.getSession().getAttribute("listaUtentiTrovati");
		for(UtenteRegistratoObj u:listaUtentiTrovati){
			out.println(u.getNome() + " " + u.getCognome() + " (" + u.getUsername() +")" + "<br><br>");
		}
		request.getSession().removeAttribute("listaUtentiTrovati");
	}
	%>
	</b>
	</h5>
	</div>
                    	
        
    </div>
</div>

<div id="templatemo_footer_wrapper">
    <div id="templatemo_footer">
        Designed by FAMA PROJECT
        <div class="cleaner"></div>
    </div>
</div>

</body>
</html>