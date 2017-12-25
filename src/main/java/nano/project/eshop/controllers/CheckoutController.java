package nano.project.eshop.controllers;

import nano.project.eshop.models.Address;
import nano.project.eshop.models.Order;
import nano.project.eshop.models.User;
import nano.project.eshop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class CheckoutController {
    private UserService userService;

    @Autowired
    public CheckoutController(UserService userService) {
        this.userService = userService;
    }

    //GETS
    @RequestMapping(value = "/checkout1",method = RequestMethod.GET)
    public ModelAndView showCheckout1(ModelAndView modelAndView , @RequestParam Map requestParam, HttpServletRequest request){
        if(request.getSession().getAttribute("name")==null){
            modelAndView.setViewName("redirect:/login?checkout=true");
            return  modelAndView;
        }

        User user =  userService.findByEmail((String) request.getSession().getAttribute("name"));
        modelAndView.addObject("user",user);
        modelAndView.setViewName("checkout1");
        return modelAndView;
    }

    @RequestMapping(value = "/checkout2",method = RequestMethod.GET)
    public ModelAndView showCheckout2(ModelAndView modelAndView ,HttpServletRequest request){

        if(request.getSession().getAttribute("name")==null){
            modelAndView.setViewName("redirect:/login?checkout=true");
            return  modelAndView;
        }
        modelAndView.setViewName("checkout2");
        return modelAndView;
    }
    @RequestMapping(value = "/checkout3",method = RequestMethod.GET)
    public ModelAndView showCheckout3(ModelAndView modelAndView , HttpServletRequest request){
        if(request.getSession().getAttribute("name")==null){
            modelAndView.setViewName("redirect:/login?checkout=true");
            return  modelAndView;
        }
        modelAndView.setViewName("checkout3");
        return modelAndView;
    }
    @RequestMapping(value = "/checkout4",method = RequestMethod.GET)
    public ModelAndView showCheckout4(ModelAndView modelAndView,HttpServletRequest request){
        if(request.getSession().getAttribute("name")==null){
            modelAndView.setViewName("redirect:/login?checkout=true");
            return  modelAndView;
        }
        modelAndView.setViewName("checkout4");
        return modelAndView;
    }

    //POSTS

    @RequestMapping(value = "/checkout1",method = RequestMethod.POST)
    public ModelAndView submitCheckout1(ModelAndView modelAndView , @RequestParam Map requestParams, HttpServletRequest request){
        String email = (String)request.getSession().getAttribute("name");
        if(email==null){
            modelAndView.setViewName("redirect:/login?checkout=true");
            return  modelAndView;
        }
        User user = userService.findByEmail(email);

        if(user==null){
            modelAndView.setViewName("redirect:/login?checkout=true");
            return  modelAndView;
        }
        modelAndView.addObject("user",user);

        String street = (String) requestParams.get("street");
        String country = (String) requestParams.get("country");
        String city = (String) requestParams.get("city");
        String zip_string = (String) requestParams.get("zip");
        String fname = (String) requestParams.get("firstname");
        String lname = (String) requestParams.get("lastname");

        if(street!=null){
            if(street.equals("")){
                modelAndView.addObject("errorMessage", "Street empty!");
                return modelAndView;
            }
        }else {
            modelAndView.addObject("errorMessage", "Street empty!");
            return modelAndView;
        }

        if(country!=null){
            if(country.equals("")){
                modelAndView.addObject("errorMessage", "Country empty!");
                return modelAndView;
            }
        }else {
            modelAndView.addObject("errorMessage", "Country empty!");
            return modelAndView;
        }

        if(city!=null){
            if(city.equals("")){
                modelAndView.addObject("errorMessage", "City empty!");
                return modelAndView;
            }
        }else {
            modelAndView.addObject("errorMessage", "City empty!");
            return modelAndView;
        }

        if(zip_string!=null){
            if(zip_string.equals("")){
                modelAndView.addObject("errorMessage", "Zip empty!");
                return modelAndView;
            }
        }else {
            modelAndView.addObject("errorMessage", "Zip empty!");
            return modelAndView;
        }

        if(fname!=null){
            if(fname.equals("")){
                modelAndView.addObject("errorMessage", "First Name empty!");
                return modelAndView;
            }
        }else {
            modelAndView.addObject("errorMessage", "First Name empty!");
            return modelAndView;
        }

        if(lname!=null){
            if(lname.equals("")){
                modelAndView.addObject("errorMessage", "Last Name empty!");
                return modelAndView;
            }
        }else {
            modelAndView.addObject("errorMessage", "Last Name empty!");
            return modelAndView;
        }

        Address address = new Address();

        address.setCity(city);
        address.setCountry(country);
        address.setStreet(street);
        Integer ZIP;
        try {
            ZIP = Integer.parseInt(zip_string);
        } catch (Exception e) {
            modelAndView.addObject("errorMessage", "Zip code not a number!");
            return modelAndView;
        }
        address.setZip_code(ZIP);
        user.setFirstName(fname);
        user.setLastName(lname);
        user.setAddress(address);
        userService.saveUser(user);
        modelAndView.setViewName("redirect:/checkout2");
        return modelAndView;
    }

    @RequestMapping(value = "/checkout2",method = RequestMethod.POST)
    public ModelAndView submitCheckout2(ModelAndView modelAndView,HttpServletRequest request){
        if(request.getSession().getAttribute("name")==null){
            modelAndView.setViewName("redirect:/login?checkout=true");
            return  modelAndView;
        }

        modelAndView.setViewName("checkout2");
        return modelAndView;
    }
    @RequestMapping(value = "/checkout3",method = RequestMethod.POST)
    public ModelAndView submitCheckout3(ModelAndView modelAndView,HttpServletRequest request){
        if(request.getSession().getAttribute("name")==null){
            modelAndView.setViewName("redirect:/login?checkout=true");
            return  modelAndView;
        }


        modelAndView.setViewName("checkout3");
        return modelAndView;
    }
    @RequestMapping(value = "/checkout4",method = RequestMethod.POST)
    public ModelAndView submitCheckout4(ModelAndView modelAndView, HttpServletRequest request){
        if(request.getSession().getAttribute("name")==null){
            modelAndView.setViewName("redirect:/login?checkout=true");
            return  modelAndView;
        }
        modelAndView.setViewName("checkout4");
        return modelAndView;
    }

}
