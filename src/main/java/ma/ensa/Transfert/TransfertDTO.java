package ma.ensa.Transfert;

import lombok.Data;

@Data
public class TransfertDTO {
    protected Long id;
    protected double montant;
    protected Long ref;
    protected double commission;
    protected String motif;
    protected Long idAgent;
    protected Long idClientt;
    protected Long idBeneficiaire;
}
