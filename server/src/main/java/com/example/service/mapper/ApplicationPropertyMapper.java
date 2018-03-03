package com.example.service.mapper;

import com.example.domain.ApplicationProperty;
import com.example.service.dto.ApplicationPropertyDTO;
import com.example.service.dto.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity ApplicationProperty and its DTO ApplicationPropertyDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ApplicationPropertyMapper extends EntityMapper<ApplicationPropertyDTO, ApplicationProperty> {


}
