package com.tbh.backend.mappers;

import com.tbh.backend.dto.InstanceDTO;
import com.tbh.backend.entity.Instance;
import org.springframework.stereotype.Component;

@Component
public class InstanceMapper {


    public InstanceDTO mapToDTO(Instance instance) {
        return new InstanceDTO(
                instance.getId(),
                instance.getIp(),
                instance.getPort()
        );
    }


    public Instance mapToEntity(InstanceDTO instanceDTO) {
        return new Instance(
                instanceDTO.getId(),
                instanceDTO.getIp(),
                instanceDTO.getPort()
        );
    }


}
