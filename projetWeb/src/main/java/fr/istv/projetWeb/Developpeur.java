package fr.istv.projetWeb;


import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Developpeur {
	@Id @GeneratedValue(strategy=GenerationType.AUTO) //Automatique
	private int idDev;
	private String nom;
	private String avatar;
	@OneToMany
	private List<Commentaire> commentaire;
	@OneToMany
	private List<Bug> bug;
	
}
