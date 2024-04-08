package projectspringboot2.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import projectspringboot2.model.annonce;

public interface annonceRepository extends JpaRepository<annonce,Integer> {

	

}