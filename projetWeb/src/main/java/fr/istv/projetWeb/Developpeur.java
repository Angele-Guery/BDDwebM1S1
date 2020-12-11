package fr.istv.projetWeb;


import java.util.List;
import java.util.Optional;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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
		  property = "idDev")
public class Developpeur {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY) //Automatique
	private int idDev;
	private String nom;
	private String avatar;
	@OneToMany(mappedBy="auteur")
	//@JsonManagedReference
	private List<Commentaire> commentaire;
	//@JsonManagedReference
	@OneToMany(mappedBy="developpeur")
	private List<Bug> bug;
	
	public void addBug(Optional<Bug> bug) {
		this.bug.add(bug.get());
	}
}
