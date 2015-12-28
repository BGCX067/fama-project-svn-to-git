package fama.obj;

import java.io.Serializable;

import fama.eb.UtenteRegistrato;

public class UtenteRegistratoObj implements Serializable{

	private static final long serialVersionUID = 1L;

	private long id;
	private String username;
	private String password;
	private String nome;
	private String cognome;
	private String email;
	private String tel;
	private Boolean admin;
	
	public UtenteRegistratoObj(UtenteRegistrato ur) {
		id = ur.getId();
		username = ur.getUsername();
		password = ur.getPassword();
		nome = ur.getNome();
		cognome = ur.getCognome();
		email = ur.getEmail();
		tel = ur.getTel();
		admin = ur.getAdmin();
	}

	public long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getNome() {
		return nome;
	}

	public String getCognome() {
		return cognome;
	}

	public String getEmail() {
		return email;
	}

	public String getTel() {
		return tel;
	}

	public Boolean getAdmin() {
		return admin;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}
	
	
}
