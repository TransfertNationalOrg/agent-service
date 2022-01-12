package ma.ensa.repository;

import ma.ensa.model.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface AgentRepository extends JpaRepository<Agent, Long> {
}
