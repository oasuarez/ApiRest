package com.tienda.springboot.rest.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQueries({
	@NamedQuery(name = "Client.findByDocument", query = "SELECT c FROM Client c WHERE LOWER(c.document) = LOWER(?1)"),
	@NamedQuery(name = "Client.findByEmail", query = "SELECT c FROM Client c WHERE LOWER(c.email) = LOWER(?1)"),
	@NamedQuery(name = "Client.validateLogin", query = "SELECT c FROM Client c WHERE LOWER(c.user) = LOWER(?1) and c.password = ?2")
})
public class Client {
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private Long age;
	@Column(unique=true)
	private String document;
	@Column(unique=true)
	private String email;
	private String user;
	private String password;
	@OneToMany
	private List<Purchase> purchases = new ArrayList<>();

	public Client() {
		super();
	}

	public Client(Long id, String name, Long age, String document, String email, String user, String password) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.document = document;
		this.email = email;
		this.user = user;
		this.password = password;
	}

	/*
	 * Constructor without ID
	 */
	public Client(String name, Long age, String document, String email, String user, String password) {
		super();
		this.name = name;
		this.age = age;
		this.document = document;
		this.email = email;
		this.user = user;
		this.password = password;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Long getAge() {
		return age;
	}

	public void setAge(Long age) {
		this.age = age;
	}

	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Purchase> getPurchases() {
		return purchases;
	}

	public void setPurchases(List<Purchase> purchases) {
		this.purchases = purchases;
	}

}
