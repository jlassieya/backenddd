package projectspringboot2.service;



import projectspringboot2.model.Localisation;
import projectspringboot2.model.LocalisationDTO;
import projectspringboot2.model.Utilisateur;
import projectspringboot2.repository.UtilisateurRepository;
import projectspringboot2.repository.LocalisationRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
 
@Service
public class LocalisationService {

    private final LocalisationRepository localisationRepository;
    private final UtilisateurRepository utilisateurRepository;

    @Autowired
    public LocalisationService(LocalisationRepository localisationRepository, UtilisateurRepository utilisateurRepository) {
        this.localisationRepository = localisationRepository;
        this.utilisateurRepository = utilisateurRepository;
    }

    public void addLocalisation(LocalisationDTO localisationDTO) {
        Utilisateur utilisateur = utilisateurRepository.findById(localisationDTO.getUtilisateurId())
                .orElseThrow(() -> new EntityNotFoundException("Utilisateur non trouvé"));

        Localisation localisation = Localisation.builder()
                .utilisateur(utilisateur) // Correction de la référence à l'utilisateur
                .latitude(localisationDTO.getLatitude())
                .longitude(localisationDTO.getLongitude())
                
                .build();

        localisationRepository.save(localisation);
    }

    public List<Localisation> getAllLocalisation() {
        return localisationRepository.findAll();
    }
}
