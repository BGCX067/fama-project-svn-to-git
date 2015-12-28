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

<script type="text/javascript">
function validaForm(){
	var nomeAbilita = document.inserisciNuoveAbilitaAdmin.nomeAbilita.value;
	var settore = document.inserisciNuoveAbilitaAdmin.settore.value;
	var descrizione = document.inserisciNuoveAbilitaAdmin.descrizione.value;
	
	if(nomeAbilita==""){
		alert("Nome abilita NON inserito");
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
	
	document.inserisciNuoveAbilitaAdmin.action = "/FamaWeb/InserisciNuoveAbilitaAdminServlet";
	document.inserisciNuoveAbilitaAdmin.submit();
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

%>
<div id="templatemo_body_wrapper">
	<div id="templatemo_wrapper">
    	
        <div id="templatemo_header">
            <div id="site_title"><h1><a href="/FamaWeb/home/home.jsp">Swimv2</a></h1></div>
            <div id="templatemo_menu">
                <ul>
                  <li><a href="/FamaWeb/personale/inserisciNuoveAbilitaAdmin.jsp" class="current">Inserisci nuove abilita'</a></li>
                  <li><a href="/FamaWeb/personale/paginaPersonaleAdmin.jsp">Pagina personale admin</a></li>
                </ul>
            </div> <!-- end of templatemo_menu -->
        </div><!-- end of header -->
        
        <div id="templatemo_middle">
            <div id="mid_left">
                <div id="mid_title">SWIMv2: Small World hypothesIs Machine v2</div>
                <p>Una rete di amici generalmente puo' essere un buon modo per trovare supporto e aiuto da persone fidate.</p>
		<p>Con Swim v2 puoi creare la tua rete di conoscenze che ti permettera' di trovare la persona giusta per il lavoro giusto tramite un sistema di amicizie e richieste di aiuto.</p>
            </div>
            <img src="/FamaWeb/images/admin.gif" alt="free for job" />
        </div> <!-- end of middle -->
        
        <div id="templatemo_main">
        
        	<div class="cbox_fw">
                <div class="cbox_large float_l">
                	<h2>Inserisci nuove abilita':</h2>
                	<p>
                	</p>
                <div id="fama_form">
                
                <form name="inserisciNuoveAbilitaAdmin" method="post">
                        
                        <label for="nomeAbilita">Nome abilita': </label> <input type="text" name="nomeAbilita" class="input_field" /><br></br>
                        <label for="settore">Settore: </label> <input type="text" name="settore" class="input_field" /><br></br>
                        <label for="descrizione">Descrizione: </label> <input type="text" name="descrizione" class="input_field" />
                        <div class="cleaner h10"></div>
                        <input type="button" value="Conferma" onClick="validaForm()" class="submit_btn float_l" /> 
                                           
                </form>
                </div>
          <div class="cleaner h20"></div>
				</div>
				</div>
		</div> <!-- end of main -->	
    <div class="cbox_fw"><h5><b>
   <%
		if (request.getSession().getAttribute("AccessoBeanRemote") != null) {
			if(request.getSession().getAttribute("nomeAbilitaVuoto")!= null && request.getSession().getAttribute("nomeAbilitaVuoto").equals("nomeAbilitaVuoto")){
				response.sendRedirect("/FamaWeb/personale/inserisciNuoveAbilitaAdmin.jsp");
				request.getSession().removeAttribute("nomeAbilitaVuoto");
			}
			else{
			if (request.getSession().getAttribute("errore") != null && request.getSession().getAttribute("errore").equals("Abilita' già inserita in precedenza")) {
				out.println("<br><br> <span id=\"error\" >" + request.getSession().getAttribute("errore") + "</span>");
				out.println("<a href=\"/FamaWeb/personale/inserisciNuoveAbilitaAdmin.jsp\">Ok</a><br>");
				request.getSession().removeAttribute("errore");
			} else {
				AccessoBeanRemote abr = (AccessoBeanRemote) request.getSession().getAttribute("AccessoBeanRemote");
				ArrayList<AbilitaObj> listaAbilita = (ArrayList<AbilitaObj>) abr.listaAbilitaInSistema();
				out.println("ABILITA' GIA' PRESENTI: <br> <br>");
				for (AbilitaObj a : listaAbilita) {
					out.println("NOME: " + a.getNome() + "<br>SETTORE: " + a.getSettore() + "<br>DESCRIZIONE: " + a.getDescrizione() + "<br><br>");
				}
			}}
		} else {
			response.sendRedirect("/FamaWeb/access/loginAdmin.jsp");
		}
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