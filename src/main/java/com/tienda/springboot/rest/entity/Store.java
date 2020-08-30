package com.tienda.springboot.rest.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Store {
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private String schedule;
	private String address;
	@ManyToMany
	@JoinTable(name = "store_product",
	joinColumns = @JoinColumn(name = "store_id"),
	inverseJoinColumns = @JoinColumn(name = "product_id")
			)
	private List<Product> products = new ArrayList<>();

	public void addProduct(Product product) {
		products.add(product);
		product.getStores().add(this);
	}

	public void removeProduct(Product product) {
		products.remove(product);
		product.getStores().remove(this);
	}

	public Store() {
		super();
	}

	public Store(Long id, String name, String schedule, String address) {
		super();
		this.id = id;
		this.name = name;
		this.schedule = schedule;
		this.address = address;
	}

	/*
	 * Constructor without ID
	 */
	public Store(String name, String schedule, String address) {
		super();
		this.name = name;
		this.schedule = schedule;
		this.address = address;
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

	public String getSchedule() {
		return schedule;
	}

	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

}
