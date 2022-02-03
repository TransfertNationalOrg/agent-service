package ma.ensa.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "client-service")
public interface ClientFeign {
    @PostMapping("/client/")
    ClientDTO save(@RequestBody ClientDTO clientDTO);

    @PutMapping("/client/")
    ClientDTO update(@RequestBody ClientDTO clientDTO);
}
