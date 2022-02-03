package ma.ensa.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "client-service")
public interface CompteFeign {
    @PostMapping("/compte/")
    CompteDTO save(@RequestBody CompteDTO compteDTO);

    @PutMapping("/compte/")
    ClientDTO update(@RequestBody CompteDTO compteDTO);
}
