package com.tienda.springboot.rest.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tienda.springboot.rest.entity.Client;
import com.tienda.springboot.rest.entity.Product;
import com.tienda.springboot.rest.entity.Purchase;
import com.tienda.springboot.rest.exception.ClientException;
import com.tienda.springboot.rest.repository.ClientRepository;
import com.tienda.springboot.rest.repository.ProductRepository;
import com.tienda.springboot.rest.repository.PurchaseRepository;

@RestController
public class PurchaseResource {

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private PurchaseRepository purchaseRepository;

	@PostMapping("/BuyProducts")
	public Client purchaseProducts(@RequestBody Client client) {
		try {
			if (client == null || client.getDocument() == null 
					|| client.getDocument().trim().equals("")) {
				throw new ClientException("Please enter the document!");
			} else if (client.getPurchases() == null || client.getPurchases().isEmpty()) {
				throw new ClientException("Enter at least one purchase!");
			}
			//Validar que las compras tengan productos
			for (int i = 0; i < client.getPurchases().size(); i++) {
				if (client.getPurchases().get(i).getProducts() == null 
						|| client.getPurchases().get(i).getProducts().isEmpty()) {
					throw new ClientException("Purchase "+(i+1)+" must have at least one product!");
				}
			}

			//Validar que el cliente exista
			Client clientBD = clientRepository.findByDocument(client.getDocument());
			if (clientBD == null) {
				throw new ClientException("Client doesn't exist!");
			}
			List<Purchase> listPurchases = client.getPurchases();
			for (Purchase purchase : listPurchases) {
				purchase.setDate(new Date().toString());
				purchase.setMount(new Long(0));
				purchase.setNumberProducts(String.valueOf(purchase.getProducts().size()));
				List<Product> listroducts = purchase.getProducts();
				for (Product product : listroducts) {
					Optional<Product> productoBD = productRepository.findById(product.getId());
					purchase.setMount(purchase.getMount()+productoBD.get().getPrice());
				}
			}
			clientBD.setPurchases(client.getPurchases());
			for (Purchase purchase : listPurchases) {
				purchaseRepository.save(purchase);
			}
			clientRepository.save(clientBD);
			return clientBD;

		} catch (ClientException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ClientException("General error, contact the Admin!");
		}

	}

}
