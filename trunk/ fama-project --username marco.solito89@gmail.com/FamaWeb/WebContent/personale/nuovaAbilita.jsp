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
	var nomeNuovaAbilita = document.nuovaAbilita.nomeNuovaAbilita.value;
	var settore = document.nuovaAbilita.settore.value;
	var descrizione = document.nuovaAbilita.descrizione.value;
	
	if(nomeNuovaAbilita==""){
		alert("Nome nuova abilita' NON inserito");
		return;
	}
	
	if(settore==""){
		alert("Settore NON inserito");
		return;
	}
	
	if(descrizione==""){
		alert("Descrizione NON inserita");
		return;
	}
	
	document.nuovaAbilita.action = "/FamaWeb/NuovaAbilitaServlet";
	document.nuovaAbilita.submit();
}
</script>
</head>
<body>
<%
if(request.getSession().getAttribute("login") == null){
	response.sendRedirect("/FamaWeb/home/home.jsp");
} else {
%>

<div id="templatemo_body_wrapper">
	<div id="templatemo_wrapper">
    	
        <div id="templatemo_header">
            <div id="site_title"><h1><a href="/FamaWeb/home/home.jsp">Swimv2</a></h1></div>
            <div id="templatemo_menu">
                <ul>
                  <li><a href="/FamaWeb/home/home.jsp">Home</a></li>
                  <li><a href="/FamaWeb/personale/nuovaAbilita.jsp" class="current">Nuova Abilita'</a></li>
                  <li><a href="/FamaWeb/personale/paginaPersonale.jsp" class="last">Pagina Personale</a></li>
                </ul>
            </div> <!-- end of templatemo_menu -->
        </div><!-- end of header -->
        
        <div id="templatemo_middle">
            <div id="mid_left">
                <div id="mid_title">SWIMv2: Small World hypothesIs Machine v2</div>
                <p>Pagina inserimento nuove abilita', richiedi all'amministratore di inserire una nuova abilita' nel sistema.</p>
				<p>Compila i campi sottostanti per chiedere l'inserimento di nuove abilita' nel sistema. </p>
            </div>
            <img src="/FamaWeb/images/profilo.jpg" alt="nessuna immagine" />
        </div> <!-- end of middle -->

		<div id="templatemo_main">
        <div class="cbox_fw">
            	<div class="cbox_large float_l">
            		<div id="fama_form">
                		<form name="nuovaAbilita" method="post">                   
                       		 <label for="nomeNuovaAbilita">Nome nuova abilita':</label> <input type="text" name="nomeNuovaAbilita" class="input_field" />
                        	 <div class="cleaner h10"></div>
                       		 <label for="settore">Settore:</label> <input type="text" name="settore" class="input_field" />
                        	 <div class="cleaner h10"></div>
                       		 <label for="descrizione">Descrizione:</label> <input type="text" name="descrizione" class="input_field" />
                        	 <div class="cleaner h10"></div>
                        	 <input type="button" value="Invia" onClick="validaForm()" class="submit_btn float_l" />              
                		</form>
                	</div>
					<br>
					<br>
					<%
						if (request.getSession().getAttribute("nuovaAbilita") != null) {
							out.println("nuova abilita richiesta");
							request.getSession().removeAttribute("nuovaAbilita");
						}
						if (request.getSession().getAttribute("errore") != null) {
							out.println("<br>"
									+ "<span id=\"error\">" + request.getSession().getAttribute("errore") + "</span>" + "<br>");
							request.getSession().removeAttribute("errore");
						}
					%>
                </div>
        </div>
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