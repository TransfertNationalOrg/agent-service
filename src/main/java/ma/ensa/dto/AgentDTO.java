package ma.ensa.dto;

import lombok.Data;
import ma.ensa.model.enumer.IDENTITE;
import ma.ensa.model.enumer.PAYS;
import ma.ensa.model.enumer.ROLE;
import ma.ensa.model.enumer.TITRE;

import java.util.Date;

@Data
public class AgentDTO {
    //mettre les annotations notNull et notBlank

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
    protected String password;
    protected String matricule;
    protected ROLE role;
    protected double solde;
}
