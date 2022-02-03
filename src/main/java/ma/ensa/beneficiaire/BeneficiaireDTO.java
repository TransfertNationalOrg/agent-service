package ma.ensa.beneficiaire;

import lombok.Data;
import ma.ensa.model.enumer.PAYS;
import ma.ensa.model.enumer.TITRE;

@Data
public class BeneficiaireDTO {
    protected Long id;

    protected TITRE titre;
    protected String prenom;
    protected String nom;
    protected PAYS pays;
}
