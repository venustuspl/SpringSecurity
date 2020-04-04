package pl.venustus.SpringSecurity;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class Start {

    public Start(AppUserRepo appUserRepo, PasswordEncoder passwordEncoder) {

        AppUser appUser = new AppUser();
        appUser.setUsername("Janusz");
        appUser.setPassword(passwordEncoder.encode("Janusz123"));
        appUser.setRole("ROLE_ADMIN");
        appUserRepo.save(appUser);
    }


}
