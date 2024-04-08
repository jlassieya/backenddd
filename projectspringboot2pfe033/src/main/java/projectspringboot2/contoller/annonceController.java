package projectspringboot2.contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import projectspringboot2.model.annonce;
import projectspringboot2.service.AnnonceService;


@RestController
@RequestMapping("/annonce")
public class annonceController {
	@Autowired
	private AnnonceService annonceService;
	
	
	@GetMapping()
	 
	 @CrossOrigin
	public List<annonce> getAllUtilisateur() {
		return annonceService.getAllAnnonce();
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<annonce>  findAnnonceById(@PathVariable Integer id) {
		annonce annonce = annonceService.findAnnonceById(id);
		if (annonce==null) {
			return new ResponseEntity<annonce>(HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<annonce>(annonce,HttpStatus.OK);
		}
		
	}
	@PostMapping
	public annonce CreateAnnonce(@RequestBody annonce annonce) {
		return annonceService.createAnnonce(annonce);
	}
	
	@DeleteMapping(path = "/{id}")
	public void deleteAnnonce(@PathVariable Integer id) {
		annonceService.deleteAnnonce(id);
		 System.out.println("utilisateur was deleted");
	}
	@PutMapping
	public annonce updateAnnonce(@RequestBody annonce annonce) {
		return annonceService.updateAnnonce(annonce);
	}
}
