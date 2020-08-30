package com.tienda.springboot.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tienda.springboot.rest.entity.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{

	public Client findByDocument(String document);
	
	public Client findByEmail(String email);
	
	public Client validateLogin(String user, String password);
	
}
