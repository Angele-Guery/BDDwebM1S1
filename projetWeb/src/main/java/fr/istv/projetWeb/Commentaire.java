package fr.istv.projetWeb;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Commentaire {
	@Id @GeneratedValue(strategy=GenerationType.AUTO) //Automatique
	private int idCom;
	private String message;
	@ManyToOne
	@JsonBackReference
	private Developpeur auteur;
	@JsonFormat(pattern="dd-MM-yyyy")
	private Date date;
	@ManyToOne
	@JsonBackReference
	private Bug bug;
}
