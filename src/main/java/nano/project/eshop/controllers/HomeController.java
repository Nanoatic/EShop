package nano.project.eshop.controllers;

import nano.project.eshop.models.Product;
import nano.project.eshop.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class HomeController {

    private ProductService productService;

    @Autowired
    public HomeController(ProductService productService) {
        this.productService = productService;
    }


    @RequestMapping(value = {"/home", "/"}, method = RequestMethod.GET)
    public ModelAndView showHome(ModelAndView modelAndView) {
        modelAndView.setViewName("home");
        List<Product> products = productService.findLast10();
        modelAndView.addObject("products", products);
        return modelAndView;
    }

}
