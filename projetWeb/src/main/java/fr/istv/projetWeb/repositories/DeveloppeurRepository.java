package fr.istv.projetWeb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import fr.istv.projetWeb.Developpeur;


public interface DeveloppeurRepository extends JpaRepository<Developpeur, Integer>{
	Optional<Developpeur> findById(Integer id);
}
