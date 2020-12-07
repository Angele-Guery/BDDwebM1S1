package fr.istv.projetWeb;


import java.util.List;
import java.util.Optional;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


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
public class Developpeur {
	@Id @GeneratedValue(strategy=GenerationType.AUTO) //Automatique
	private int idDev;
	private String nom;
	private String avatar;
	@OneToMany
	private List<Commentaire> commentaire;
	@OneToMany
	private List<Bug> bug;
	
	public void addBug(Optional<Bug> bug) {
		this.bug.add(bug.get());
	}
}
