package ma.ensa.converter;

import ma.ensa.dto.AgentDTO;
import ma.ensa.model.Agent;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.springframework.stereotype.Component;

@Component
public class AgentConverter extends AbstractConverter<Agent, AgentDTO> {

    private final ModelMapper modelMapper;

    public AgentConverter(ModelMapper modelMapper) {
        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);
        this.modelMapper = modelMapper;
    }

    @Override
    public Agent convertToDM(AgentDTO agentDTO) {
        return modelMapper.map(agentDTO, Agent.class);
    }

    @Override
    public AgentDTO convertToDTO(Agent agent) {
        return modelMapper.map(agent, AgentDTO.class);
    }
}