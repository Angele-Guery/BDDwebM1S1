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

	/**
	 * récupère la liste de tous les bugs
	 * @return liste des bugs
	 */
	@GetMapping("bugs")
	public List<Bug> getAllBugs(){
		return bugsRepository.findAll();
	}

	/**
	 * ajoute un bug dans la base de données
	 * @param bug
	 * @return le bug ajouté
	 */
	@PostMapping("bugs")
	public Bug createBug(@Validated @RequestBody CreateBug bug) {
	    return bugsRepository.save(
	        Bug
	        .builder()
	        .titre(bug.getTitre())
	        .description(bug.getDescription())
	        .priorite(bug.getPriorite())
	        .avancement("TODO")
	        .dateCreation(bug.getDateCreation())
	        /*.developpeur(bug.getDeveloppeur())
	        .commentaire(bug.getCommentaire())*/
            .build()
	    );
	}

	/**
	 * supprime un bug selectionné par son ID
	 * @param id : l'ID du bug a supprimer
	 */
	@DeleteMapping("bugs/{id}")
	public void deleteBugById(@PathVariable Integer id) {
	    bugsRepository.deleteById(id);
	}

	/**
	 * modifie le contenu d'un bug en fonction de son ID
	 * @param bug
	 * @param id : l'ID du bug qu'on veut modifier
	 * @return le bug modifié
	 */
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

	/**
	 * récupère un bug en fonction de son ID
	 * @param id : l'ID du bug qu'on veut récupérer
	 * @return le bug récupéré
	 */
	@GetMapping("bugs/{id}")
	public Optional<Bug> getBugById(@PathVariable Integer id){
		return bugsRepository.findById(id);
	}

	/**
	 * récupère les bug qui sont en TO DO
	 * @return les bugs en TO DO
	 */
	@GetMapping("bugs/todo")
	public List<Bug> getBugToDo(){
		return bugsRepository.findTODObugs();
	}

	/**
	 * récupère les bug qui sont en EN COURS
	 * @return les bugs EN COURS
	 */
	@GetMapping("bugs/encours")
	public List<Bug> getBugEnCours(){
		return bugsRepository.findEnCoursbugs();
	}

	/**
	 * récupère les bug qui sont TERMINE
	 * @return les bugs en TERMINE
	 */
	@GetMapping("bugs/termine")
	public List<Bug> getBugTermine(){
		return bugsRepository.findTerminebugs();
	}

	/**
	 * récupère les bugs selon un intervalle de date
	 * @param debut : date de début de l'intervalle
	 * @param fin : date de fin de l'intervalle
	 * @return les bugs compris entre les dates données en entrée
	 */
	@GetMapping("bugs/date")
	public List<Bug> getBugByDate(@RequestParam("debut") @DateTimeFormat(pattern = "dd-MM-yyyy") Date debut, 
			@RequestParam("fin") @DateTimeFormat(pattern = "dd-MM-yyyy") Date fin){
		return bugsRepository.findBugByDate(debut,fin);
	}
	
	/**
	 * retourne les bugs en fonction du contenu de leur titre
	 * @param titre : le titre que l'on recherche
	 * @return les bugs ayant le titre donné dans leur titre
	 */
	@GetMapping("bugs/titre")
	public List<Bug> getBugTitre(@RequestParam String titre){
		return bugsRepository.findBugByTitre(titre);
	}
	
	/**
	 * associe un developpeur a un bug
	 * @param idbug : l'ID du bug
	 * @param iddev : l'ID du developpeur
	 * @return le bug modifié
	 */
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
