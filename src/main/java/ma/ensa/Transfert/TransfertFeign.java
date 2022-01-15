package ma.ensa.Transfert;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="transfert-service")
public interface TransfertFeign {
    @GetMapping("/transfert/agent/{id}")
    List<TransfertDTO> getTransfertsByAgent(@PathVariable("id") Long id);
}
