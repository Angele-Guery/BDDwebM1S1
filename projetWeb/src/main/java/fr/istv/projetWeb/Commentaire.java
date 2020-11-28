package fr.istv.projetWeb;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

	
@Entity
public class Commentaire {
	@Id @GeneratedValue(strategy=GenerationType.AUTO) //Automatique
	private int idCom;
	private String message;
	@ManyToOne
	private Developpeur auteur;
	@JsonFormat(pattern="dd-MM-yyyy")
	private Date date;
	@ManyToOne
	private Bug bug;
}
