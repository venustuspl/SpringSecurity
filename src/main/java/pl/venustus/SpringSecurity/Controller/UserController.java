
/*
 * Copyright (c) 2020. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package pl.venustus.SpringSecurity.Controller;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.venustus.SpringSecurity.Model.AppUser;
import pl.venustus.SpringSecurity.Model.Token;
import pl.venustus.SpringSecurity.Repo.AppUserRepo;
import pl.venustus.SpringSecurity.Repo.TokenRepo;
import pl.venustus.SpringSecurity.Service.UserService;

import javax.mail.MessagingException;
import java.security.Principal;
import java.util.Collection;

@Controller
public class UserController {

    private UserService userService;
    private AppUserRepo appUserRepo;
    private TokenRepo tokenRepo;

    public UserController(UserService userService, AppUserRepo appUserRepo, TokenRepo tokenRepo) {
        this.userService = userService;
        this.appUserRepo = appUserRepo;
        this.tokenRepo = tokenRepo;
    }

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
    public String register(AppUser appUser) throws MessagingException {
        userService.addUser(appUser);
        return "sign-up";
    }


    @GetMapping("/token")
    public String register(@RequestParam String value) {
        Token byValue = tokenRepo.findByValue(value);
        AppUser appUser = byValue.getAppUser();
        appUser.setEnabled(true);
        appUserRepo.save(appUser);
        return "sign-up";
    }

}
