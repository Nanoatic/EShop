package nano.project.eshop.controllers;

import nano.project.eshop.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

import static nano.project.eshop.Constants.SERVER_EMAIL;

@Controller
public class ContactUsController {
    EmailService emailService;

    @Autowired
    public ContactUsController(EmailService emailService) {
        this.emailService = emailService;
    }

    @RequestMapping(value = "/contactus", method = RequestMethod.GET)
    public ModelAndView submitContactForm(ModelAndView modelAndView) {
        modelAndView.setViewName("contactus");
        return modelAndView;
    }

    @RequestMapping(value = "/contactus", method = RequestMethod.POST)
    public ModelAndView showContactForm(ModelAndView modelAndView, @RequestParam Map requestParams) {
        modelAndView.setViewName("contactus");
        SimpleMailMessage registrationEmail = new SimpleMailMessage();
        registrationEmail.setTo(SERVER_EMAIL);
        registrationEmail.setSubject((String) requestParams.get("sendsubject"));
        registrationEmail.setText("First Name :" + requestParams.get("sendfirstname") + "\n"
                + "Last Name :" + requestParams.get("sendlastname") + "\n"
                + "Email :" + requestParams.get("sendemail") + "\n"
                + "Message :" + requestParams.get("sendmessage") + "\n");
        registrationEmail.setFrom("noreply@domain.com");

        emailService.sendEmail(registrationEmail);

        modelAndView.addObject("confirmationMessage", "Message sent to admin");
        return modelAndView;
    }
}
