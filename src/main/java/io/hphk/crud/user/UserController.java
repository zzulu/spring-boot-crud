package io.hphk.crud.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;


@Controller
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/signup")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "user/signup.html";
    }

    @PostMapping("/signup")
    public RedirectView createUser(@ModelAttribute User user) {
        userService.createUser(user);
        return new RedirectView("/users/login");
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("user", new User());
        return "user/login.html";
    }
}
