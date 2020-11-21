package fr.istv.projetWeb;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Bug {
	@Id
	private int id;
	private String name;
}
