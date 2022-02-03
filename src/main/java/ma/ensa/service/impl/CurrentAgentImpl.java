package ma.ensa.service.impl;

import lombok.Data;
import ma.ensa.exception.DuplicatedException;
import ma.ensa.exception.NotFoundException;
import ma.ensa.model.CurrentAgent;
import ma.ensa.repository.CurrentAgentRepository;
import ma.ensa.service.CurrentAgentService;
import org.springframework.stereotype.Service;

@Data
@Service
public class CurrentAgentImpl implements CurrentAgentService {

    final CurrentAgentRepository agentRepository;

    @Override
    public CurrentAgent save(CurrentAgent currentAgent) throws DuplicatedException {
        CurrentAgent currentAgentFromDB =  agentRepository.findById(currentAgent.getId()).orElse(null);
        if (currentAgentFromDB != null)
            throw new DuplicatedException(currentAgent.getId());
        return agentRepository.save(currentAgent);
    }

    @Override
    public CurrentAgent update(CurrentAgent currentAgent) throws NotFoundException {
        CurrentAgent agentFromDB = agentRepository.findById(currentAgent.getId()).orElse(null);
        if (agentFromDB == null)
            throw new NotFoundException(currentAgent.getId());
        return agentRepository.save(currentAgent);
    }
}
