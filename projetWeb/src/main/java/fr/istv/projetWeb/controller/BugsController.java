package fr.istv.projetWeb.controller;

import org.springframework.web.bind.annotation.RestController;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
public class BugsController {

	@Autowired
    BugRepository bugsRepository;
	
	@GetMapping("bugs")
	public List<Bug> getAllBugs(){
		return bugsRepository.findAll();
	}
	
	@PostMapping("bugs")
	public Bug createBug(@Validated @RequestBody CreateBug bug) { 
	    return bugsRepository.save(
	        Bug
	        .builder()
	        .titre(bug.getTitre())
	        .description(bug.getDescription())
	        .priorite(bug.getPriorite())
	        .avancement(bug.getAvancement())
	        .dateCreation(bug.getDateCreation())
	        .developpeur(bug.getDeveloppeur())
	        .commentaire(bug.getCommentaire())
            .build()
	    );
	}
	
	
	@DeleteMapping("bugs/{id}")
	  void deleteBug(@PathVariable Integer id) {
	    bugsRepository.deleteById(id);
	  }
	
	
	@PutMapping("/bugs/{id}")
	  Optional<Bug> replaceEmployee(@RequestBody CreateBug bug, @PathVariable int id) {

	    return bugsRepository.findById(id)
	      .map(Bug -> {
	        Bug.setTitre(bug.getTitre());
	        Bug.setDescription(bug.getDescription());
	        Bug.setPriorite(bug.getPriorite());
	        Bug.setAvancement(bug.getAvancement());
	        Bug.setDateCreation(bug.getDateCreation());
	        return bugsRepository.save(Bug);
	      });
	  }
}

