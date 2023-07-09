package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import web.carService.CarService;
import web.carService.CarServiceImpl;
import web.model.Car;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CarController {

    @GetMapping(value = "/cars")
    public String printFullCarsList(ModelMap model, HttpServletRequest request) {

        String count = request.getParameter("count");

        CarService carService = new CarServiceImpl();

        List<Car> carsList = new ArrayList<>();

        carsList.add(new Car("BMW", 111, 10));
        carsList.add(new Car("Toyota", 222, 3));
        carsList.add(new Car("Porsche", 333, 1));
        carsList.add(new Car("Ford", 444, 15));
        carsList.add(new Car("Skoda", 555, 23));

        if (count == null) {
            model.addAttribute("resultCarsList", carsList);
        } else {
            int intCount = Integer.parseInt(count);
            if (intCount < 5) {
                model.addAttribute("resultCarsList", carService.getListOfCars(carsList, intCount));
            } else {
                model.addAttribute("resultCarsList", carsList);
            }
        }

        return "cars";
    }

}
