package ma.ensa.controller;

import lombok.Data;
import ma.ensa.Transfert.TransfertDTO;
import ma.ensa.Transfert.TransfertFeign;
import ma.ensa.beneficiaire.BeneficiaireDTO;
import ma.ensa.beneficiaire.BeneficiaireFeign;
import ma.ensa.client.ClientDTO;
import ma.ensa.client.ClientFeign;
import ma.ensa.client.CompteDTO;
import ma.ensa.client.CompteFeign;
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
    private final CompteFeign compteFeign;
    private final ClientFeign clientFeign;
    private final BeneficiaireFeign beneficiaireFeign;

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

    //GESTION DES CLIENTS ET LEUR COMPTE
    //Get current agent
    @GetMapping("/currentAgent")
    public ResponseEntity<?> getCurrentAgent(){
        return ResponseEntity.ok().body(currentAgentConverter.convertToDTO(currentAgentRepository.findById(1L).get()));
    }

    //Add a client
    @PostMapping("/client/")
    public ClientDTO saveClient(@RequestBody ClientDTO clientDTO){
        return clientFeign.save(clientDTO);
    }

    //Update a client
    @PutMapping("/client/")
    public ClientDTO updateClient(@RequestBody ClientDTO clientDTO){
        return clientFeign.update(clientDTO);
    }

    @GetMapping("/client/")
    public List<ClientDTO> getAllClients(){
        return clientFeign.getAll();
    }

    @GetMapping("/client/{id}")
    public ClientDTO getClientById(@PathVariable("id") Long id){
        return clientFeign.getClientById(id);
    }


    //Add a compte
    @PostMapping("/compte/")
    public CompteDTO saveCompte(@RequestBody CompteDTO compteDTO){
        return compteFeign.save(compteDTO);
    }

    //Update a compte
    @PutMapping("/compte/")
    public ClientDTO updateCompte(@RequestBody CompteDTO compteDTO){
        return compteFeign.update(compteDTO);
    }

    //Get compte by id
    @GetMapping("/compte/{id}")
    public CompteDTO getCompteById(@PathVariable("id") Long id){
        return compteFeign.getCompteById(id);
    }

    //GESTION DES BENEFICIAIRES
    //Add a beneficiaire
    @PostMapping("/beneficiaire/")
    public BeneficiaireDTO saveBeneficiaire(@RequestBody BeneficiaireDTO beneficiaireDTO){
        return beneficiaireFeign.save(beneficiaireDTO);
    }
    //Update a beneficiaire
    @PutMapping("/beneficiaire/")
    public BeneficiaireDTO udpateBenefiaire(@RequestBody BeneficiaireDTO beneficiaireDTO){
        return beneficiaireFeign.update(beneficiaireDTO);
    }
    //Get beneficiaire by id
    @GetMapping("/beneficiaire/{id}")
    public BeneficiaireDTO getBeneficiaireById(@PathVariable("id") Long id){
        return beneficiaireFeign.getBeneficiaireById(id);
    }


}
