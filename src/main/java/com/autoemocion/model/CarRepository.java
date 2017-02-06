package com.autoemocion.model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;



@Repository
public class CarRepository {
	
	@PersistenceContext
	EntityManager em;
	
	
	public void create(Car entity){
		em.persist(entity);
	}
	
	 public List<Car> findAll() {
	        return em.createNativeQuery("SELECT * FROM CAR",Car.class).getResultList();
	    }
	 
	 public void deleteAll(){
		 em.createNativeQuery("DELETE FROM CAR").executeUpdate();
	 }

}
