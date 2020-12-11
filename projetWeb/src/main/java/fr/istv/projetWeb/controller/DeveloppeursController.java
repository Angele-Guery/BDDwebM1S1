package fr.istv.projetWeb.controller;

import org.springframework.web.bind.annotation.RestController;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


import fr.istv.projetWeb.repositories.*;
import fr.istv.projetWeb.*;

@RestController
public class DeveloppeursController {
	
	@Autowired
    DeveloppeurRepository developpeursRepository;
	
	/**
	 * récupère la liste des developpeurs
	 * @return la liste des developpeurs
	 */
	@GetMapping("developpeurs")
	public List<Developpeur> getAllDeveloppeurs(){
		return developpeursRepository.findAll();
	}
	
	/**
	 * permet d'ajouter un developpeur dans la liste
	 * @param developpeur
	 * @return le developpeur ajouté
	 */
	@PostMapping("developpeurs")
	public Developpeur createDeveloppeur(@Validated @RequestBody CreateDeveloppeur developpeur) { 
	    return developpeursRepository.save(
	    	Developpeur
	        .builder()
	        .nom(developpeur.getNom())
	        .avatar("https://lesexpertsdurecouvrement.com/wp-content/uploads/2015/11/default-avatar-596x596.jpg")
	        /*.commentaire(developpeur.getCommentaire())
	        .bug(developpeur.getBug())*/
            .build()
	    );
	}
	
}
