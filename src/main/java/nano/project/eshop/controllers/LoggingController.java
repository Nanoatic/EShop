package nano.project.eshop.controllers;

import nano.project.eshop.models.User;
import nano.project.eshop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
public class LoggingController {

    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private UserService userService;
    @Autowired
    public LoggingController(BCryptPasswordEncoder bCryptPasswordEncoder, UserService userService) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userService = userService;

    }
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ModelAndView logout(ModelAndView modelAndView,
                                      HttpServletRequest request) {
        modelAndView.setViewName("redirect:/home");
        if(request.getSession().getAttribute("name")!=null){
         request.getSession().invalidate();
        }
        return modelAndView;

    }
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView showLoginForm(ModelAndView modelAndView,
                                      HttpServletRequest request) {
        modelAndView.setViewName("login");
        if(request.getSession().getAttribute("name")!=null){
            modelAndView.setViewName("redirect:/home");
        }
        return modelAndView;

    }

        @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView processLoginForm(ModelAndView modelAndView,
                                                HttpServletRequest request,

                                                @RequestParam Map requestParams) {


        if (requestParams.get("email") != null) {
             User user = userService.findByEmail((String) requestParams.get("email"));
            System.out.println(user);
            if (user == null) {
                modelAndView.addObject("loginErrorMessage", "User is not in database !");
                modelAndView.setViewName("login");
                return modelAndView;
            }
            if (!bCryptPasswordEncoder.matches((CharSequence) requestParams.get("password"),user.getPassword())) {
                modelAndView.addObject("loginErrorMessage", "Password is wrong !");
                modelAndView.setViewName("login");
                return modelAndView;

            }
            user.setPassword(bCryptPasswordEncoder.encode((CharSequence) requestParams.get("password")));
            userService.saveUser(user);
            if (!user.getEnabled()) {
                modelAndView.addObject("loginErrorMessage", "Your account is not activated !");
                modelAndView.setViewName("login");
                return modelAndView;

            }
            //Set Session attribs on login
            request.getSession().setAttribute("name", user.getEmail());
            request.getSession().setAttribute("fname",user.getFirstName());
            request.getSession().setAttribute("role",user.getRole());

            modelAndView.setViewName("redirect:/customer-account");
            return modelAndView;
        }
        modelAndView.setViewName("redirect:/customer-account");
        return modelAndView;

    }
}
