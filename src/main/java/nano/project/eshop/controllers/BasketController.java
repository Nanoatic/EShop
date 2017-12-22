package nano.project.eshop.controllers;

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
}
