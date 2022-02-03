package ma.ensa;

import lombok.Data;
import ma.ensa.model.CurrentAgent;
import ma.ensa.repository.CurrentAgentRepository;
import ma.ensa.service.CurrentAgentService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@Data
@SpringBootApplication
@EnableFeignClients
public class AgentServiceApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(AgentServiceApplication.class, args);
	}


	private final CurrentAgentService currentAgentService;
	private final CurrentAgentRepository currentAgentRepository;

	@Override
	public void run(String... args) throws Exception {
		if (currentAgentRepository.findAll().size()==0){
			CurrentAgent currentAgent = new CurrentAgent();
			currentAgent.setId(1L);
			currentAgentService.save(currentAgent);
		}
	}
}
