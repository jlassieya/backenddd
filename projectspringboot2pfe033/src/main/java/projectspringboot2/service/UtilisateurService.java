package projectspringboot2.service;

import org.springframework.stereotype.Service;

import projectspringboot2.model.Utilisateur;
import projectspringboot2.repository.UtilisateurRepository;

import java.security.SecureRandom;

@Service
public class UtilisateurService {
    private final UtilisateurRepository utilisateurRepository;


    public UtilisateurService(UtilisateurRepository utilisateurRepository ) {
        this.utilisateurRepository = utilisateurRepository;
     }

    public String generateVerificationCode(String email) {
        // Generate a random verification code
        SecureRandom secureRandom = new SecureRandom();
        int verificationCode = secureRandom.nextInt(900000) + 100000; // 6-digit code

        // Find the user by email
        Utilisateur utilisateur = utilisateurRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable avec cet email: " + email));

        // Set the verification code to the user
        utilisateur.setVerificationCode(String.valueOf(verificationCode));

        // Save the user to persist the verification code
        utilisateurRepository.save(utilisateur);

        // For simplicity, let's print it here
        System.out.println("Code de vérification pour " + email + ": " + verificationCode);

        return String.valueOf(verificationCode);
    }
    public boolean verifierCodeDeVerification(String email, String CodeSaisi) {
        Utilisateur utilisateur = utilisateurRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable avec cet email: " + email));

        // Assuming getVerificationCode() returns a String or null
        String codeStocke = utilisateur.getVerificationCode();

        // Logging for debugging
        System.out.println("Code Saisi: " + CodeSaisi);
        System.out.println("code Stocke: " + codeStocke);

        // Check if the stored verification code matches the entered code
        return CodeSaisi != null && CodeSaisi.equals(codeStocke);
    }
    public String definirMotDePasse(String email, String nouveauMotDePasse) {
        Utilisateur utilisateur = utilisateurRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable avec cet email: " + email));
        utilisateur.setMotDePasse(nouveauMotDePasse);
        utilisateur.setVerificationCode(null);
        utilisateurRepository.save(utilisateur);
        return "Nouveau mot de passe défini avec succès";
    }
}
