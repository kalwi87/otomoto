package com.autoemocion.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.autoemocion.model.Car;
import com.autoemocion.service.CarService;

@RestController
@RequestMapping("/cars")
public class CarController {
	
	@Autowired
	CarService carservice;
	
	@GetMapping
	public List<Car> getListOfCars() throws IOException{
		return carservice.listOfCars();
	}
	

}