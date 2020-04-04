package pl.venustus.SpringSecurity;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @GetMapping("/hello")
    @ResponseBody
    public String hello() {
        return "hello";
    }

    @GetMapping("/for-admin")
    public String forAdmin() {
        return "hello-admin";
    }

    @GetMapping("/for-user")
    public String forUser() {
        return "hello-user";
    }

}
