package ma.ensa.repository;

import ma.ensa.model.CurrentAgent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CurrentAgentRepository extends JpaRepository<CurrentAgent, Long> {
}
