package nano.project.eshop.controllers;

import nano.project.eshop.models.Address;
import nano.project.eshop.models.Order;
import nano.project.eshop.models.OrderStatus;
import nano.project.eshop.models.User;
import nano.project.eshop.services.OrderLineService;
import nano.project.eshop.services.OrderService;
import nano.project.eshop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Map;

@Controller
public class CheckoutController {
    private UserService userService;
    private OrderService orderService;
    private OrderLineService orderLineService;

    @Autowired
    public CheckoutController(UserService userService ,OrderService orderService , OrderLineService orderLineService) {
        this.orderLineService = orderLineService;
        this.orderService = orderService;
        this.userService = userService;
    }

    //GETS
    @RequestMapping(value = "/checkout1", method = RequestMethod.GET)
    public ModelAndView showCheckout1(ModelAndView modelAndView, @RequestParam Map requestParam, HttpSession request) {
        String email = (String) request.getAttribute("name");
        if (email == null) {
            modelAndView.setViewName("redirect:/login?checkout=true");
            return modelAndView;
        }
        Order order = (Order) request.getAttribute("order");
        if (order == null) {
            modelAndView.setViewName("redirect:/basket");
            return modelAndView;
        }
        modelAndView.addObject("order", order);

        User user = userService.findByEmail((String) request.getAttribute("name"));
        modelAndView.addObject("user", user);
        order.setOwner(user);
        modelAndView.setViewName("checkout1");
        return modelAndView;
    }

    @RequestMapping(value = "/checkout2", method = RequestMethod.GET)
    public ModelAndView showCheckout2(ModelAndView modelAndView, HttpSession request) {

        if (request.getAttribute("name") == null) {
            modelAndView.setViewName("redirect:/login?checkout=true");
            return modelAndView;
        }
        Order order = (Order) request.getAttribute("order");
        if (order == null) {
            modelAndView.setViewName("redirect:/basket");
            return modelAndView;
        }
        modelAndView.addObject("order", order);
        if(order.getAddress()==null){
            modelAndView.setViewName("redirect:/checkout1");
            return modelAndView;
        }
        modelAndView.setViewName("checkout2");
        return modelAndView;
    }

    @RequestMapping(value = "/checkout3", method = RequestMethod.GET)
    public ModelAndView showCheckout3(ModelAndView modelAndView, HttpSession request) {
        if (request.getAttribute("name") == null) {
            modelAndView.setViewName("redirect:/login?checkout=true");
            return modelAndView;
        }
        Order order = (Order) request.getAttribute("order");
        if (order == null) {
            modelAndView.setViewName("redirect:/basket");
            return modelAndView;
        }
        modelAndView.addObject("order", order);
        if(order.getMethod()==null){
            modelAndView.setViewName("redirect:/checkout2");
            return modelAndView;
        }
        modelAndView.setViewName("checkout3");
        return modelAndView;
    }


    @RequestMapping(value = "/checkout4", method = RequestMethod.GET)
    public ModelAndView showCheckout4(ModelAndView modelAndView, HttpSession request) {
        if (request.getAttribute("name") == null) {
            modelAndView.setViewName("redirect:/login?checkout=true");
            return modelAndView;
        }
        Order order = (Order) request.getAttribute("order");
        if (order == null) {
            modelAndView.setViewName("redirect:/basket");
            return modelAndView;
        }
        modelAndView.addObject("order", order);

        if(order.getPayement()==null){
            modelAndView.setViewName("redirect:/checkout3");
            return modelAndView;
        }
        modelAndView.setViewName("checkout4");
        return modelAndView;
    }

    //POSTS

    @RequestMapping(value = "/checkout1", method = RequestMethod.POST)
    public ModelAndView submitCheckout1(ModelAndView modelAndView, @RequestParam Map requestParams, HttpSession request) {
        String email = (String) request.getAttribute("name");
        Order order = (Order) request.getAttribute("order");
        if (order == null) {
            modelAndView.setViewName("redirect:/basket");
            return modelAndView;
        }
        modelAndView.addObject("order", order);
        if (email == null) {
            modelAndView.setViewName("redirect:/login?checkout=true");
            return modelAndView;
        }
        User user = userService.findByEmail(email);

        if (user == null) {
            modelAndView.setViewName("redirect:/login?checkout=true");
            return modelAndView;
        }
        modelAndView.addObject("user", user);
        order.setOwner(user);
        String street = (String) requestParams.get("street");
        String country = (String) requestParams.get("country");
        String city = (String) requestParams.get("city");
        String zip_string = (String) requestParams.get("zip");
        String fname = (String) requestParams.get("firstname");
        String lname = (String) requestParams.get("lastname");

        if (street != null) {
            if (street.equals("")) {
                modelAndView.addObject("errorMessage", "Street empty!");
                return modelAndView;
            }
        } else {
            modelAndView.addObject("errorMessage", "Street empty!");
            return modelAndView;
        }

        if (country != null) {
            if (country.equals("")) {
                modelAndView.addObject("errorMessage", "Country empty!");
                return modelAndView;
            }
        } else {
            modelAndView.addObject("errorMessage", "Country empty!");
            return modelAndView;
        }

        if (city != null) {
            if (city.equals("")) {
                modelAndView.addObject("errorMessage", "City empty!");
                return modelAndView;
            }
        } else {
            modelAndView.addObject("errorMessage", "City empty!");
            return modelAndView;
        }

        if (zip_string != null) {
            if (zip_string.equals("")) {
                modelAndView.addObject("errorMessage", "Zip empty!");
                return modelAndView;
            }
        } else {
            modelAndView.addObject("errorMessage", "Zip empty!");
            return modelAndView;
        }

        if (fname != null) {
            if (fname.equals("")) {
                modelAndView.addObject("errorMessage", "First Name empty!");
                return modelAndView;
            }
        } else {
            modelAndView.addObject("errorMessage", "First Name empty!");
            return modelAndView;
        }

        if (lname != null) {
            if (lname.equals("")) {
                modelAndView.addObject("errorMessage", "Last Name empty!");
                return modelAndView;
            }
        } else {
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
        order.setAddress(address);
        userService.saveUser(user);
        modelAndView.setViewName("redirect:/checkout2");
        return modelAndView;
    }

    @RequestMapping(value = "/checkout2", method = RequestMethod.POST)
    public ModelAndView submitCheckout2(ModelAndView modelAndView, @RequestParam Map requestParams, HttpSession request) {
        if (request.getAttribute("name") == null) {
            modelAndView.setViewName("redirect:/login?checkout=true");
            return modelAndView;
        }
        Order order = (Order) request.getAttribute("order");
        if (order == null) {
            modelAndView.setViewName("redirect:/basket");
            return modelAndView;
        }
        modelAndView.addObject("order", order);
        if(order.getAddress()==null){
            modelAndView.setViewName("redirect:/checkout1");
            return modelAndView;
        }
        String method = (String) requestParams.get("delivery");
        order.setMethod(method);
        modelAndView.setViewName("redirect:/checkout3");
        return modelAndView;
    }

    @RequestMapping(value = "/checkout3", method = RequestMethod.POST)
    public ModelAndView submitCheckout3(ModelAndView modelAndView,@RequestParam Map requestParams, HttpSession request) {
        if (request.getAttribute("name") == null) {
            modelAndView.setViewName("redirect:/login?checkout=true");
            return modelAndView;
        }

        Order order = (Order) request.getAttribute("order");
        if (order == null) {
            modelAndView.setViewName("redirect:/basket");
            return modelAndView;
        }
        modelAndView.addObject("order", order);
        if(order.getMethod()==null){
            modelAndView.setViewName("redirect:/checkout2");
            return modelAndView;
        }
        String pay = (String) requestParams.get("payment");
        order.setPayement(pay);

        modelAndView.setViewName("redirect:/checkout4");
        return modelAndView;
    }

    @RequestMapping(value = "/checkout4", method = RequestMethod.POST)
    public ModelAndView submitCheckout4(ModelAndView modelAndView, HttpSession request) {
        String email = (String) request.getAttribute("name");
        if (email == null) {
            modelAndView.setViewName("redirect:/login?checkout=true");
            return modelAndView;
        }
        Order order = (Order) request.getAttribute("order");
        if (order == null) {
            modelAndView.setViewName("redirect:/basket");
            return modelAndView;
        }
        modelAndView.addObject("order", order);
        if(order.getPayement()==null){
            modelAndView.setViewName("redirect:/checkout3");
            return modelAndView;
        }
        order.setOrder_date(new Date());
        order.setStatus(OrderStatus.BEING_PREPARED);
        orderService.saveOrder(order);

        for (int i = 0; i < order.getOrderLines().size() ; i++) {
            orderLineService.saveOrderLine(order.getOrderLines().get(i));
        }

        request.removeAttribute("order");

        modelAndView.setViewName("redirect:/customer-orders");
        return modelAndView;
    }

}
