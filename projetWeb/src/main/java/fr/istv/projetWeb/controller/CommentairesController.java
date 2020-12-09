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
	
	@Autowired
	BugRepository bugsRepository;
	
	@Autowired
	DeveloppeurRepository developpeursRepository;
	
	@GetMapping("commentaires")
	public List<Commentaire> getAllCommentaires(){
		return commentairesRepository.findAll();
	}
	
	@PostMapping("commentaires/{idbug}/{iddev}")
	public ResponseEntity<?> createCommentaire(@Validated @RequestBody CreateCommentaire commentaire, @PathVariable("idbug") int idbug, @PathVariable("iddev") int iddev) {
		try {
			Bug bug = this.bugsRepository.findById(idbug).map(bugFound -> {return bugFound;}).orElseThrow(() -> new RuntimeException("Bug non trouvé"));
			Developpeur developpeur = this.developpeursRepository.findById(iddev).map(devFound -> {return devFound;}).orElseThrow(() -> new RuntimeException("Developpeur non trouvé"));
			return ResponseEntity.ok(commentairesRepository.save(
				        Commentaire
				        .builder()
				        .message(commentaire.getMessage())
				        .auteur(developpeur)
				        .date(commentaire.getDate())
				        .bug(bug)
			            .build()
					 ));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(e);
		}   	
	}
	
}
