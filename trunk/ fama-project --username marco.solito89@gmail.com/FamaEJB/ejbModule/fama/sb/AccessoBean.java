package fama.sb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.jboss.ejb3.annotation.RemoteBinding;

import fama.eb.Abilita;
import fama.eb.DichiarazioneAbilita;
import fama.eb.Feedback;
import fama.eb.RichiestaAiuto;
import fama.eb.RichiestaAmicizia;
import fama.eb.UtenteRegistrato;
import fama.obj.AbilitaObj;
import fama.obj.FeedbackObj;
import fama.obj.RichiestaAiutoObj;
import fama.obj.RichiestaAmiciziaObj;
import fama.obj.UtenteRegistratoObj;

/**
 * Session Bean implementation class AccessoBean
 */
@Stateful
@RemoteBinding( jndiBinding = "AccessoBeanRemote" )
public class AccessoBean implements AccessoBeanRemote {

	@PersistenceContext( unitName = "FamaDB" )
	private EntityManager manager;
	
	private UtenteRegistrato utenteRegistrato;
	
    public AccessoBean() {
    	utenteRegistrato = null;
    }

	@Override
	public Serializable login(String username, String password) throws FamaBeanEccezione {
		Query q = manager.createQuery("FROM UtenteRegistrato ur WHERE ur.username=:us and ur.admin=false");
		q.setParameter("us", username);
		UtenteRegistrato nur = null;
		try {
			nur = (UtenteRegistrato) q.getSingleResult();
		} catch (Exception e) {
			throw new FamaBeanEccezione("username errato");
		}
		if(nur == null){
			throw new FamaBeanEccezione("username errato");
		}
		if (nur.getPassword().equals(password)) {
			utenteRegistrato = nur;
			UtenteRegistratoObj uro = new UtenteRegistratoObj(utenteRegistrato);
			return uro;
		}
		throw new FamaBeanEccezione("password errata");
	}
	
	@Override
	public Serializable loginAdmin(String username, String password) throws FamaBeanEccezione {
		Query q = manager.createQuery("FROM UtenteRegistrato ur WHERE ur.username=:us and ur.admin=true");
		q.setParameter("us", username);
		UtenteRegistrato nur = null;
		try {
			nur = (UtenteRegistrato) q.getSingleResult();
		} catch (Exception e) {
			throw new FamaBeanEccezione("username errato");
		}
		if(nur == null){
			throw new FamaBeanEccezione("username errato");
		}
		if (nur.getPassword().equals(password)) {
			utenteRegistrato = nur;
			UtenteRegistratoObj uro = new UtenteRegistratoObj(utenteRegistrato);
			return uro;
		}
		throw new FamaBeanEccezione("password errata");
	}

	@Override
	public void logout() {
		utenteRegistrato = null;
		return;
	}
	
	@Override
	public Serializable ricercaUtente(String chiaveRicerca) {
		/*
		 * 1 - ricerca nella tabella UtenteRegistrato con chiave = username
		 * 2 - ricerca nella tabella UtenteRegistrato con chiave = nome
		 * 3 - ricerca nella tabella UtenteRegistrato con chiave = cognome
		 * 4 - unire e mostrare in ordine tutti i risultati trovati eliminando le copie uguali
		 * 5 - ritornare una lista di oggetti UtenteRegistratoObj
		 */
		Query queryUsername = manager.createQuery("FROM UtenteRegistrato WHERE username=:chiave and username!=:mioUsername and admin=false");
		queryUsername.setParameter("chiave", chiaveRicerca);
		queryUsername.setParameter("mioUsername", utenteRegistrato.getUsername());
		Query queryNome = manager.createQuery("FROM UtenteRegistrato WHERE nome=:chiave and username!=:mioUsername and admin=false");
		queryNome.setParameter("chiave", chiaveRicerca);
		queryNome.setParameter("mioUsername", utenteRegistrato.getUsername());
		Query queryCognome = manager.createQuery("FROM UtenteRegistrato WHERE cognome=:chiave and username!=:mioUsername and admin=false");
		queryCognome.setParameter("chiave", chiaveRicerca);
		queryCognome.setParameter("mioUsername", utenteRegistrato.getUsername());
		
		List<UtenteRegistrato> utentiUsername = (List<UtenteRegistrato>) queryUsername.getResultList();
		List<UtenteRegistrato> utentiNome = (List<UtenteRegistrato>) queryNome.getResultList();
		List<UtenteRegistrato> utentiCognome = (List<UtenteRegistrato>) queryCognome.getResultList();
		
		ArrayList<UtenteRegistratoObj> totale = new ArrayList<UtenteRegistratoObj>();
		for(UtenteRegistrato utente: utentiUsername ){
			Boolean trovato = false;
			for(UtenteRegistratoObj utenteObj: totale){
				if(utenteObj.getUsername().equals(utente.getUsername())){
					trovato = true;
				}
			}
			if(trovato == false){
				totale.add(new UtenteRegistratoObj(utente));
			}
		}
		for(UtenteRegistrato utente: utentiNome ){
			Boolean trovato = false;
			for(UtenteRegistratoObj utenteObj: totale){
				if(utenteObj.getUsername().equals(utente.getUsername())){
					trovato = true;
				}
			}
			if(trovato == false){
				totale.add(new UtenteRegistratoObj(utente));
			}
		}
		for(UtenteRegistrato utente: utentiCognome ){
			Boolean trovato = false;
			for(UtenteRegistratoObj utenteObj: totale){
				if(utenteObj.getUsername().equals(utente.getUsername())){
					trovato = true;
				}
			}
			if(trovato == false){
				totale.add(new UtenteRegistratoObj(utente));
			}
		}
		
		return (Serializable) totale;
	}

	@Override
	public boolean richiediAmicizia(String to) {
		if(utenteRegistrato.getUsername().equals(to)){
			return false;
		}
		Query query = manager.createQuery("FROM RichiestaAmicizia WHERE utente1=:me1 OR utente2=:me2");
		query.setParameter("me1", utenteRegistrato.getUsername());
		query.setParameter("me2", utenteRegistrato.getUsername());
		ArrayList<RichiestaAmicizia> richieste = (ArrayList<RichiestaAmicizia>) query.getResultList();
		for(RichiestaAmicizia dest: richieste){
			if(dest.getUtente2().equals(to)){
				return false;
			}
			if(dest.getUtente2().equals(utenteRegistrato.getUsername()) && dest.getUtente1().equals(to)){
				if(!dest.getAccettata()){
					dest.setAccettata(true);
					manager.merge(dest);
				}
				return false;
			}
		}
		RichiestaAmicizia nuova = new RichiestaAmicizia();
		nuova.setUtente1(utenteRegistrato.getUsername());
		nuova.setUtente2(to);
		nuova.setAccettata(false);
		manager.persist(nuova);
		return true;
	}
	
	@Override
	public boolean confermaRichiestaAmicizia(String to) {
		try{
			Query qRichiesta = manager.createQuery("FROM RichiestaAmicizia WHERE utente1=:ut1 AND utente2=:ut2 AND accettata=false");
			qRichiesta.setParameter("ut1", to);
			qRichiesta.setParameter("ut2", utenteRegistrato.getUsername());
			RichiestaAmicizia ra = (RichiestaAmicizia) qRichiesta.getSingleResult();
			ra.setAccettata(true);
			manager.merge(ra);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	@Override
	public boolean rifiutaRichiestaAmicizia(String to) {
		try{
			Query qRichiesta = manager.createQuery("DELETE FROM RichiestaAmicizia WHERE utente1=:ut1 AND utente2=:ut2 AND accettata=false");
			qRichiesta.setParameter("ut1", to);
			qRichiesta.setParameter("ut2", utenteRegistrato.getUsername());
			qRichiesta.executeUpdate();
			return true;
		} catch (Exception e) {
			return false;
		}		
	}
	
	@Override
	public boolean richiediAiuto(String to, String descrizione) {
		if(utenteRegistrato.getUsername().equals(to)){
			return false;
		}
		RichiestaAiuto nuova = new RichiestaAiuto();
		nuova.setUtente1(utenteRegistrato.getUsername());
		nuova.setUtente2(to);
		nuova.setAccettata(false);
		nuova.setFeedbackRicevuto(false);
		nuova.setDescrizione(descrizione);
		manager.persist(nuova);
		return true;
	}
	
	@Override
	public boolean confermaRichiestaAiuto(String to,long id,String risposta) {
		try{
			Query qRichiesta = manager.createQuery("FROM RichiestaAiuto WHERE utente1=:ut1 AND utente2=:ut2 AND accettata=false AND id=:idAiuto");
			qRichiesta.setParameter("ut1", to);
			qRichiesta.setParameter("idAiuto", id);
			qRichiesta.setParameter("ut2", utenteRegistrato.getUsername());
			RichiestaAiuto ra = (RichiestaAiuto) qRichiesta.getSingleResult();
			ra.setAccettata(true);
			ra.setRispostaAiuto(risposta);
			manager.merge(ra);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	@Override
	public boolean rifiutaRichiestaAiuto(String to, long id) {
		try{
			Query qRichiesta = manager.createQuery("DELETE FROM RichiestaAiuto WHERE utente1=:ut1 AND utente2=:ut2 AND accettata=false AND id=:idAiuto");
			qRichiesta.setParameter("ut1", to);
			qRichiesta.setParameter("idAiuto", id);
			qRichiesta.setParameter("ut2", utenteRegistrato.getUsername());
			qRichiesta.executeUpdate();
			return true;
		} catch (Exception e) {
			return false;
		}		
	}
	
	@Override
	public Serializable listaAmici() {
		try{
			Query qAmici = manager.createQuery("FROM RichiestaAmicizia WHERE utente1=:ut1 OR utente2=:ut2");
			qAmici.setParameter("ut1", utenteRegistrato.getUsername());
			qAmici.setParameter("ut2", utenteRegistrato.getUsername());
			ArrayList<RichiestaAmicizia> ra = (ArrayList<RichiestaAmicizia>) qAmici.getResultList();
			ArrayList<RichiestaAmiciziaObj> rao = new ArrayList<RichiestaAmiciziaObj>();
			for (RichiestaAmicizia raTmp : ra) {
				rao.add(new RichiestaAmiciziaObj(raTmp));
			}
			if(rao.isEmpty()){
				return null;
			} else {
				return rao;
			}
		} catch (Exception e) {
			return null;
		}
	}
	
	@Override
	public Serializable listaAiuti() {
		try{
			Query qAmici = manager.createQuery("FROM RichiestaAiuto WHERE utente1=:ut1 OR utente2=:ut2");
			qAmici.setParameter("ut1", utenteRegistrato.getUsername());
			qAmici.setParameter("ut2", utenteRegistrato.getUsername());
			ArrayList<RichiestaAiuto> ra = (ArrayList<RichiestaAiuto>) qAmici.getResultList();
			ArrayList<RichiestaAiutoObj> rao = new ArrayList<RichiestaAiutoObj>();
			for (RichiestaAiuto raTmp : ra) {
				rao.add(new RichiestaAiutoObj(raTmp));
			}
			if(rao.isEmpty()){
				return null;
			} else {
				return rao;
			}
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public boolean rilasciaFeedback(String to, long idAiuto, String valutazione,
			String commento) {
		Query noFeedback = manager.createQuery("from Feedback where idAiuto=:ida");
		noFeedback.setParameter("ida", idAiuto);
		ArrayList<Feedback> listaFeed = (ArrayList<Feedback>) noFeedback.getResultList();
		if(!listaFeed.isEmpty()){
			return false;
		}
		try{
			Feedback fb = new Feedback();
			fb.setUtente1(utenteRegistrato.getUsername());
			fb.setUtente2(to);
			fb.setIdAiuto(idAiuto);
			fb.setValutazione(valutazione);
			fb.setCommento(commento);
			manager.persist(fb);
			
			Query qAiuto = manager.createQuery("from RichiestaAiuto where id=:ida");
			qAiuto.setParameter("ida", idAiuto);
			RichiestaAiuto ra = (RichiestaAiuto) qAiuto.getSingleResult();
			ra.setFeedbackRicevuto(true);
			manager.merge(ra);
			
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	@Override
	public Serializable getFeedback(long idAiuto) {
		try{
			Query feedback = manager.createQuery("from Feedback where idAiuto=:ida");
			feedback.setParameter("ida", idAiuto);
			Feedback fb = (Feedback) feedback.getSingleResult();
			return new FeedbackObj(fb);
		} catch (Exception e) {
			return null;
		}
	}
	
	@Override
	public Serializable databaseUtentiAdmin() {
	
		Query q = manager.createQuery("FROM UtenteRegistrato");
		
		ArrayList<UtenteRegistrato> databaseUtenti = (ArrayList<UtenteRegistrato>) q.getResultList();
		ArrayList<UtenteRegistratoObj> databaseUtentiObj = new ArrayList<UtenteRegistratoObj>();
		for(UtenteRegistrato u:databaseUtenti)
		{
			databaseUtentiObj.add(new UtenteRegistratoObj(u));
		}
		return (Serializable) databaseUtentiObj;
	}
	
	@Override
	public Serializable nuoveAbilitaAdmin() {
		Query q = manager.createQuery("FROM Abilita where accettata=false");
		ArrayList<Abilita> abilitaDaAccettare = (ArrayList<Abilita>) q.getResultList();
		
		if(abilitaDaAccettare.size()==0) 
			return null;
		ArrayList<AbilitaObj> abilitaDaAccettareObj = new ArrayList<AbilitaObj>();
		
		for(Abilita a:abilitaDaAccettare){
			abilitaDaAccettareObj.add(new AbilitaObj(a));
		}
		
		return (Serializable) abilitaDaAccettareObj;
	}

	@Override
	public boolean accettaAbilitaAdmin(String nomeAbilita) {
		try{
		Query q = manager.createQuery("FROM Abilita where nome=:nomeAbilita");
		q.setParameter("nomeAbilita", nomeAbilita);
		Abilita a = (Abilita) q.getSingleResult();
		a.setAccettata(true);
		manager.merge(a);
		return true;
		}catch (Exception e) {
			return false;
		}
		
	}

	@Override
	public boolean rifiutaAbilitaAdmin(String nomeAbilita) {
		try{
			Query q = manager.createQuery("delete FROM Abilita where nome=:nomeAbilita");
			q.setParameter("nomeAbilita", nomeAbilita);
			q.executeUpdate();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	@Override
	public boolean richiediNuovaAbilita(String nome, String settore,String descrizione) throws FamaBeanEccezione {
		Abilita giaPresente = null;
		try{
			Query q = manager.createQuery("FROM Abilita where nome=:nomeAbilita");
			q.setParameter("nomeAbilita", nome);
			giaPresente = (Abilita) q.getSingleResult();
			if(giaPresente != null){
				throw new FamaBeanEccezione("Esiste gia' una abilita' con questo nome, cambiare nome");
			}
			return false;
		} catch (Exception e) {
			if(giaPresente != null){
				throw e;
			}
			Abilita ab = new Abilita();
			ab.setNome(nome);
			ab.setSettore(settore);
			ab.setDescrizione(descrizione);
			ab.setRichiedente(utenteRegistrato.getUsername());
			ab.setAccettata(false);
			manager.persist(ab);
			return true;
		}
	}
	
	@Override
	public Serializable listaAbilitaDichiarate() {
		Query qDa = manager.createQuery("from DichiarazioneAbilita where utente=:ut");
		qDa.setParameter("ut", utenteRegistrato.getUsername());
		ArrayList<DichiarazioneAbilita> aDa = (ArrayList<DichiarazioneAbilita>) qDa.getResultList();
		
		Query qA = manager.createQuery("from Abilita");
		ArrayList<Abilita> aA = (ArrayList<Abilita>) qA.getResultList();
		ArrayList<Abilita> nAa = new ArrayList<Abilita>();
		Iterator<Abilita> it;
		for(DichiarazioneAbilita dA: aDa){
			it = aA.iterator();
			for(;it.hasNext();){
				Abilita a = it.next();
				if(dA.getNomeAbilita().equals(a.getNome())){
					nAa.add(a);
					break;
				}
			}
		}
		if(nAa.isEmpty()){
			return null;
		}
		ArrayList<AbilitaObj> aAO = new ArrayList<AbilitaObj>();
		for (Abilita abilita : nAa) {
			aAO.add(new AbilitaObj(abilita));
		}
		return aAO;
	}

	@Override
	public Serializable listaAbilitaInSistema() {
		Query q= manager.createQuery("FROM Abilita where accettata=true");
		ArrayList<Abilita> listaAb = (ArrayList<Abilita>) q.getResultList();
		ArrayList<AbilitaObj> listaAbObj = new ArrayList<AbilitaObj>();
		for(Abilita a: listaAb){
			listaAbObj.add(new AbilitaObj(a));
		}
		return (Serializable) listaAbObj;
		
	}

	@Override
	public void inserisciNuoveAbilitaAdmin(String nome, String settore, String descrizione) throws FamaBeanEccezione {

		Abilita abGiaPresente = null;
		try {
			Query q = manager.createQuery("FROM Abilita WHERE nome=:nomeAb");
			q.setParameter("nomeAb", nome);
			abGiaPresente = (Abilita) q.getSingleResult();
			if (abGiaPresente != null) {
				throw new FamaBeanEccezione("Abilita' già inserita in precedenza");
			}
		} catch (Exception e) {
			if (abGiaPresente != null) {
				throw new FamaBeanEccezione("Abilita' già inserita in precedenza");
			}
			Abilita a = new Abilita();
			a.setNome(nome);
			a.setSettore(settore);
			a.setDescrizione(descrizione);
			a.setAccettata(true);
			a.setRichiedente(utenteRegistrato.getUsername());
			manager.persist(a);

		}
	}

	@Override
	public boolean dichiaraAbilita(String nomeAbilita) throws FamaBeanEccezione {
		DichiarazioneAbilita abilitaPresente = null;
		try{
			Query q = manager.createQuery("from DichiarazioneAbilita where nomeAbilita=:na and utente=:ut");
			q.setParameter("na", nomeAbilita);
			q.setParameter("ut", utenteRegistrato.getUsername());
			abilitaPresente = (DichiarazioneAbilita) q.getSingleResult();
			if(abilitaPresente != null){
				throw new FamaBeanEccezione("Abilita già dichiarata");
			}
			return false;
		} catch (Exception e) {
			if(abilitaPresente != null){
				throw new FamaBeanEccezione("Abilita già dichiarata");
			}
			DichiarazioneAbilita da = new DichiarazioneAbilita();
			da.setNomeAbilita(nomeAbilita);
			da.setUtente(utenteRegistrato.getUsername());
			manager.persist(da);
			return true;
		}
	}
	
	

}
