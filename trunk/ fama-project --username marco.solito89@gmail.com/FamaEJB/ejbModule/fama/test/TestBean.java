package fama.test;

import java.io.Serializable;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.jboss.ejb3.annotation.RemoteBinding;

import fama.eb.UtenteRegistrato;
import fama.obj.UtenteRegistratoObj;

/**
 * Session Bean implementation class TestBean
 */
@Stateless
@RemoteBinding( jndiBinding = "TestBeanRemote" )
public class TestBean implements TestBeanRemote {
	@PersistenceContext( unitName = "FamaDB" )
	private EntityManager manager;

    /**
     * Default constructor. 
     */
    public TestBean() {
        // TODO Auto-generated constructor stub
    }
    
	public void eliminaUtente(UtenteRegistratoObj	nuovoUtente) {
		Query q = manager.createQuery("delete from UtenteRegistrato where username=:usname");
		q.setParameter("usname", nuovoUtente.getUsername());
		q.executeUpdate();
	}
	
	@Override
	public Serializable esisteUtente(String username) {
		try {
			Query q = manager.createQuery("from UtenteRegistrato where username=:usname");
			q.setParameter("usname", username);
			UtenteRegistrato ur = (UtenteRegistrato) q.getSingleResult();
			if(ur == null){
				return null;
			} else {
				return new UtenteRegistratoObj(ur);
			}
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public void cleanEntities() {
		Query q;
		q= manager.createNativeQuery("TRUNCATE TABLE UtenteRegistrato");
		q.executeUpdate();
		q = manager.createNativeQuery("TRUNCATE TABLE Abilita");
		q.executeUpdate();
		q = manager.createNativeQuery("TRUNCATE TABLE DichiarazioneAbilita");
		q.executeUpdate();
		q = manager.createNativeQuery("TRUNCATE TABLE Feedback");
		q.executeUpdate();
		q = manager.createNativeQuery("TRUNCATE TABLE OspitiConnessi");
		q.executeUpdate();
		q = manager.createNativeQuery("TRUNCATE TABLE RichiestaAiuto");
		q.executeUpdate();
		q = manager.createNativeQuery("TRUNCATE TABLE RichiestaAmicizia");
		q.executeUpdate();
		
		//aggiungo l'admin: username="admin" e password="0000" e admin=true
		UtenteRegistrato admin = new UtenteRegistrato();
		admin.setUsername("admin");
		admin.setPassword("0000");
		admin.setNome("Mario");
		admin.setCognome("Rossi");
		admin.setEmail("emailProva");
		admin.setTel("0000000000");
		admin.setAdmin(true);
		manager.persist(admin);
	}
	
	public Serializable creaAdminProva(){
		Query q= manager.createQuery("from UtenteRegistrato where username=:u and password=:p and admin=true");
		q.setParameter("u", "admin");
		q.setParameter("p", "0000");
		return new UtenteRegistratoObj((UtenteRegistrato) q.getSingleResult());

	}
}