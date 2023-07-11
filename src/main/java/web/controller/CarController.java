package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import web.service.CarService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping(value = "/cars")
    public String printFullCarsList(ModelMap model, HttpServletRequest request) {

        String count = request.getParameter("count");

        if (count == null) {
            model.addAttribute("resultCarsList", carService.getFullListOfCars());
        } else {
            int intCount = Integer.parseInt(count);
            if (intCount < 5 && intCount > 0) {
                model.addAttribute("resultCarsList", carService.getListOfCars(carService.getFullListOfCars(), intCount));
            } else {
                model.addAttribute("resultCarsList", carService.getFullListOfCars());
            }
        }

        return "cars";
    }
}
