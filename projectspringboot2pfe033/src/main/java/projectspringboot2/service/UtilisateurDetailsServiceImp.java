package projectspringboot2.service;

import projectspringboot2.repository.UtilisateurRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UtilisateurDetailsServiceImp implements UserDetailsService {

    private final UtilisateurRepository repository;

    public UtilisateurDetailsServiceImp(UtilisateurRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String nomUtilisateur) throws UsernameNotFoundException {
        return repository.findByNomUtilisateur(nomUtilisateur)
                .orElseThrow(()-> new UsernameNotFoundException("Utilisateur non trouvé"));
    }
}
