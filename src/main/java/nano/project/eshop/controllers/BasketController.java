package nano.project.eshop.controllers;

import nano.project.eshop.models.Order;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BasketController {

    @RequestMapping(value = "/basket",method = RequestMethod.GET)
    public ModelAndView showBasket(ModelAndView modelAndView){
        modelAndView.setViewName("basket");
        return modelAndView;

    }
    @RequestMapping(value = "/basket",method = RequestMethod.POST)
    public ModelAndView submitBasket(ModelAndView modelAndView){
        Order order = new Order();

        modelAndView.setViewName("basket");
        return modelAndView;

    }
}
