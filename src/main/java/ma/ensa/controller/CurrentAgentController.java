package ma.ensa.controller;

import lombok.Data;
import ma.ensa.converter.CurrentAgentConverter;
import ma.ensa.dto.AgentDTO;
import ma.ensa.model.CurrentAgent;
import ma.ensa.repository.CurrentAgentRepository;
import ma.ensa.service.CurrentAgentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("currentAgent")
@Data
public class CurrentAgentController {

    private final CurrentAgentService currentAgentService;
    private final CurrentAgentRepository currentAgentRepository;
    private final CurrentAgentConverter currentAgentConverter;

    @PutMapping("/")
    public ResponseEntity<?> update(@RequestBody AgentDTO agentDTO) throws Exception {
        if (agentDTO == null)
            return ResponseEntity.badRequest().body("The provided agent is not valid");
        CurrentAgent currentAgent = currentAgentRepository.findById(1L).get();
        currentAgent.setTheId(agentDTO.getId());
        return ResponseEntity.ok().body(currentAgentConverter.convertToDTO(currentAgentService.update(currentAgent)));
    }

    @GetMapping("/")
    public ResponseEntity<?> getCurrentAgentId(){
        return ResponseEntity.ok().body(currentAgentConverter.convertToDTO(currentAgentRepository.findById(1L).get()));
    }

}
