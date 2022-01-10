package ma.ensa.controller;

import lombok.Data;
import ma.ensa.converter.AgentConverter;
import ma.ensa.dto.AgentDTO;
import ma.ensa.service.AgentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("agent")
@Data
public class AgentController {

    final AgentService agentService;
    final AgentConverter agentConverter;

    @PostMapping("/")
    public ResponseEntity<?> save( @RequestBody AgentDTO agentDTO) throws Exception {
        if (agentDTO == null)
            return ResponseEntity.badRequest().body("The provided agent is not valid");
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(agentConverter.convertToDTO(agentService.save(agentConverter.convertToDM(agentDTO))));
    }

    @PutMapping("/")
    public ResponseEntity<?> update(@RequestBody AgentDTO agentDTO) throws Exception {
        if (agentDTO == null)
            return ResponseEntity.badRequest().body("The provided agent is not valid");
        return ResponseEntity
                .ok().body(agentConverter.convertToDTO(agentService.update(agentConverter.convertToDM(agentDTO))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) throws Exception {
        if(id == null)
            return ResponseEntity.badRequest().body("The provided agent's id is not valid");
        return ResponseEntity.ok().body("Agent [" + agentService.delete(id) + "] deleted successfully.");
    }

    @GetMapping("/")
    public ResponseEntity<List<AgentDTO>> findAll() {
        return ResponseEntity.ok().body(agentConverter.convertToDTOs(agentService.findAll()));
    }

}
