package nano.project.eshop.controllers;

import nano.project.eshop.models.User;
import nano.project.eshop.services.EmailService;
import nano.project.eshop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class ResetPasswordController {

    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private UserService userService;
    private EmailService emailService;

    @Autowired
    public ResetPasswordController(BCryptPasswordEncoder bCryptPasswordEncoder, UserService userService, EmailService emailService) {

        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userService = userService;
        this.emailService = emailService;
    }


    // Return registration form template
    @RequestMapping(value = "/reset-password", method = RequestMethod.GET)
    public ModelAndView showRegistrationPage(ModelAndView modelAndView, User user) {
        modelAndView.setViewName("reset-password");
        return modelAndView;
    }

    // Process form input data
    @RequestMapping(value = "/reset-password", method = RequestMethod.POST)
    public ModelAndView processRegistrationForm(ModelAndView modelAndView, HttpServletRequest request, @RequestParam Map requestParams, RedirectAttributes redir) {
        modelAndView.setViewName("reset-password");
        String email = (String) requestParams.get("email");
        // Lookup user in database by e-mail
        User user = userService.findByEmail(email);

        if (user == null) {
            modelAndView.addObject("errorMessage", "Email does not exist in database");
            return modelAndView;
        }


        String appUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();

        SimpleMailMessage registrationEmail = new SimpleMailMessage();
        registrationEmail.setTo(user.getEmail());
        registrationEmail.setSubject("Password Reset");
        registrationEmail.setText("To reset your password, please click the link below:\n"
                + appUrl + "/reset?token=" + user.getConfirmationToken());
        registrationEmail.setFrom("noreply@domain.com");

        emailService.sendEmail(registrationEmail);

        modelAndView.addObject("successMessage", "A reset link sent to " + user.getEmail());

        return modelAndView;
    }

    // Process confirmation link
    @RequestMapping(value = "/reset", method = RequestMethod.GET)
    public ModelAndView showReset(ModelAndView modelAndView, @RequestParam("token") String token) {
        modelAndView.setViewName("reset");
        User user = userService.findByConfirmationToken(token);

        if (user == null) { // No token found in DB
            modelAndView.addObject("invalidToken", "Invalid reset token.");
            return modelAndView;

        } else {
            modelAndView.addObject("passwordToken", token);
        }


        return modelAndView;
    }

    @RequestMapping(value = "/reset", method = RequestMethod.POST)
    public ModelAndView submitReset(ModelAndView modelAndView, @RequestParam Map requestParams) {
        modelAndView.setViewName("reset");
        User user = userService.findByConfirmationToken((String) requestParams.get("token"));

        if (user == null) { // No token found in DB
            modelAndView.addObject("invalidToken", "Invalid reset token.");
            return modelAndView;

        }
        String password = (String) requestParams.get("password");
        user.setPassword(bCryptPasswordEncoder.encode(password));
        userService.saveUser(user);

        modelAndView.addObject("successMessage", "Password has been reset.");
        return modelAndView;
    }

}