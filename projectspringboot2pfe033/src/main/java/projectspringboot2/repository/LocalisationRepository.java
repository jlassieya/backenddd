package projectspringboot2.repository;



import projectspringboot2.model.Localisation;
import org.springframework.data.jpa.repository.JpaRepository;





public interface LocalisationRepository extends JpaRepository<Localisation, Long> {
   
}