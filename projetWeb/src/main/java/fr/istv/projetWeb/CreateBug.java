package fr.istv.projetWeb;


import java.sql.Date;
import java.util.List;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateBug {
    private String titre;
	private String description;
	private String priorite;
	private String avancement;
	@JsonFormat(pattern="dd-MM-yyyy")
	private Date dateCreation;
	private Developpeur developpeur;
	private List<Commentaire> commentaire;
	
}



