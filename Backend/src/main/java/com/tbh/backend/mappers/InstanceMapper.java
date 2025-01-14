package com.tbh.backend.mappers;

import com.tbh.backend.dto.IntanceDTO;
import com.tbh.backend.entity.Instance;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.Mapping;

@Component
public class InstanceMapper {


    public IntanceDTO mapToDTO(Instance instance) {
        return new IntanceDTO(
                instance.getId(),
                instance.getIp(),
                instance.getPort()
        );
    }


    public Instance mapToEntity(IntanceDTO intanceDTO) {
        return new Instance(
                intanceDTO.getId(),
                intanceDTO.getIp(),
                intanceDTO.getPort()
        );
    }


}
