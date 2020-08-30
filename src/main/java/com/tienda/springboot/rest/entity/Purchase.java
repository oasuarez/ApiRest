package com.tienda.springboot.rest.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Purchase {
	@Id
	@GeneratedValue
	private Long id;
	private String date;
	private Long mount;
	private String numberProducts;
	@OneToMany
	private List<Product> products = new ArrayList<>();

	public Purchase() {
		super();
	}
	
	public Purchase(Long id, String date, Long mount, String numberProducts) {
		super();
		this.id = id;
		this.date = date;
		this.mount = mount;
		this.numberProducts = numberProducts;
	}

	public Purchase(String date, Long mount, String numberProducts) {
		super();
		this.date = date;
		this.mount = mount;
		this.numberProducts = numberProducts;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Long getMount() {
		return mount;
	}

	public void setMount(Long mount) {
		this.mount = mount;
	}

	public String getNumberProducts() {
		return numberProducts;
	}

	public void setNumberProducts(String numberProducts) {
		this.numberProducts = numberProducts;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

}
