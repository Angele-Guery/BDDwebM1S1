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
public class CommentairesController {
	
	@Autowired
    CommentaireRepository commentairesRepository;
	BugRepository bugsRepository;
	DeveloppeurRepository developpeursRepository;
	
	@GetMapping("commentaires")
	public List<Commentaire> getAllCommentaires(){
		return commentairesRepository.findAll();
	}
	public List<Bug> getAllBugs(){
		return bugsRepository.findAll();
	}
	public List<Developpeur> getAllDeveloppeurs(){
		return developpeursRepository.findAll();
	}
	
	@PostMapping("commentaires")
	public Commentaire createCommentaire(@Validated @RequestBody CreateCommentaire commentaire) { 
	    return commentairesRepository.save(
	        Commentaire
	        .builder()
	        .message(commentaire.getMessage())
	        .auteur(commentaire.getAuteur())
	        .date(commentaire.getDate())
	        .bug(commentaire.getBug())
            .build()
	    );
	}
	
	
	@PostMapping("commentaires")
	public Commentaire ajoutCommentaire(@Validated @RequestBody CreateCommentaire commentaire, CreateBug bug, CreateDeveloppeur developpeur) { 
	    commentairesRepository.save(
	        Commentaire
	        .builder()
	        .message(commentaire.getMessage())
	        .auteur(developpeur)
	        .date(commentaire.getDate())
	        .bug(bug)
            .build()
	    );
	    /*bugsRepository.save(
		        Bug
		        .builder()
		        .titre(bug.getTitre())
		        .description(bug.getDescription())
		        .priorite(bug.getPriorite())
		        .avancement(bug.getAvancement())
		        .dateCreation(bug.getDateCreation())
		        .developpeur(developpeur)
		        .commentaire(List<Commentaire>.add(commentaire))
	            .build()
	    );
	    developpeursRepository.save(
		    	Developpeur
		        .builder()
		        .nom(developpeur.getNom())
		        .avatar(developpeur.getAvatar())
		        .commentaire(List<Commentaire>.add(commentaire))
		        .bug(List<Bug>.add(bug))
	            .build()
	    );
	    */
	    
	    
	}
	
	
	
}
