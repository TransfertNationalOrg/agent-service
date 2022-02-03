package ma.ensa.service.impl;

import lombok.Data;
import ma.ensa.dto.AgentDTO;
import ma.ensa.exception.DuplicatedException;
import ma.ensa.exception.NotFoundException;
import ma.ensa.model.Agent;
import ma.ensa.repository.AgentRepository;
import ma.ensa.service.AgentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
public class AgentServiceImpl implements AgentService {

    final AgentRepository agentRepository;

    @Override
    public Agent save(Agent agent) throws DuplicatedException {
        Agent agentFromDB = agentRepository.findById(agent.getId()).orElse(null);
        if (agentFromDB != null)
            throw new DuplicatedException(agent.getId());
        return agentRepository.save(agent);
    }

    @Override
    public Agent update(Agent agent) throws NotFoundException {
        Agent agentFromDB = agentRepository.findById(agent.getId()).orElse(null);
        if (agentFromDB == null)
            throw new NotFoundException(agent.getId());
        agent.setId(agentFromDB.getId());
        return agentRepository.save(agent);
    }

    @Override
    public Long delete(Long id) throws NotFoundException {
        Agent agentFromDB = agentRepository.findById(id).orElse(null);
        if (agentFromDB == null)
            throw new NotFoundException(id);
        agentRepository.delete(agentFromDB);
        return id;
    }

    @Override
    public List<Agent> findAll() {
        return agentRepository.findAll();
    }

    @Override
    public Agent findByEmail(String email) {
        return agentRepository.findByEmail(email);
    }
}
