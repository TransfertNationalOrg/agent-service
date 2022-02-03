package ma.ensa.service;

import ma.ensa.dto.AgentDTO;
import ma.ensa.exception.DuplicatedException;
import ma.ensa.exception.NotFoundException;
import ma.ensa.model.Agent;

import java.util.List;
public interface AgentService {

    Agent save(Agent agent) throws DuplicatedException;
    Agent update(Agent agent) throws NotFoundException;
    Long delete(Long id) throws NotFoundException;
    List<Agent> findAll();

    Agent findByEmail(String email);
}
