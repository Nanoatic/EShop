package nano.project.eshop.controllers;

import nano.project.eshop.models.Order;
import nano.project.eshop.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/customer-order", method = RequestMethod.GET)
    public ModelAndView showOrder(ModelAndView modelAndView , @RequestParam Map requestParams, HttpSession httpSession){
        String email = (String) httpSession.getAttribute("name");
        if(email==null){
            modelAndView.setViewName("redirect:/login");
            return modelAndView;
        }
        Long id;
        try {
            id = Long.parseLong((String) requestParams.get("id"));
        } catch (Exception e) {
            modelAndView.setViewName("redirect:/home");
            return modelAndView;
        }
        Order order = orderService.findOrderById(id);
        modelAndView.addObject("order",order);
        modelAndView.setViewName("customer-order");
        return modelAndView;
    }


    @RequestMapping(value = "/customer-order", method = RequestMethod.POST)
    public ModelAndView submitOrder(ModelAndView modelAndView , HttpSession httpSession){
        String email = (String) httpSession.getAttribute("name");
        if(email==null){
            modelAndView.setViewName("redirect:/login");
            return modelAndView;
        }
        modelAndView.setViewName("customer-order");
        return modelAndView;
    }
}
