package fr.istv.projetWeb;

import java.sql.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Bug {
	@Id @GeneratedValue(strategy=GenerationType.AUTO) //Automatique
	private int idBug;
	private String titre;
	private String description;
	private String priorite;
	private String avancement;
	private Date dateCreation;
	@ManyToOne
	private Developpeur developpeur;
	@OneToMany
	private List<Commentaire> commentaire;
}
