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

        AppUser appUserMaciek = new AppUser();
        appUserMaciek.setUsername("Maciek");
        appUserMaciek.setPassword(passwordEncoder.encode("Maciek123"));
        appUserMaciek.setRole("ROLE_USER");
        appUserRepo.save(appUserMaciek);

    }


}
