package fr.istv.projetWeb.controller;

import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
public class BugsController {

	@Autowired
    BugRepository bugsRepository;
	
	@Autowired
	DeveloppeurRepository devRepository;

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
	        /*.developpeur(bug.getDeveloppeur())
	        .commentaire(bug.getCommentaire())*/
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

	@GetMapping("bugs/date")
	public List<Bug> getBugByDate(@RequestParam("debut") @DateTimeFormat(pattern = "dd-MM-yyyy") Date debut, 
			@RequestParam("fin") @DateTimeFormat(pattern = "dd-MM-yyyy") Date fin){
		return bugsRepository.findBugByDate(debut,fin);
	}
	@GetMapping("bugs/titre")
	public List<Bug> getBugTitre(@RequestParam String titre){
		return bugsRepository.findBugByTitre(titre);
	}
	
	
	@PutMapping("/bugs/{idbug}/devs/{iddev}")
	public ResponseEntity<?> addDev(@PathVariable("idbug") int idbug, @PathVariable("iddev") int iddev) {
		// Dans la base de données, c'est le bug qui stock le lien avec le développeur.
		// Donc on ajoute le développeur au bug.
		try {
			Bug bug = this.bugsRepository.findById(idbug).map(bugFound -> {
				// Le bug est trouvé on cherche le développeur
				Optional<Developpeur> devFound = this.devRepository.findById(iddev);
				if (devFound.isPresent()) {
					// Le développeur est trouvé
					bugFound.setDeveloppeur(devFound.get());
					return bugsRepository.save(bugFound);
				} else {
					// Le développeur n'est pas trouvé
					throw new RuntimeException("Developpeur non trouvé");
				}
				// Le bug n'est pas trouvé
			}).orElseThrow(() -> new RuntimeException("Bug non trouvé"));
			return ResponseEntity.ok(bug);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(e);
		}

	}
}
