package com.tienda.springboot.rest.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.tienda.springboot.rest.entity.Client;
import com.tienda.springboot.rest.exception.ClientException;
import com.tienda.springboot.rest.repository.ClientRepository;

@RestController
public class ClientResource {

	@Autowired
	private ClientRepository clientRepository;

	@GetMapping("/clients")
	public List<Client> retrieveAllClients() {
		return clientRepository.findAll();
	}

	@GetMapping("/clients/{id}")
	public Client retrieveClient(@PathVariable long id) {
		Optional<Client> client = clientRepository.findById(id);

		if (!client.isPresent())
			throw new ClientException("id-" + id);

		return client.get();
	}

	@DeleteMapping("/clients/{id}")
	public void deleteClient(@PathVariable long id) {
		try {
			clientRepository.deleteById(id);
		} catch (Exception e) {
			throw new ClientException("Client with id-" + id+ "not found");
		}
	}

	@PostMapping("/clients")
	public ResponseEntity<Object> createClient(@RequestBody Client client) {

		try {

			Client clienteBD = clientRepository.findByDocument(client.getDocument());

			if (clienteBD != null) {
				return ResponseEntity
						.status(HttpStatus.FORBIDDEN)
						.body("Client already exist with this document!");
			}

			clienteBD = clientRepository.findByEmail(client.getEmail());

			if (clienteBD !=null) {
				return ResponseEntity
						.status(HttpStatus.FORBIDDEN)
						.body("Client already exist with this email!");
			}

			Client savedClient = clientRepository.save(client);

			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(savedClient.getId()).toUri();

			return ResponseEntity.created(location).build();

		} catch (Exception e) {
			return ResponseEntity
					.status(HttpStatus.FORBIDDEN)
					.body("General error creating client!");
		}

	}

	@PutMapping("/clients/{id}")
	public ResponseEntity<Object> updateClient(@RequestBody Client client, @PathVariable long id) {

		try {
			Optional<Client> clientOptional = clientRepository.findById(id);

			if (!clientOptional.isPresent())
				throw new ClientException("Resource not found!");

			client.setId(id);

			clientRepository.save(client);

			return ResponseEntity
					.status(HttpStatus.OK)
					.body("Client updated!");
		} catch (ClientException ce) {
			throw ce;
		} catch (Exception e) {
			return ResponseEntity
					.status(HttpStatus.FORBIDDEN)
					.body("General error updating client!");
		}
	}
	
}
