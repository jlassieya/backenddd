package projectspringboot2.contoller;

import org.springframework.http.HttpStatus;
import projectspringboot2.model.AuthenticationResponse;
import projectspringboot2.model.Utilisateur;
import projectspringboot2.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final AuthenticationService authService;

    public AuthenticationController(AuthenticationService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody Utilisateur request
    ) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody Utilisateur request
    ) {
        ResponseEntity<AuthenticationResponse> responseEntity = authService.authenticate(request);
        HttpStatus status = (HttpStatus) responseEntity.getStatusCode();
        if (status == HttpStatus.OK) {
            return ResponseEntity.ok(responseEntity.getBody());
        } else if (status == HttpStatus.NOT_FOUND || status == HttpStatus.UNAUTHORIZED) {
            return ResponseEntity.status(status).body(responseEntity.getBody());
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
