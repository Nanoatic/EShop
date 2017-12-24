package nano.project.eshop.controllers;

import nano.project.eshop.services.HibernateSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class CategoryController {
    private HibernateSearchService hibernateSearchService;

    @Autowired
    public CategoryController(HibernateSearchService hibernateSearchService) {
        this.hibernateSearchService = hibernateSearchService;
    }

    @RequestMapping(value = "/category" , method = RequestMethod.GET)
    public ModelAndView showCategory(ModelAndView modelAndView , @RequestParam Map requestParam){
        modelAndView.setViewName("category");
        return modelAndView;
    }

}
