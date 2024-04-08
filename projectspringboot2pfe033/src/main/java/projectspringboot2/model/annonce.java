package projectspringboot2.model;



import jakarta.persistence.Entity;

import java.util.Date;

import jakarta.persistence.*;

@Entity
@Table(name="annonce")

public class annonce {
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private int id;

private String adresse_depart;

private String adresse_destination;
private Date datelimite;
private String description;

@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "id_utilisateur")
private Utilisateur utilisateur;


public int getId() {
	return id;
}



public void setId(int id) {
	this.id = id;
}


public String getAdresse_depart() {
	return adresse_depart;
}



public void setAdresse_depart(String adresse_depart) {
	this.adresse_depart = adresse_depart;
}



public String getAdresse_destination() {
	return adresse_destination;
}



public void setAdresse_destination(String adresse_destination) {
	this.adresse_destination = adresse_destination;
}



public Date getDatelimite() {
	return datelimite;
}



public void setDatelimite(Date datelimite) {
	this.datelimite = datelimite;
}



public String getDescription() {
	return description;
}



public void setDescription(String description) {
	this.description = description;
}

}