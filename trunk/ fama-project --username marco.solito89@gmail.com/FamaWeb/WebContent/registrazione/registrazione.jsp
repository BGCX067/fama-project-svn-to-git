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
	var username = document.registrazione.username.value;
	var password = document.registrazione.password.value;
	var ripetiPassword = document.registrazione.ripetiPassword.value;
	var nome= document.registrazione.nome.value;
	var cognome= document.registrazione.cognome.value;
	var email= document.registrazione.email.value;
	var tel= document.registrazione.tel.value;

	if(username==""){
		alert("Username NON inserito");
		return;
	}
	
	if(password==""){
		alert("Password NON inserita");
		return;
	}
	

	if(password!=ripetiPassword){
		alert("Convalida password non riuscita....\n\nInserire il valore corretto nel campo 'Ripeti password'");
		return;
	}
	
	if(nome==""){
		alert("Nome NON inserito");
		return;
	}
	
	if(!isAlfabetico(nome)){
		alert('Nome: ' + nome + ' NON corretto: inserire solo caratteri');
		return;}
	
	if(cognome==""){
		alert("Cognome NON inserito");
		return;
	}
	
	if(!isAlfabetico(cognome)){
		alert('Cognome: ' + cognome + ' NON corretto: inserire solo caratteri');
		return;}
	
	if(nome==cognome){
		alert("Nome e Cognome devono essere distinti");
		return;
	}
	
	if(email==""){
		alert("email NON inserita");
		return;
	}
	
	if(tel==""){
		alert("Numero di telefono NON inserito");
		return;
	}
	
	if(!isNumerico(tel)){
		alert('Telefono: ' + cognome + ' NON corretto: inserire solo numeri');
		return;}
	
	document.registrazione.action = "/FamaWeb/RegistrazioneServlet";
	document.registrazione.submit();
}

function isAlfabetico(input){

	for ( var i = 0; i < input.length; i++) {
			var cc = input.charAt(i).charCodeAt(0);

			if ((cc > 64 && cc < 91) || (cc > 96 && cc < 123)) {
			} else {
				return false;
			}
		}
	return true;
}

function isNumerico(input){
	for (var i = 0; i < input.length; i++) {
		var cc = input.charAt(i).charCodeAt(0);

		if (cc >= 48 && cc <= 57) {
		} else {
			return false;
		}
	}
return true;
	
}
</script>
</head>
<body>

<div id="templatemo_body_wrapper">
	<div id="templatemo_wrapper">
    	
        <div id="templatemo_header">
            <div id="site_title"><h1><a href="/FamaWeb/home/home.jsp">Swimv2</a></h1></div>
            <div id="templatemo_menu">
                <ul>
                  <li><a href="/FamaWeb/registrazione/registrazione.jsp" class="current">Registrati</a></li>
                  <li><a href="/FamaWeb/home/home.jsp" class="last">Home</a></li>
                </ul>
            </div> <!-- end of templatemo_menu -->
        </div><!-- end of header -->
        
        <div id="templatemo_middle">
            <div id="mid_left">
                <div id="mid_title">SWIMv2: Small World hypothesIs Machine v2</div>
                <p>Pagina di registrazione di Swim v2</p>
				<p>Compila i campi sottostanti correttamente e potrai iniziare a utilizzare i servizi di Swim v2</p>
            </div>
            <img src="/FamaWeb/images/templatemo_icon_01.png" alt="free for job" />
        </div> <!-- end of middle -->
        
        <div id="templatemo_main">
        <div class="cbox_fw">
            	<div class="cbox_large float_l">
                <div id="fama_form">
                
                <form name="registrazione" method="post">
                        
                        <label for="username">Username:</label> <input type="text" name="username" class="input_field" />
<% 
if(request.getSession().getAttribute("errore") != null){
	out.println(" <span id=\"error\" >" + request.getSession().getAttribute("errore") + "</span>");
	request.getSession().removeAttribute("errore");
}
%>
                        <div class="cleaner h10"></div>
                        <label for="password">Password:</label> <input type="password" name="password" class="input_field" />
                        <div class="cleaner h10"></div>
                        <label for="ripetiPassword">Ripeti password:</label> <input type="password" name="ripetiPassword" class="input_field" />
                        <div class="cleaner h10"></div>
                        <label for="nome">Nome:</label> <input type="text" name="nome" class="input_field" />
                        <div class="cleaner h10"></div>
                        <label for="cognome">Cognome:</label> <input type="text" name="cognome" class="input_field" />
                        <div class="cleaner h10"></div>
                        <label for="email">Email:</label> <input type="text" name="email" class="input_field" />
                        <div class="cleaner h10"></div>
                        <label for="tel">Tel:</label> <input type="text" name="tel" class="input_field" />
                        <div class="cleaner h10"></div>
                        <input type="button" value="Invia" onClick="validaForm()" class="submit_btn float_l" />           
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
</body>
</html>