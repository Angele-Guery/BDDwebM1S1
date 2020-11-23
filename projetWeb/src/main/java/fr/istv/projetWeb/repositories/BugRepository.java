package fr.istv.projetWeb.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import fr.istv.projetWeb.Bug;

public interface BugRepository extends JpaRepository<Bug, Integer>{
	Optional<Bug> findById(Integer id);
	
	
}
