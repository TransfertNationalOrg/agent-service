package ma.ensa.repository;

import ma.ensa.dto.AgentDTO;
import ma.ensa.model.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface AgentRepository extends JpaRepository<Agent, Long> {
    @Query("select agent from Agent agent where agent.email = :x")
    Agent findByEmail(@Param("x") String email);
}
