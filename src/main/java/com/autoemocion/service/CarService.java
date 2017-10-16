package com.autoemocion.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.autoemocion.service.otomoto.OtomotoService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.autoemocion.model.Car;
import com.autoemocion.model.CarRepository;

import javax.print.Doc;


@Service
@Transactional
public class CarService {

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(CarService.class);

    private CarRepository carRepo;
    private OtomotoService otomotoService;

    public CarService(CarRepository carRepo, OtomotoService otomotoService) {
        this.carRepo = carRepo;
        this.otomotoService = otomotoService;
    }

    public void create(Car car) {
        carRepo.create(car);
    }

    public List<Car> findAll() {
        return carRepo.findAll();
    }

    public void deleteAll() {
        carRepo.deleteAll();
    }
    public void updateData(){
        carRepo.deleteAll();
        List<Car> cars = null;
        try {
            cars = otomotoService.listOfCars();
        } catch (IOException e) {
            LOG.warn(e.getMessage());
        }
        for (Car car : cars) {
            carRepo.create(car);
        }
    }

}
