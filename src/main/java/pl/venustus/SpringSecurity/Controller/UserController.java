package pl.venustus.SpringSecurity.Controller;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.venustus.SpringSecurity.Model.AppUser;
import pl.venustus.SpringSecurity.Service.UserService;

import java.security.Principal;
import java.util.Collection;

@Controller
public class UserController {

    UserService userService;

//    for restvalue
//    @GetMapping("/hello")
//    @ResponseBody
//    public String hello() {
//        return "hello";
//    }

//    @GetMapping("/for-admin")
//    public String forAdmin() {
//        return "hello-admin";
//    }

    @GetMapping("/hello")
    public String hello(Principal principal, Model model) {
        model.addAttribute("name", principal.getName());
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        model.addAttribute("authorities", authorities);
        Object details = SecurityContextHolder.getContext().getAuthentication().getDetails();
        model.addAttribute("details", details);
        return "hello";
    }

    @GetMapping("/sign-up")
    public String signup(Model model) {
        model.addAttribute("user", new AppUser());
        return "sign-up";
    }

    @PostMapping("/register")
    public String register(AppUser appUser) {
        userService.addUser(appUser);
        return "sign-up";
    }

}
