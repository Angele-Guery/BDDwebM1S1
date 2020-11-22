package fr.istv.projetWeb;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

	
@Entity
public class Commentaire {
	@Id @GeneratedValue(strategy=GenerationType.AUTO) //Automatique
	private int idCom;
	private String message;
	@ManyToOne
	private Developpeur auteur;
	private Date date;
	@ManyToOne
	private Bug bug;
}
