package pl.venustus.SpringSecurity.Service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.venustus.SpringSecurity.Model.AppUser;
import pl.venustus.SpringSecurity.Model.Token;
import pl.venustus.SpringSecurity.Model.TokenRepo;
import pl.venustus.SpringSecurity.Repo.AppUserRepo;

import java.util.UUID;

@Service
public class UserService {

    private TokenRepo tokenRepo;
    private AppUserRepo appUserRepo;
    private PasswordEncoder passwordEncoder;

    public UserService(TokenRepo tokenRepo, AppUserRepo appUserRepo, PasswordEncoder passwordEncoder) {
        this.tokenRepo = tokenRepo;
        this.appUserRepo = appUserRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public void addUser(AppUser appUser) {
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        appUser.setRole("ROLE_USER");
        appUserRepo.save(appUser);
        sendToken(appUser);
    }


    private void sendToken(AppUser appUser) {
        String tokenValue = UUID.randomUUID().toString();
        Token token = new Token();
        token.setValue(tokenValue);
        token.setAppUser(appUser);
        tokenRepo.save(token);
        String url = "http://localhost:8080?token=" + tokenValue;


    }
}
