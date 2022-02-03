package ma.ensa.converter;

import ma.ensa.dto.CurrentAgentDTO;
import ma.ensa.model.CurrentAgent;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.springframework.stereotype.Component;

@Component
public class CurrentAgentConverter extends AbstractConverter<CurrentAgent, CurrentAgentDTO> {

    private final ModelMapper modelMapper;

    public CurrentAgentConverter(ModelMapper modelMapper) {
        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);
        this.modelMapper = modelMapper;
    }

    @Override
    public CurrentAgent convertToDM(CurrentAgentDTO currentAgentDTO) {
        return modelMapper.map(currentAgentDTO, CurrentAgent.class);
    }

    @Override
    public CurrentAgentDTO convertToDTO(CurrentAgent currentAgent) {
        return modelMapper.map(currentAgent, CurrentAgentDTO.class);
    }
}