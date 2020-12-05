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
	
	@GetMapping("commentaires")
	public List<Commentaire> getAllCommentaires(){
		return commentairesRepository.findAll();
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
	
}
