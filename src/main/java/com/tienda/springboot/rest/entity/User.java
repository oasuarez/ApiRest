package com.tienda.springboot.rest.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class User {
	@Id
	@GeneratedValue
	private Long id;
	private String user;
	private String password;
	@OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    private List<Role> roles;
	private String description;

	public User() {
		super();
	}

	/*
	 * Constructor without ID
	 */
	public User(String user, String password, List<Role> roles, String description) {
		super();
		this.user = user;
		this.password = password;
		this.description = description;
		this.roles = roles;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
