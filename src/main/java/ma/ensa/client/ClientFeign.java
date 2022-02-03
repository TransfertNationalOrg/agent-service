package ma.ensa.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "client-service")
public interface ClientFeign {
    @PostMapping("/client/")
    ClientDTO save(@RequestBody ClientDTO clientDTO);

    @PutMapping("/client/")
    ClientDTO update(@RequestBody ClientDTO clientDTO);

    @GetMapping("/client/")
    List<ClientDTO> getAll();

    @GetMapping("/client/{id}")
    ClientDTO getClientById(@PathVariable("id") Long id);

}
