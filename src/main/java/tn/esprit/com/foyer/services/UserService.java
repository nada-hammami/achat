package tn.esprit.com.foyer.services;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tn.esprit.com.foyer.dto.UserDTO;
import tn.esprit.com.foyer.requests.ChangePasswordRequest;
import tn.esprit.com.foyer.entities.User;
import tn.esprit.com.foyer.repositories.UserRepository;
import tn.esprit.com.foyer.requests.ChangePasswordResponse;
import tn.esprit.com.foyer.config.JwtService;
import java.security.Principal;


@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository repository;
    @NonNull
    HttpServletRequest request;
    public ChangePasswordResponse changePassword(ChangePasswordRequest request, Principal connectedUser) {
        var user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();
        ChangePasswordResponse response = new ChangePasswordResponse();

        // check if the current password is correct
        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            response.setMessage("Wrong password");
            return response;
        }

        // check if the two new passwords are the same


        // update the password
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));

        // save the new password
        repository.save(user);

        response.setMessage("Password changed successfully");
        return response;
    }

    public UserDTO getUserDataByToken() {
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;
        jwt = authHeader.substring(7);
        userEmail = jwtService.extractUsername(jwt);
        try {
            User user = repository.findByEmail(userEmail);
            if (user != null) {
                return UserDTO.fromEntity(user);
            }
        } catch (Exception e) {

            e.printStackTrace();


        }

        return null;
    }
}
