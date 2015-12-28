package fama.sb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.ejb3.annotation.RemoteBinding;

import fama.eb.UtenteRegistrato;

/**
 * Session Bean implementation class RegistrazioneBean
 */
@Stateless
@RemoteBinding( jndiBinding = "RegistrazioneBeanRemote" )
public class RegistrazioneBean implements RegistrazioneBeanRemote {
	
	@PersistenceContext( unitName = "FamaDB" )
	private EntityManager manager;
	
    public RegistrazioneBean() {
    }

	@Override
	public Boolean registraUtente(String username, String password, String nome,
			String cognome, String email, String tel) {
		
		javax.persistence.Query q = manager.createQuery("FROM UtenteRegistrato ur WHERE ur.username =:us");
		q.setParameter("us", username);
		List<UtenteRegistrato> res = q.getResultList();
		
		if (res.size() == 0) {
			UtenteRegistrato ur = new UtenteRegistrato();
			ur.setUsername(username);
			ur.setPassword(password);
			ur.setNome(nome);
			ur.setCognome(cognome);
			ur.setEmail(email);
			ur.setTel(tel);
			ur.setAdmin(false);
			
			manager.persist(ur);
			return true;
		} else {
			return false;
		}
	}

}
