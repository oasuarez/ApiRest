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

import com.tienda.springboot.rest.entity.Product;
import com.tienda.springboot.rest.exception.ProductException;
import com.tienda.springboot.rest.repository.ProductRepository;

@RestController
public class ProductResource {

	@Autowired
	private ProductRepository productRepository;

	@GetMapping("/products")
	public List<Product> retrieveAllProducts() {
		return productRepository.findAll();
	}

	@GetMapping("/products/{id}")
	public Product retrieveProduct(@PathVariable long id) {
		Optional<Product> product = productRepository.findById(id);

		if (!product.isPresent())
			throw new ProductException("id-" + id);

		return product.get();
	}

	@DeleteMapping("/products/{id}")
	public void deleteProduct(@PathVariable long id) {
		productRepository.deleteById(id);
	}

	@PostMapping("/products")
	public ResponseEntity<Object> createProduct(@RequestBody Product product) {
		try {

			Product savedProduct = productRepository.save(product);

			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(savedProduct.getId()).toUri();

			return ResponseEntity.created(location).build();
		} catch (Exception e) {
			return ResponseEntity
					.status(HttpStatus.FORBIDDEN)
					.body("General error creating Product!");
		}

	}

	@PutMapping("/products/{id}")
	public ResponseEntity<Object> updateProduct(@RequestBody Product product, @PathVariable long id) {
		try {
			Optional<Product> productOptional = productRepository.findById(id);

			if (!productOptional.isPresent())
				throw new ProductException("Resource not found!");

			product.setId(id);

			productRepository.save(product);

			return ResponseEntity
					.status(HttpStatus.OK)
					.body("Product updated!");
		} catch (ProductException ce) {
			throw ce;
		} catch (Exception e) {
			return ResponseEntity
					.status(HttpStatus.FORBIDDEN)
					.body("General error updating Product!");
		}
	}
}
