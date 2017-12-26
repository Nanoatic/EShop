package nano.project.eshop.controllers;

import nano.project.eshop.models.User;
import nano.project.eshop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class OrdersController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/customer-orders", method = RequestMethod.GET)
    public ModelAndView showOrders(ModelAndView modelAndView , HttpSession httpSession){
        String email = (String) httpSession.getAttribute("name");
        if(email==null){
            modelAndView.setViewName("redirect:/login");
            return modelAndView;
        }
        User user = userService.findByEmail(email);
        modelAndView.addObject("orders",user.getOrders());
        modelAndView.setViewName("customer-orders");
        return modelAndView;
    }


    @RequestMapping(value = "/customer-orders", method = RequestMethod.POST)
    public ModelAndView submitOrders(ModelAndView modelAndView , HttpSession httpSession){
        String email = (String) httpSession.getAttribute("name");
        if(email==null){
            modelAndView.setViewName("redirect:/login");
            return modelAndView;
        }
        modelAndView.setViewName("customer-orders");
        return modelAndView;
    }
}
