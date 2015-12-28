package fama.sb;
import javax.ejb.Remote;

@Remote
public interface RegistrazioneBeanRemote {
	
	public Boolean registraUtente(String username,String password,
			String nome,String cognome,
			String email,String tel);

}
