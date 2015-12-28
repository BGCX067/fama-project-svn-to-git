package fama.sb;
import java.io.Serializable;

import javax.ejb.Remote;

@Remote
public interface AccessoBeanRemote {
	public Serializable login(String username, String password) throws FamaBeanEccezione;
	public Serializable loginAdmin(String username, String password) throws FamaBeanEccezione;
	public void logout();
	public Serializable ricercaUtente(String chiaveRicerca);
	public boolean richiediAmicizia(String to);
	public boolean confermaRichiestaAmicizia(String to);
	public boolean rifiutaRichiestaAmicizia(String to);
	public boolean richiediAiuto(String to,String descrizione);
	public boolean confermaRichiestaAiuto(String to,long id,String risposta);
	public boolean rifiutaRichiestaAiuto(String to,long id);
	public Serializable listaAmici();
	public Serializable listaAiuti();
	public boolean rilasciaFeedback(String to,long idAiuto,String valutazione,String commento);
	public Serializable getFeedback(long idAiuto);
	public Serializable databaseUtentiAdmin();
	public boolean richiediNuovaAbilita(String nome,String settore,String descrizione) throws FamaBeanEccezione;
	public Serializable listaAbilitaDichiarate();
	public Serializable nuoveAbilitaAdmin();
	public boolean accettaAbilitaAdmin(String nomeAbilita);
	public boolean rifiutaAbilitaAdmin(String nomeAbilita);
	public Serializable listaAbilitaInSistema();
	public void inserisciNuoveAbilitaAdmin(String nome, String settore, String descrizione) throws FamaBeanEccezione;
	public boolean dichiaraAbilita(String nomeAbilita) throws FamaBeanEccezione;
}
