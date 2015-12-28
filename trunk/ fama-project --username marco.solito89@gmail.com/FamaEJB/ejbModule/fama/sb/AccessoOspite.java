package fama.sb;

import java.io.Serializable;
import java.util.ArrayList;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.jboss.ejb3.annotation.RemoteBinding;

import fama.eb.UtenteRegistrato;
import fama.obj.UtenteRegistratoObj;

/**
 * Session Bean implementation class AccessoOspite
 */
@Stateless
@RemoteBinding( jndiBinding = "AccessoOspiteRemote" )
public class AccessoOspite implements AccessoOspiteRemote {
	
	@PersistenceContext( unitName = "FamaDB" )
	private EntityManager manager;

    /**
     * Default constructor. 
     */
	
    public AccessoOspite() {
    }
    
    @Override
    public Serializable ricercaUtenti(String chiaveRicerca) {
    	Query q= manager.createQuery("from UtenteRegistrato where admin=false and (username=:cr or nome=:cr or cognome=:cr)");
    	q.setParameter("cr", chiaveRicerca);
    	ArrayList<UtenteRegistrato> listaUR = (ArrayList<UtenteRegistrato>) q.getResultList();
    	ArrayList<UtenteRegistratoObj> listaURO = new ArrayList<UtenteRegistratoObj>();
    	for (UtenteRegistrato ur: listaUR){
    		listaURO.add(new UtenteRegistratoObj(ur));
    	}
    	
    	return listaURO;
    }

}
