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
	
	@GetMapping("developpeurs")
	public List<Developpeur> getAllDeveloppeurs(){
		return developpeursRepository.findAll();
	}
	
	@PostMapping("developpeurs")
	public Developpeur createDeveloppeur(@Validated @RequestBody CreateDeveloppeur developpeur) { 
	    return developpeursRepository.save(
	    	Developpeur
	        .builder()
	        .nom(developpeur.getNom())
	        .avatar(developpeur.getAvatar())
	        .commentaire(developpeur.getCommentaire())
	        .bug(developpeur.getBug())
            .build()
	    );
	}
	
}
