package nano.project.eshop.controllers;

import nano.project.eshop.models.Category;
import nano.project.eshop.models.Product;
import nano.project.eshop.services.HibernateSearchService;
import nano.project.eshop.services.ProductService;
import nano.project.eshop.services.SolrProductService;
import nano.project.eshop.wrappers.PageWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
public class CategoryController {
    private SolrProductService solrProductService;
    private HibernateSearchService hibernateSearchService;
    private ProductService productService;

    @Autowired
    public CategoryController(HibernateSearchService hibernateSearchService, ProductService productService, SolrProductService solrProductService) {
        this.hibernateSearchService = hibernateSearchService;
        this.productService = productService;
        this.solrProductService = solrProductService;
    }

    @RequestMapping(value = "/category", method = RequestMethod.GET)
    public ModelAndView showCategory(ModelAndView modelAndView, @RequestParam Map requestParam, Pageable pageable) {

        modelAndView.setViewName("category");
        Long numberOfComputers =
                productService.countByCategory(Category.DESKTOP) +
                        productService.countByCategory(Category.LAPTOP);
        Long numberOfComponents =
                productService.countByCategory(Category.MOTHERBOARD) +
                        productService.countByCategory(Category.GRAPHICCARDS) +
                        productService.countByCategory(Category.STORAGE) +
                        productService.countByCategory(Category.CASE);
        Long numberOfAccessories =
                productService.countByCategory(Category.MOUSE) +
                        productService.countByCategory(Category.KEYBOARD) + productService.countByCategory(Category.HEADPHONE);
        Long numberOfMiscellaneous =
                productService.countByCategory(Category.CONSOLE) +
                        productService.countByCategory(Category.HEADPHONE);

        modelAndView.addObject("numberOfComputers", numberOfComputers);
        modelAndView.addObject("numberOfComponents", numberOfComponents);
        modelAndView.addObject("numberOfAccessories", numberOfAccessories);
        modelAndView.addObject("numberOfMiscellaneous", numberOfMiscellaneous);

        if (requestParam.get("searchsubmit") != null) {
            if(!((String)requestParam.get("searchsubmit")).equals("")) {
                String searchString = (String) requestParam.get("searchname");
                if (searchString == null) {
                    searchString = "";
                }
                Page<Product> productPage = solrProductService.searchByName(searchString, pageable);
                PageWrapper<Product> page = new PageWrapper<Product>(productPage, "/category");

                List<Product> productList = page.getContent();
                modelAndView.addObject("products", productList);
                modelAndView.addObject("page", page);
                modelAndView.addObject("selectedCategory", Category.ALLBYNAME);
                modelAndView.addObject("searchsubmit","value");
                modelAndView.addObject("searchname",searchString);
                return modelAndView;
            }
        }
        if (requestParam.get("category") != null) {
            String category = (String) requestParam.get("category");
            Page<Product> productPage = productService.findByCategory(category, pageable);
            PageWrapper<Product> page = new PageWrapper<Product>(productPage, "/category");
            List<Product> productList = page.getContent();
            modelAndView.addObject("products", productList);
            modelAndView.addObject("page", page);
            modelAndView.addObject("selectedCategory", category );
            return modelAndView;
        }
        modelAndView.setViewName("redirect:/home");
        return modelAndView;
    }

}
