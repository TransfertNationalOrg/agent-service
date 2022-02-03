package ma.ensa.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "client-service")
public interface CompteFeign {
    @PostMapping("/compte/")
    CompteDTO save(@RequestBody CompteDTO compteDTO);

    @PutMapping("/compte/")
    ClientDTO update(@RequestBody CompteDTO compteDTO);

    @GetMapping("/client/")
    List<CompteDTO> getAllCompte();

    @GetMapping("/client/{id}")
    CompteDTO getCompteById(@PathVariable("id") Long id);
}
