package fr.istv.projetWeb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

import fr.istv.projetWeb.Bug;
import fr.istv.projetWeb.Commentaire;


public interface CommentaireRepository extends JpaRepository<Commentaire, Integer>{
	Optional<Commentaire> findById(Integer id);
	
}
