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

import com.tienda.springboot.rest.entity.Store;
import com.tienda.springboot.rest.exception.StoreException;
import com.tienda.springboot.rest.repository.StoreRepository;

@RestController
public class StoreResource {

	@Autowired
	private StoreRepository storeRepository;

	@GetMapping("/stores")
	public List<Store> retrieveAllStores() {
		try {
			List<Store> list = storeRepository.findAll();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw new StoreException("Error retrieving stores!");
		}
	}

	@GetMapping("/stores/{id}")
	public Store retrieveStore(@PathVariable long id) {
		Optional<Store> store = storeRepository.findById(id);

		if (!store.isPresent())
			throw new StoreException("id-" + id);

		return store.get();
	}

	@DeleteMapping("/stores/{id}")
	public void deleteStore(@PathVariable long id) {
		storeRepository.deleteById(id);
	}

	@PostMapping("/stores")
	public ResponseEntity<Object> createStore(@RequestBody Store store) {
		try {

			Store savedStore = storeRepository.save(store);

			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(savedStore.getId()).toUri();

			return ResponseEntity.created(location).build();

		} catch (Exception e) {
			return ResponseEntity
					.status(HttpStatus.FORBIDDEN)
					.body("General error creating Store!");
		}

	}

	@PutMapping("/stores/{id}")
	public ResponseEntity<Object> updateStore(@RequestBody Store store, @PathVariable long id) {

		try {

			Optional<Store> storeOptional = storeRepository.findById(id);

			if (!storeOptional.isPresent())
				throw new StoreException("Resource not found!");

			store.setId(id);

			storeRepository.save(store);

			return ResponseEntity
					.status(HttpStatus.OK)
					.body("Store updated!");
		} catch (StoreException ce) {
			throw ce;
		}catch (Exception e) {
			return ResponseEntity
					.status(HttpStatus.FORBIDDEN)
					.body("General error updating Store!");
		}
	}
}
