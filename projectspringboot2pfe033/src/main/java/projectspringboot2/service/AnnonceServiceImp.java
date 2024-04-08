package projectspringboot2.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projectspringboot2.model.annonce;
import projectspringboot2.repository.annonceRepository;
@Service

public class AnnonceServiceImp implements AnnonceService {
	@Autowired 
	private annonceRepository annonceRepository;
	
	
	public List<annonce> getAllAnnonce() {
		 return annonceRepository.findAll();
	}

	
	public annonce findAnnonceById(Integer id) {
		 Optional<annonce> utOptional = annonceRepository.findById(id);
		if (utOptional.isEmpty()) {
			System.out.println("this Annonce is not exist");
			return null;
		}else {
			return utOptional.get();
		}
				
	}
	
	public annonce createAnnonce(annonce annonce) {
		return annonceRepository.save(annonce);
	}
	
	public annonce updateAnnonce (annonce annonce) {
		Optional<annonce> utOptional = annonceRepository.findById(annonce.getId());
		 if (utOptional.isEmpty()) {
			 System.out.println("this Annonce is not exist");
				return null;
			}else {
				System.out.println("Annonce was updated");
				return annonceRepository.save(annonce);
			}
		
	}
	

@Override
public void deleteAnnonce (Integer id) {
	System.out.println("utilisateur was deleted");
	annonceRepository.deleteById(id);
}

}
