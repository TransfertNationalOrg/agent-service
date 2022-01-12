package ma.ensa.model;

import lombok.Data;
import ma.ensa.model.enumer.IDENTITE;
import ma.ensa.model.enumer.PAYS;
import ma.ensa.model.enumer.TITRE;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import java.util.Set;

@Data
@Entity
public class Agent {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    protected Long id;

    protected TITRE titre;
    protected String prenom;
    protected String nom;
    protected IDENTITE identite;
    protected PAYS pays;
    protected Long numIdentite;
    protected Date expiration;
    protected Date naissance;
    protected String profession;
    protected PAYS nationalite;
    protected String adressePays;
    protected String adresseLegale;
    protected String ville;
    protected Long gsm;
    protected String email;
    protected String matricule;


}
