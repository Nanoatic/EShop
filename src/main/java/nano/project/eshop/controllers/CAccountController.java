package nano.project.eshop.controllers;

import nano.project.eshop.models.Address;
import nano.project.eshop.models.User;
import nano.project.eshop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Controller
public class CAccountController {
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private UserService userService;

    @Autowired
    public CAccountController(BCryptPasswordEncoder bCryptPasswordEncoder, UserService userService) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userService = userService;
    }

    @RequestMapping(value = "/customer-account", method = RequestMethod.GET)
    public ModelAndView showCustomerAccount(ModelAndView modelAndView, @RequestParam(required = false) Map requestParams, HttpSession request) {

        modelAndView.setViewName("customer-account");
        String email = (String) request.getAttribute("name");
        if (email == null) {
            modelAndView.setViewName("redirect:/home");
            return modelAndView;
        }

        User user = userService.findByEmail(email);
        requestParams.put("user", user);
        modelAndView.addAllObjects(requestParams);
        return modelAndView;
    }

    @RequestMapping(value = "/customer-account", method = RequestMethod.POST)
    public ModelAndView submitCustomerAccount(ModelAndView modelAndView, @RequestParam Map requestParams, HttpSession request) {
        modelAndView.setViewName("customer-account");
        String email = (String) request.getAttribute("name");
        if (email == null) {
            modelAndView.setViewName("redirect:/home");
            return modelAndView;
        }
        User user = userService.findByEmail(email);
        modelAndView.addObject("user", user);
        String bdate = (String) requestParams.get("bdate");
        boolean dateIsSet = true;
        if (bdate != null) {
            SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
            Date date = null;
            try {
                date = formatter1.parse((String) requestParams.get("bdate"));
            } catch (Exception e) {
                modelAndView.addObject("warningMessage", "Date wrong format!");
                dateIsSet = false;
            }
            if (dateIsSet) {
                user.setBdate(date);
            }
        }
        if (requestParams.get("savechanges") != null) {
            Address address = new Address();
            address.setCity((String) requestParams.get("city"));
            address.setCountry((String) requestParams.get("country"));
            address.setStreet((String) requestParams.get("street"));
            Integer ZIP;
            try {
                ZIP = Integer.parseInt((String) requestParams.get("zip"));
            } catch (Exception e) {
                modelAndView.addObject("errorMessage", "Zip code not a number!");
                return modelAndView;
            }
            address.setZip_code(ZIP);
            user.setFirstName((String) requestParams.get("firstname"));
            user.setLastName((String) requestParams.get("lastname"));
            user.setAddress(address);
            userService.saveUser(user);
            request.setAttribute("fname", user.getFirstName());
            modelAndView.addObject("successMessage", "Changes saved succuessfully!");
            return modelAndView;
        } else {
            if (requestParams.get("savepassword") != null) {
                String oldpwd = (String) requestParams.get("oldpassword");
                String newpwd = (String) requestParams.get("newpassword");
                String rnewpwd = (String) requestParams.get("repeatnewpassword");
                if (!newpwd.equals(rnewpwd)) {
                    modelAndView.addObject("perrorMessage", "Passwords are no identical!");
                    return modelAndView;
                }
                if (!bCryptPasswordEncoder.matches(oldpwd, user.getPassword())) {
                    modelAndView.addObject("perrorMessage", "Wrong old password!");
                    return modelAndView;
                }
                user.setPassword(bCryptPasswordEncoder.encode(newpwd));
                userService.saveUser(user);
                modelAndView.addObject("psuccessMessage", "Password changed with success!");
                return modelAndView;
            }
        }
        return modelAndView;
    }
}
