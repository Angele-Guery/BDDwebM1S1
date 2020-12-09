package fr.istv.projetWeb.controller;

import org.springframework.web.bind.annotation.RestController;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	@PostMapping("commentaires/{idbug}/{iddev}")
	public ResponseEntity<?> createCommentaire(@Validated @RequestBody CreateCommentaire commentaire, @PathVariable("idbug") int idbug, @PathVariable("iddev") int iddev) {
		Bug bug = this.bugsRepository.findById(idbug).get();
		Developpeur developpeur = this.developpeursRepository.findById(iddev).get();
		if(developpeur != null && bug != null) {
			 commentairesRepository.save(
				        Commentaire
				        .builder()
				        .message(commentaire.getMessage())
				        .auteur(developpeur)
				        .date(commentaire.getDate())
				        .bug(bug)
			            .build()
					 );
			 return ResponseEntity.ok(commentaire);
		}
		else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("bug ou/et developpeur inexistant");
		}
	    	
	}
	
	
	
}
