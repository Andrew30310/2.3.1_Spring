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

    @Autowired
    CarService carService;

    @GetMapping(value = "/cars")
    public String printFullCarsList(ModelMap model, HttpServletRequest request) {

        String count = request.getParameter("count");

        if (count == null) {
            model.addAttribute("resultCarsList", carService.getFullListOfCars());
        } else {
            int intCount = Integer.parseInt(count);
            if (intCount < 5) {
                model.addAttribute("resultCarsList", carService.getListOfCars(carService.getFullListOfCars(), intCount));
            } else {
                model.addAttribute("resultCarsList", carService.getFullListOfCars());
            }
        }

        return "cars";
    }
}
