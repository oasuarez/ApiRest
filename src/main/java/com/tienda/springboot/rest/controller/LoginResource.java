package com.tienda.springboot.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tienda.springboot.rest.entity.Client;
import com.tienda.springboot.rest.exception.ClientException;
import com.tienda.springboot.rest.repository.ClientRepository;

@RestController
public class LoginResource {
	
	@Autowired
	private ClientRepository clientRepository;

	@PostMapping("/Login")
	public Client validateLoginClient(@RequestBody Client client) {

		try {

			Client clientBD = clientRepository.validateLogin(client.getUser(), client.getPassword());

			if (clientBD ==null) {
				throw new ClientException("Incorrect user or password, please try again!");
			}

			return clientBD;

		} catch (Exception e) {
			e.printStackTrace();
			throw new ClientException("Error validating Login, please try again!");
		}

	}

}
