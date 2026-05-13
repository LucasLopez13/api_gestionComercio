package com.gyl.api_gestionComercio.mapper;

import com.gyl.api_gestionComercio.dto.request.ClienteRequestDto;
import com.gyl.api_gestionComercio.dto.request.updates.ClienteUpdateDto;
import com.gyl.api_gestionComercio.dto.response.ClienteResponseDto;
import com.gyl.api_gestionComercio.entity.Cliente;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    Cliente toEntity(ClienteRequestDto requestDTO);

    ClienteResponseDto toResponseDTO(Cliente cliente);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "idCliente", ignore = true)
    @Mapping(target = "ventas", ignore = true)
    void updateEntityFromDTO(ClienteUpdateDto requestDTO, @MappingTarget Cliente cliente);
}
