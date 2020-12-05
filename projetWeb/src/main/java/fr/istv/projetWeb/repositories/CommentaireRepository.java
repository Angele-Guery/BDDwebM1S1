package fr.istv.projetWeb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import fr.istv.projetWeb.Commentaire;


public interface CommentaireRepository extends JpaRepository<Commentaire, Integer>{
	Optional<Commentaire> findById(Integer id);
}
