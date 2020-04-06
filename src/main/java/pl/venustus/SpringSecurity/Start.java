package pl.venustus.SpringSecurity;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.venustus.SpringSecurity.Model.AppUser;
import pl.venustus.SpringSecurity.Repo.AppUserRepo;

@Configuration
public class Start {

    public Start(AppUserRepo appUserRepo, PasswordEncoder passwordEncoder) {

        AppUser appUser = new AppUser();
        appUser.setUsername("Janusz");
        appUser.setPassword(passwordEncoder.encode("Janusz123"));
        appUser.setRole("ROLE_ADMIN");
        appUser.setEnabled(true);
        appUserRepo.save(appUser);

        AppUser appUserMaciek = new AppUser();
        appUserMaciek.setUsername("Maciek");
        appUserMaciek.setPassword(passwordEncoder.encode("Maciek123"));
        appUserMaciek.setRole("ROLE_USER");
        appUserMaciek.setEnabled(true);
        appUserRepo.save(appUserMaciek);

    }


}
