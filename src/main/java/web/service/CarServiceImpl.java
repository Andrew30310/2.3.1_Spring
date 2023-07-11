package web.service;

import org.springframework.stereotype.Component;
import web.model.Car;

import java.util.ArrayList;
import java.util.List;

@Component
public class CarServiceImpl implements CarService {

    @Override
    public List<Car> getFullListOfCars(){
        List<Car> carsList = new ArrayList<>();

        carsList.add(new Car("BMW", 111, 10));
        carsList.add(new Car("Toyota", 222, 3));
        carsList.add(new Car("Porsche", 333, 1));
        carsList.add(new Car("Ford", 444, 15));
        carsList.add(new Car("Skoda", 555, 23));
        return carsList;
    }
    @Override
    public List<Car> getListOfCars(List<Car> inputList, int num) {
        return inputList.subList(0, num);
    }

}
