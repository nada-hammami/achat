package tn.esprit.com.foyer.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.com.foyer.dto.UserDTO;
import tn.esprit.com.foyer.requests.ChangePasswordRequest;
import tn.esprit.com.foyer.requests.ChangePasswordResponse;
import tn.esprit.com.foyer.services.UserService;

import java.security.Principal;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PatchMapping("/change-password")
    public ResponseEntity<ChangePasswordResponse> changePassword(
            @RequestBody ChangePasswordRequest request,
            Principal connectedUser
    ) {
        ChangePasswordResponse response = service.changePassword(request, connectedUser);

        if (response.getMessage().equals("Password changed successfully")) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().body(response);
        }
    }
    @GetMapping("/getDataByToken")
    public UserDTO getDataByTokenl() {
        return service.getUserDataByToken();
    }
}
