package projectspringboot2.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import projectspringboot2.model.AuthenticationResponse;
import projectspringboot2.model.Utilisateur;
import projectspringboot2.repository.UtilisateurRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final UtilisateurRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(UtilisateurRepository repository,
                                 PasswordEncoder passwordEncoder,
                                 JwtService jwtService,
                                 AuthenticationManager authenticationManager) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public AuthenticationResponse register(Utilisateur request) {
        // Vérifier si l'utilisateur existe déjà. Si oui, authentifier l'utilisateur
        if (repository.findByNomUtilisateur(request.getNomUtilisateur()).isPresent()) {
            return new AuthenticationResponse(null, "L'utilisateur existe déjà", null);
        }

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setPrenom(request.getPrenom());
        utilisateur.setNom(request.getNom());
        utilisateur.setNomUtilisateur(request.getNomUtilisateur());
        utilisateur.setMotDePasse(passwordEncoder.encode(request.getMotDePasse()));
        utilisateur.setEmail(request.getEmail());
        utilisateur.setRole(request.getRole());

        utilisateur = repository.save(utilisateur);

        String jwt = jwtService.generateToken(utilisateur);

        return new AuthenticationResponse(jwt, "L'inscription de l'utilisateur a réussi",utilisateur.getRole());
    }

    public ResponseEntity<AuthenticationResponse> authenticate(Utilisateur request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getNomUtilisateur(),
                            request.getMotDePasse()
                    )
            );

            Utilisateur utilisateur = repository.findByNomUtilisateur(request.getNomUtilisateur()).orElseThrow();

            String jwt = jwtService.generateToken(utilisateur);

            return ResponseEntity.ok(new AuthenticationResponse(jwt, "La connexion de l'utilisateur a réussi", utilisateur.getRole()));
        } catch (UsernameNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new AuthenticationResponse(null, "Nom d'utilisateur non trouvé", null));
        } catch (AuthenticationException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new AuthenticationResponse(null, "Mot de passe incorrect", null));
        }
    }

}
