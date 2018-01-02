package nano.project.eshop.controllers;


import nano.project.eshop.models.Order;
import nano.project.eshop.models.Product;
import nano.project.eshop.models.User;
import nano.project.eshop.services.OrderService;
import nano.project.eshop.services.ProductService;
import nano.project.eshop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class AdminController {


    private OrderService orderService;

    @Autowired
    public AdminController(OrderService orderService) {
        this.orderService = orderService;
    }
    @Autowired
    private UserService userService;
    @RequestMapping(value ={"/admin"}, method = RequestMethod.GET)
    public ModelAndView showOrders(ModelAndView modelAndView, HttpSession request) {
        String email = (String) request.getAttribute("name");
        if (email == null) {
            modelAndView.setViewName("redirect:/404");
            return modelAndView;
        }
        User user = userService.findByEmail(email);
        if(!user.getRole().equals("admin")){
            modelAndView.setViewName("redirect:/404");
            return modelAndView;
        }

        modelAndView.setViewName("admin");
        List<Order> orders = orderService.findAllOrders();

        modelAndView.addObject("orders",orders);
        return modelAndView;
    }
}
