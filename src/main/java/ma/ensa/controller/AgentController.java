package ma.ensa.controller;

import lombok.Data;
import ma.ensa.Transfert.TransfertDTO;
import ma.ensa.Transfert.TransfertFeign;
import ma.ensa.converter.AgentConverter;
import ma.ensa.converter.CurrentAgentConverter;
import ma.ensa.dto.AgentDTO;
import ma.ensa.exception.NotFoundException;
import ma.ensa.model.Credentials;
import ma.ensa.model.CurrentAgent;
import ma.ensa.repository.AgentRepository;
import ma.ensa.repository.CurrentAgentRepository;
import ma.ensa.service.AgentService;
import ma.ensa.service.CurrentAgentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("agent")
@Data
public class AgentController {

    private final AgentService agentService;
    private final AgentConverter agentConverter;
    private final TransfertFeign transfertFeign;
    private final CurrentAgentRepository currentAgentRepository;
    private final CurrentAgentService currentAgentService;
    private  final CurrentAgentConverter currentAgentConverter;

    final AgentRepository agentRepository;

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

    //Get all transferts by agent from transfert-service
    @GetMapping("/allTransferts/{id}")
    public List<TransfertDTO> getAllTransfertsByAgent(@PathVariable("id") Long id){
        return transfertFeign.getTransfertsByAgent(id);
    }

    //Get agent by id
    @GetMapping("/{id}")
    public AgentDTO getAgentById(@PathVariable("id") Long id){
        return agentConverter.convertToDTO(agentRepository.getById(id));
    }

    //Authentification
    @GetMapping("/login")
    public ResponseEntity<?> login(@RequestBody Credentials credentials) throws NotFoundException {
        if (credentials == null)
            return ResponseEntity.badRequest().body("The provided credentials is not valid");
        AgentDTO agentDTOFromDB = agentConverter.convertToDTO(agentService.findByEmail(credentials.getEmail()));
        if (agentDTOFromDB == null){
            return ResponseEntity.badRequest().body("The agent with the provided email does not exist");
        }
        if (!credentials.getPassword().equals(agentDTOFromDB.getPassword()))
            return ResponseEntity.badRequest().body("password is wrong");
        CurrentAgent currentAgent = currentAgentRepository.findById(1L).get();
        currentAgent.setTheId(agentDTOFromDB.getId());
        return ResponseEntity.ok().body(currentAgentConverter.convertToDTO(currentAgentService.update(currentAgent)));
    }

    //Get current agent
    @GetMapping("/")
    public ResponseEntity<?> getCurrentAgent(){
        return ResponseEntity.ok().body(currentAgentConverter.convertToDTO(currentAgentRepository.findById(1L).get()));
    }

}
