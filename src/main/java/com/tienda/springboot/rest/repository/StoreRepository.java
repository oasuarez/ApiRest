package com.tienda.springboot.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tienda.springboot.rest.entity.Store;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long>{

}
