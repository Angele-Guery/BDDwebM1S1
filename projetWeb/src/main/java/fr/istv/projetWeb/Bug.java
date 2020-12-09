package fr.istv.projetWeb;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

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
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "idBug")
public class Bug {
	@Id @GeneratedValue(strategy=GenerationType.AUTO) //Automatique
	private int idBug;
	private String titre;
	private String description;
	private String priorite;
	private String avancement;
	@JsonFormat(pattern="dd-MM-yyyy")
	private Date dateCreation;
	@ManyToOne
	//@JsonBackReference
	private Developpeur developpeur;
	@OneToMany(mappedBy="bug", orphanRemoval=true)
	//@JsonManagedReference
	private List<Commentaire> commentaire;
	
	
}
