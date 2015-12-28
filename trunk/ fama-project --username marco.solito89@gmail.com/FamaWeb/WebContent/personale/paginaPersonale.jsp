<%@page import="java.io.PrintWriter"%>
<%@page import="fama.obj.DichiarazioneAbilitaObj"%>
<%@page import="fama.obj.AbilitaObj"%>
<%@page import="fama.obj.FeedbackObj"%>
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
<script type="text/javascript">
function validaForm1(){
	var valutazione = document.rilasciaFeedback.valutazione.value;
	var commento= document.rilasciaFeedback.commento.value;

	if(valutazione.length > 2){
		alert("Il Feedback deve avere una valore compreso tra 0 e 10 inclusi");
		return;
	}
	
	if (valutazione.length == 1) {
			var c = valutazione.charAt(0).charCodeAt(0);
			if (c >= 48 && c <= 57) {
			} else {
				alert("Il Feedback deve avere una valore compreso tra 0 e 10 inclusi");
				return;
			}
		} else {

			if (valutazione.length == 2 && valutazione == "10") {
			} else {
				alert("Il Feedback deve avere una valore compreso tra 0 e 10 inclusi");
				return;
			}
		}

		if (commento == "") {
			alert("Inserire un commento");
			return;
		}

		document.rilasciaFeedback.action = "/FamaWeb/RichiestaAiutoServlet";
		document.rilasciaFeedback.submit();
	}
	
function validaForm2(){
	var rispostaAiuto = document.gestioneRichiestaAiuto.rispostaAiuto.value;
	
	if(rispostaAiuto==""){
		alert("Inserire una risposta all'aiuto");
		return;
	}
	
	document.gestioneRichiestaAiuto.action = "/FamaWeb/RichiestaAiutoServlet";
	document.gestioneRichiestaAiuto.submit();
	
}
</script>
</head>
<body>
	<%
		if (request.getSession().getAttribute("login") == null) {
			response.sendRedirect("/FamaWeb/home/home.jsp");
		} 
			UtenteRegistratoObj uroAdmin = (UtenteRegistratoObj) request.getSession().getAttribute("UtenteRegistratoObj");
			String username = uroAdmin.getUsername();
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

				AccessoBeanRemote abr = (AccessoBeanRemote) request.getSession().getAttribute("AccessoBeanRemote");
				UtenteRegistratoObj uro = (UtenteRegistratoObj) request.getSession().getAttribute("UtenteRegistratoObj");
	%>

<div id="templatemo_body_wrapper">
	<div id="templatemo_wrapper">
    	
        <div id="templatemo_header">
            <div id="site_title"><h1><a href="/FamaWeb/home/home.jsp">Swimv2</a></h1></div>
            <div id="templatemo_menu">
                <ul>
                  <li><a href="/FamaWeb/home/home.jsp">Home</a></li>
                  <li><a href="/FamaWeb/personale/paginaPersonale.jsp" class="current">Personale</a></li>
                  <li><a href="/FamaWeb/LogoutUtenteServlet" class="last">Logout</a></li>
                </ul>
            </div> <!-- end of templatemo_menu -->
        </div><!-- end of header -->
        
        <div id="templatemo_middle">
            <div id="mid_left">
                <div id="mid_title">
                <%
    			out.println(uro.getNome() + " " + uro.getCognome() + " (" + uro.getUsername() + ") ");
                %>
                </div>
                <p>Benvenuto nella tua pagina personale, da qui puoi usufruire di tutti i servizi messi a disposizione da Swim.</p>
				<p>Dalla tua pagina personale puoi consultare la tua lista amici, cercare utenti, dichiarare abilita' e molto altro. </p>
            </div>
            <img src="/FamaWeb/images/profilo.jpg" alt="nessuna immagine" />
        </div> <!-- end of middle -->
        
        <div id="templatemo_main">
        
        	<div class="cbox_fw">
                <!--<div class="cbox_3b fp_box boxPagPers" ><span class="b1"></span>-->
                <div class="cbox_3b boxPagPers" ><span class="b1"></span>
                    <h4>Lista Amici:</h4>
                    <p>
	<% 
	ArrayList<RichiestaAmiciziaObj> rao = (ArrayList<RichiestaAmiciziaObj>) abr.listaAmici();
			ArrayList<RichiestaAmiciziaObj> nuoveRichieste = new ArrayList<RichiestaAmiciziaObj>();
			if (rao == null) {
				out.println("Lista Amici Vuota<br>");
			} else {
				for (RichiestaAmiciziaObj raoTemp : rao) {
					if (raoTemp.getUtente1().equals(uro.getUsername())) {
						out.println("<b>"+raoTemp.getUtente2()+"</b>");
						if (!raoTemp.isAccettata()) {
							out.println(": richiesta inoltrata");
						} else {
							//out.println(": amico");
						}
					} else {
						if (!raoTemp.isAccettata()) {
							nuoveRichieste.add(raoTemp);
						} else {
							out.println("<b>"+raoTemp.getUtente1()+"</b>");
							//out.println(": amico");
						}
					}
					out.println("<br>");
				}
			}
	%>
                    </p>
                </div>
                <div class="cbox_3b boxPagPers"><span class="b2"></span>
                    <h4>Richieste di Amicizia:</h4>
                    <p>
	<%
		if (nuoveRichieste.isEmpty()) {
				out.println("nessuna nuova richiesta<br>");
			} else {
				for (RichiestaAmiciziaObj nuovaRaoTemp : nuoveRichieste) {
					out.println("<b>" + nuovaRaoTemp.getUtente1() + "</b>: ");
					out.println(" <a href=\"/FamaWeb/RichiestaAmiciziaServlet?to="
							+ nuovaRaoTemp.getUtente1()
							+ "&gestioneRichiestaAmicizia=conferma\">conferma</a>");
					out.println(" <a href=\"/FamaWeb/RichiestaAmiciziaServlet?to="
							+ nuovaRaoTemp.getUtente1()
							+ "&gestioneRichiestaAmicizia=rifiuta\">rifiuta</a>");
					out.println("<br>");
				}
			}
	%>
                    </p>
                </div>
                <div class="cbox_3b cbox_rm boxPagPers"><span class="b3"></span>
                    <h4>Lista Collaborazioni:</h4>
                    <p>
<%
		ArrayList<RichiestaAiutoObj> raiutoo = (ArrayList<RichiestaAiutoObj>) abr.listaAiuti();
			ArrayList<RichiestaAiutoObj> nuoveRichiesteAiuto = new ArrayList<RichiestaAiutoObj>();
			if (raiutoo == null) {
				out.println("nessun aiuto dato o ricevuto<br>");
			} else {
				for (RichiestaAiutoObj raiutooTemp : raiutoo) {
					if (raiutooTemp.getUtente1().equals(uro.getUsername())) {
						out.println("<b>"+raiutooTemp.getUtente2()+"</b>");
						if (!raiutooTemp.isAccettata()) {
							out.println(": richiesta aiuto inoltrata ma non ancora accettata");
						} else {
							out.println(": aiuto confermato. <br>DESCRIZIONE AIUTO: " + raiutooTemp.getDescrizione() + "<br>RISPOSTA: "
									+ raiutooTemp.getRispostaAiuto()
									+ "<br>");
							if (!raiutooTemp.isFeedbackRicevuto()) {
								out.println(" rilascia feedback per aiuto ricevuto: ");

								out.println(""
										+ "<form name=\"rilasciaFeedback\" method=\"get\">" 
										+ "<input type=\"hidden\" name=\"gestisciFeedback\" value=\"si\" />"
										+ "<input type=\"hidden\" name=\"to\" value="
										+ raiutooTemp.getUtente2()
										+ " />"
										+ "<input type=\"hidden\" name=\"idAiuto\" value="
										+ raiutooTemp.getId()
										+ " />"
										+ "Valutazione: <input type=\"text\" name=\"valutazione\" /> "
										+ "Commento: <input type=\"commento\" name=\"commento\" /> "
										+ "<input type=\"button\" value=\"Rilascia\" onClick=\"validaForm1()\"/>"
										+ "</form>");

							} else {
								out.println("<br>FEEDBACK RILASCIATO: ");
								FeedbackObj fbo = (FeedbackObj) abr
										.getFeedback(raiutooTemp.getId());
								out.println(fbo.getValutazione() + "<br>COMMENTO: "
										+ fbo.getCommento() + "<br>");
							}
						}
					} else {
						if (!raiutooTemp.isAccettata()) {
							nuoveRichiesteAiuto.add(raiutooTemp);
						} else {
							out.println("<b>"+raiutooTemp.getUtente1()+"</b>");
							if (!raiutooTemp.isFeedbackRicevuto()) {
								out.println(" devi ricevere un feedback da questo utente </br>");
							} else {
								out.println("<br>FEEDBACK RICEVUTO: ");
								FeedbackObj fbo = (FeedbackObj) abr
										.getFeedback(raiutooTemp.getId());
								out.println(fbo.getValutazione() + "<br>COMMENTO: "
										+ fbo.getCommento());
								out.println("<br>");
							}
						}
					}
					out.println("<br>");
				}
			}
	%>
                    </p>
                </div>
                <div class="cbox_3b boxPagPers"><span class="b2"></span>
                    <h4>Richieste Di Aiuto:</h4>
                    <p>
	<%
		if (nuoveRichiesteAiuto.isEmpty()) {
				out.println("nessuna nuova richiesta<br>");
			} else {
				for (RichiestaAiutoObj nuovaRaiutooTemp : nuoveRichiesteAiuto) {
					out.println("<b>"+nuovaRaiutooTemp.getUtente1()+"</b>");
					out.println(": ti ha richiesto aiuto: "
							+ nuovaRaiutooTemp.getDescrizione() + " ");
					
					out.println("<form name=\"gestioneRichiestaAiuto\" method=\"get\">" 						
							+ "<input type=\"hidden\" name=\"gestioneRichiestaAiuto\" value=\"conferma\" />"
							+ "<input type=\"hidden\" name=\"to\" value="
							+ nuovaRaiutooTemp.getUtente1()
							+ " />"
							+ "<input type=\"hidden\" name=\"idAiuto\" value="
							+ nuovaRaiutooTemp.getId()
							+ " />"
							+ "Risposta: <input type=\"text\" name=\"rispostaAiuto\" /> "
							+ "<input type=\"button\" value=\"Rispondi\" onClick=\"validaForm2()\"/>"
							+ "</form>");

					out.println(" <a href=\"/FamaWeb/RichiestaAiutoServlet?to="
							+ nuovaRaiutooTemp.getUtente1()
							+ "&idAiuto="
							+ nuovaRaiutooTemp.getId()
							+ "&gestioneRichiestaAiuto=rifiuta\">rifiuta</a>");
					out.println("<br>");
					out.println("<br>");
				}
			}
	%>
                    </p>
                </div>
                
                <div class="cbox_3b boxPagPers"><span class="b2"></span>
                    <h4>Le mie Abilita':</h4>
                    <p>
	<%
		ArrayList<AbilitaObj> aAOBJ = (ArrayList<AbilitaObj>) abr.listaAbilitaDichiarate();
			if (aAOBJ != null) {
				for(AbilitaObj aOBJ: aAOBJ){
	 				out.println("NOME: " + aOBJ.getNome() + "<br>" + 
								"SETTORE: " + aOBJ.getSettore() + "<br>" + 
	 							"DESCRIZIONE: " + aOBJ.getDescrizione() + "<br>"); 
	 				out.println("<br>");
				}
			}
	%>
                    </p>
                </div>
                
                <div class="cbox_3b cbox_rm boxPagPers"><span class="b2"></span>
                    <h4>Altre Funzioni:</h4>
                    <p>
                    	<a href="/FamaWeb/personale/ricercaUtente.jsp">Ricerca Utente (amicizia-aiuto)</a><br>
						<a href="/FamaWeb/personale/nuovaAbilita.jsp">Richiedi Nuova Abilita'</a><br>
						<a href="/FamaWeb/personale/dichiaraAbilita.jsp">Dichiara Abilita'</a><br>
                    </p>
                </div>
                
                <div class="cleaner"></div>
            </div>
            <div class="cbox_fw"></div>
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

