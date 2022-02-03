package ma.ensa.beneficiaire;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name="transfert-service")
public interface BeneficiaireFeign {
    //Get all beneficiaires from beneficiaire-service
    @GetMapping("beneficiaire/")
    List<BeneficiaireDTO> findAll();

    //Get a beneficiaire by id from beneficiaire-service
    @GetMapping("beneficiaire/{idBeneficiaire}")
    BeneficiaireDTO getBeneficiaireById(@PathVariable("id") Long id);

    //Add beneficiaire
    @PostMapping("/beneficiaire/")
    BeneficiaireDTO save(@RequestBody BeneficiaireDTO beneficiaireDTO);

    //Update beneficiaire
    @PutMapping("/beneficiaire/")
    BeneficiaireDTO update(@RequestBody BeneficiaireDTO beneficiaireDTO);
}
