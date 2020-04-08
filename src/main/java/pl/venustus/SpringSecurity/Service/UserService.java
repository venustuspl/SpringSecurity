/*
 * Copyright (c) 2020. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package pl.venustus.SpringSecurity.Service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.venustus.SpringSecurity.Model.AppUser;
import pl.venustus.SpringSecurity.Model.Token;
import pl.venustus.SpringSecurity.Repo.AppUserRepo;
import pl.venustus.SpringSecurity.Repo.TokenRepo;

import javax.mail.MessagingException;
import java.util.UUID;

@Service
public class UserService {

    private TokenRepo tokenRepo;
    private MailService mailService;
    private AppUserRepo appUserRepo;
    private PasswordEncoder passwordEncoder;

    public UserService(TokenRepo tokenRepo, MailService mailService, AppUserRepo appUserRepo, PasswordEncoder passwordEncoder) {
        this.tokenRepo = tokenRepo;
        this.mailService = mailService;
        this.appUserRepo = appUserRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public void addUser(AppUser appUser) throws MessagingException {
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        appUser.setRole("ROLE_USER");
        appUserRepo.save(appUser);
        sendToken(appUser);
    }


    private void sendToken(AppUser appUser) throws MessagingException {
        String tokenValue = UUID.randomUUID().toString();
        Token token = new Token();
        token.setValue(tokenValue);
        token.setAppUser(appUser);
        tokenRepo.save(token);
        String url = "http://localhost:8080/token?value=" + tokenValue;
        mailService.sendMail(appUser.getUsername(), "Potwierdzaj to!", url, false);

    }
}
