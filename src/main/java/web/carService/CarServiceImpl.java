package web.carService;

import org.springframework.beans.factory.annotation.Autowired;
import web.carService.CarService;
import web.model.Car;

import java.util.ArrayList;
import java.util.List;

public class CarServiceImpl implements CarService {

    @Override
    public List<Car> getListOfCars(List<Car> inputList, int num) {
        return inputList.subList(0, num);
    }

}
