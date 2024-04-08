package projectspringboot2.contoller;





	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.http.ResponseEntity;
	import org.springframework.security.crypto.password.PasswordEncoder;
	import org.springframework.web.bind.annotation.*;
	import projectspringboot2.service.EmailService;
	import projectspringboot2.service.UtilisateurService;

	import java.util.Map;

	@RestController
	@RequestMapping("/api/reinitialiserLeMotDePasse")
	public class reinitialiserLeMotDePasseController  {
	    private final UtilisateurService utilisateurService;
	    private final EmailService emailService;

	    @Autowired
	    PasswordEncoder encoder;
	    public reinitialiserLeMotDePasseController (UtilisateurService utilisateurService, EmailService emailService) {
	        this.utilisateurService = utilisateurService;
	        this.emailService = emailService;
	    }
	    @PutMapping("/MotDePasseOublie")
	    public ResponseEntity<Boolean> MotDePasseOublie(@RequestParam String email) {
	        String verificationCode = utilisateurService.generateVerificationCode(email);
	        emailService.EnvoyerEmailDeVerification(email, verificationCode);
	        return ResponseEntity.ok(true);
	    }

	    @PutMapping("/definirMotDePasse")
	    public ResponseEntity<Boolean> definirMotDePasse(@RequestBody Map<String, String> request) {
	        String email = request.get("email");
	        String verificationCode = request.get("verificationCode");
	        String nouveauMotDePasse = request.get("nouveauMotDePasse");
	        // Verify the verification code
	        if (utilisateurService.verifierCodeDeVerification(email, verificationCode)) {
	            // Verification successful, set the new password
	            String result = utilisateurService.definirMotDePasse(email, encoder.encode(nouveauMotDePasse));
	            return ResponseEntity.ok(true);
	        } else {
	            // Verification failed
	            return ResponseEntity.ok(false);		}
	    }
	}

