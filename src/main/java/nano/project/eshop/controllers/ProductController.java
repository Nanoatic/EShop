package nano.project.eshop.controllers;

import nano.project.eshop.models.Product;
import nano.project.eshop.repositories.ProductRepository;
import nano.project.eshop.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class ProductController {

    ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }



    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView allProducts(ModelAndView modelAndView) {
        modelAndView.setViewName("home");
        Iterable<Product> products = productService.findLast10();
        modelAndView.addObject("products",products);
        return modelAndView;
    }
}
