package fr.istv.projetWeb.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import fr.istv.projetWeb.Bug;

public interface BugRepository extends JpaRepository<Bug, Integer>{
	Optional<Bug> findById(Integer id);
	
	@Query("SELECT b FROM Bug b WHERE b.avancement = 'TODO'")
	List<Bug> findTODObugs();
	
	@Query("SELECT b FROM Bug b WHERE b.avancement = 'TERMINE'")
	List<Bug> findTerminebugs();
	
	@Query("SELECT b FROM Bug b WHERE b.avancement = 'EN COURS'")
	List<Bug> findEnCoursbugs();
	
	@Query("SELECT b FROM Bug b WHERE b.titre LIKE %?1%")
	List<Bug> findBugByTitre(String titre);
	
	
	
	
}
