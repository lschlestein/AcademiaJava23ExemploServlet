package com.Model;

public class Pessoa {
	private long id;
	private String nome;
	private String password;
	private String email;
	public Pessoa(long id,String nome, String password, String email) {
		this.id = id;
		this.nome = nome;
		this.password = password;
		this.email = email;
	}
	public Pessoa(String nome, String password, String email) {
		this.nome = nome;
		this.password = password;
		this.email = email;
	}
	public Pessoa() {
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "Pessoa [id=" + id + ", nome=" + nome + ", password=" + password + ", email=" + email + "]";
	}
	
}
