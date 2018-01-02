package nano.project.eshop.controllers;

import nano.project.eshop.Helpers;
import nano.project.eshop.models.Order;
import nano.project.eshop.models.Product;
import nano.project.eshop.models.User;
import nano.project.eshop.services.ProductService;
import nano.project.eshop.services.SolrProductService;
import nano.project.eshop.services.UserService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Controller
public class FormsController {
    @Autowired
    private UserService userService;
    @Autowired
    private SolrProductService productService;

    @RequestMapping(value ={"/forms"}, method = RequestMethod.GET)
    public ModelAndView showForms(ModelAndView modelAndView, HttpSession request) {
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
        modelAndView.setViewName("forms");
        return modelAndView;
    }


    /**
     * Upload single file using Spring Controller
     */
    @RequestMapping(value = "/forms", method = RequestMethod.POST)
    public ModelAndView newProduct(ModelAndView modelAndView, @RequestParam MultiValueMap requestparam,
                             @RequestParam("file") MultipartFile file ,HttpSession request) throws IOException {

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
        Product product = new Product();
        List<String> reqdet = (List<String>) requestparam.get("details");
        product.setDetails(reqdet.get(0));
        List<String> reqcat = (List<String>) requestparam.get("category");
        product.setCategory(reqcat.get(0));
        List<String> reqname = (List<String>) requestparam.get("name");
        product.setName(reqname.get(0));
        List<String> reqpri = (List<String>) requestparam.get("price");
        product.setPrice(Float.parseFloat(reqpri.get(0)));
        List<String> reqqut = (List<String>) requestparam.get("quantity");
        product.setQuantity(Integer.parseInt(reqqut.get(0)));

        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();

                String name =  Helpers.gencode9()+"."+ FilenameUtils.getExtension(file.getOriginalFilename());

                // Create the file on server
                File serverFile = new File("src\\main\\resources\\static\\img\\products\\",name);
                serverFile.createNewFile();
                serverFile.delete();
                //wait

                FileUtils.writeByteArrayToFile(serverFile,bytes);

                product.setPhoto("/img/products/"+name);
                modelAndView.setViewName("redirect:/products");



            } catch (Exception e) {
                System.out.println(e.getMessage());
                modelAndView.setViewName("redirect:/home");
                return modelAndView;
            }
        }


        productService.saveProduct(product);


        return  modelAndView;

    }

    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public ModelAndView updateProduct(ModelAndView modelAndView,
                                      @RequestParam MultiValueMap requestparam,HttpSession request) throws IOException {

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
            Product updateproduct = new Product();
            List<String> reqid = (List<String>) requestparam.get("sup");
            List<Long> ids = new ArrayList<>();
        for(String s : reqid) ids.add(Long.parseLong(s));

        List<Product> products = productService.findAllByIds(ids);

        /*updateproduct.setName(product.getName());
        updateproduct.setPhoto(product.getPhoto());
        List<String> reqpri = (List<String>) requestparam.get("price");
        updateproduct.setPrice(Float.parseFloat(reqpri.get(0)));
        List<String> reqqut = (List<String>) requestparam.get("quantity");
        updateproduct.setQuantity(Integer.parseInt(reqqut.get(0)));
        updateproduct.setDetails(product.getDetails());
        updateproduct.setCategory(product.getCategory());*/

            productService.deleteProducts(products);

        //productService.save(updateproduct);

        modelAndView.setViewName("redirect:/products");
        return  modelAndView;
    }




}
