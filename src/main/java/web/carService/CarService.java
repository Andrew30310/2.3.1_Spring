package web.carService;

import web.model.Car;

import java.util.List;

public interface CarService {
    List<Car> getFullListOfCars();
    List<Car> getListOfCars(List<Car> inputList, int num);
}
