package nano.project.eshop.controllers;

import nano.project.eshop.models.Order;
import nano.project.eshop.models.OrderLine;
import nano.project.eshop.models.Product;
import nano.project.eshop.services.ProductService;
import nano.project.eshop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class BasketController {
    private ProductService productService;

    @Autowired
    public BasketController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(value = "/basket", method = RequestMethod.GET)
    public ModelAndView showBasket(ModelAndView modelAndView) {
        modelAndView.setViewName("basket");
        return modelAndView;

    }

    @RequestMapping(value = "/basket", method = RequestMethod.POST)
    public ModelAndView submitBasket(ModelAndView modelAndView, @RequestParam MultiValueMap requestParams, HttpSession request) {
        modelAndView.setViewName("basket");
        Order order = new Order();
        List<String> listids = (List<String>) requestParams.get("ids[]");
        List<String> listqts = (List<String>) requestParams.get("qts[]");
        if(listids==null){
            modelAndView.addObject("errorMessage","Basket is empty!");
            return  modelAndView;
        }
        if(listids.isEmpty()){
            modelAndView.addObject("errorMessage","Basket is empty!");
            return  modelAndView;
        }
        List<OrderLine> orderLines = new ArrayList<>();
        for (int i = 0; i < listids.size(); i++) {
            OrderLine orderLine = new OrderLine();
            orderLine.setQuantity(Integer.parseInt(listqts.get(i)));
            Product product = productService.findById(Long.parseLong(listids.get(i)));
            orderLine.setProduct(product);
            orderLine.setOrder(order);
            orderLines.add(orderLine);

        }
        order.setOrderLines(orderLines);
        request.setAttribute("order", order);
        modelAndView.setViewName("redirect:/checkout1");
        return modelAndView;

    }
}
