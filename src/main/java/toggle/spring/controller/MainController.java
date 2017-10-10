package toggle.spring.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import toggle.spring.models.Disc;
import toggle.spring.models.User;
import toggle.spring.service.util.DiscService;
import toggle.spring.service.util.UserService;


import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.concurrent.Callable;

@Controller
@SessionAttributes(value = "session")
public class MainController {

    @Autowired
    private UserService userService;

    @Autowired
    private DiscService discService;


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView loginUser(User user) {
        ModelAndView modelAndView = new ModelAndView("Login");
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @ModelAttribute("session")
    @RequestMapping(value = "/load", method = RequestMethod.GET)
    public ModelAndView loadUser(HttpServletRequest request) {
        String name = request.getParameter("username");
        String pass = request.getParameter("password");
        User user = userService.login(name, pass);
        return user == null ? new ModelAndView("redirect:/login") :
                new ModelAndView("redirect:/").addObject("session", user);
    }

    @RequestMapping(value = "/reg", method = RequestMethod.GET)
    public ModelAndView regUser() {
        ModelAndView model = new ModelAndView("Register");
        model.addObject("user", new User());
        return model;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView discs(@ModelAttribute("session") User user) {
        List<Disc> discs = discService.list();
        ModelAndView modelAndView = new ModelAndView("Main");
        modelAndView.addObject("discs", discs).addObject("session", user);
        return modelAndView;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelAndView save(@ModelAttribute("session") User user) {
        userService.save(user);
        return new ModelAndView("redirect:/login");
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public ModelAndView disc(@ModelAttribute("session") User user) {
        ModelAndView model = new ModelAndView("Disc");
        model.addObject("disc", new Disc());
        return model;
    } //piece of base login code

    //todo @RequestMapping(value = "/edit", method = RequestMethod.GET)

    @RequestMapping(value = "/savedisc", method = RequestMethod.POST)
    public ModelAndView saveToCollection(@ModelAttribute("session") User user, Disc disc) {
        //todo make norm metod
        discService.save(disc);
        userService.discs(user, disc);
        return new ModelAndView("redirect:/");
    }


    @RequestMapping(value = "/discstrn", method = RequestMethod.GET)
    public ModelAndView discsTrn(@ModelAttribute("session") User user, HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("disc_id"));
        Disc disc = discService.get(id);
        userService.discs(user, disc);
        return new ModelAndView("redirect:/");
    }

}
