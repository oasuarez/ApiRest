package com.tienda.springboot.rest.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Product {
	@Id
	@GeneratedValue
	private Long id;
	private String barcode;
	private String description;
	private Long price;
	private String name;
	@JsonIgnore
	@ManyToMany(mappedBy = "products")
    private List<Store> stores = new ArrayList<>();
	
	public Product() {
		super();
	}
	
	public Product(Long id, String name, String description, Long price, String barcode) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.barcode = barcode;
	}

	/*
	 * Constructor without ID
	 */
	public Product(String name, String description, Long price, String barcode) {
		super();
		this.name = name;
		this.description = description;
		this.price = price;
		this.barcode = barcode;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Store> getStores() {
		return stores;
	}

	public void setStores(List<Store> stores) {
		this.stores = stores;
	}

}
