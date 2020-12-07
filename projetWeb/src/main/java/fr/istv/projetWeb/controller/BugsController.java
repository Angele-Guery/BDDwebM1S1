package fr.istv.projetWeb.controller;

import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
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
	public void deleteBugById(@PathVariable Integer id) {
	    bugsRepository.deleteById(id);
	}


	@PutMapping("/bugs/{id}")
	public Optional<Bug> replaceBugById(@RequestBody CreateBug bug, @PathVariable int id) {
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

	@GetMapping("bugs/{id}")
	public Optional<Bug> getBugById(@PathVariable Integer id){
		return bugsRepository.findById(id);
	}

	@GetMapping("bugs/todo")
	public List<Bug> getBugToDo(){
		return bugsRepository.findTODObugs();
	}

	@GetMapping("bugs/encours")
	public List<Bug> getBugEnCours(){
		return bugsRepository.findEnCoursbugs();
	}

	@GetMapping("bugs/termine")
	public List<Bug> getBugTermine(){
		return bugsRepository.findTerminebugs();
	}

	@GetMapping("bugs/date/'{datedebut}'/'{datefin}'")
	public List<Bug> getBugByDate(@PathVariable("datedebut") Date debut, @PathVariable("datefin") Date fin){
		return bugsRepository.findBugByDate(debut,fin);
	}
	@GetMapping("bugs/{titre}")
	public List<Bug> getBugTitre(@PathVariable String titre){
		return bugsRepository.findBugByTitre(titre);
	}
}
