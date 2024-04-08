package projectspringboot2.service;

import java.util.List;

import projectspringboot2.model.annonce;

public interface AnnonceService {
	public List<annonce> getAllAnnonce();
	public annonce findAnnonceById(Integer id);
	public annonce createAnnonce(annonce annonce);
	public annonce updateAnnonce (annonce annonce);
	public void deleteAnnonce(Integer id);

}
