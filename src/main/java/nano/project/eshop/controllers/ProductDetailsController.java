package nano.project.eshop.controllers;

import nano.project.eshop.models.Product;
import nano.project.eshop.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class ProductDetailsController {

    private ProductService productService;

    @Autowired
    public ProductDetailsController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(value = "/product-details",method = RequestMethod.GET)
    public ModelAndView showBasket(ModelAndView modelAndView , @RequestParam Map requestParams){
        if(requestParams.get("id")==null){
            modelAndView.setViewName("redirect:/home");
            return modelAndView;
        }
        Long id ;
        try {
            id = Long.parseLong((String) requestParams.get("id"));
        }catch (Exception e ){
            modelAndView.setViewName("redirect:/home");
            return modelAndView;
        }
        Product product =  productService.findById(id);
        if(product==null){
            modelAndView.setViewName("redirect:/home");
            return modelAndView;
        }
        modelAndView.addObject("product",product);
        modelAndView.setViewName("product-details");
        return modelAndView;

    }
}
