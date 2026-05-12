package com.gyl.api_gestionComercio.mapper;


import com.gyl.api_gestionComercio.dto.request.LoginRequestDTO;
import com.gyl.api_gestionComercio.dto.request.RegistroRequestDTO;
import com.gyl.api_gestionComercio.entity.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "rol", constant = "USER")
    @Mapping(target = "password", source = "encryptedPassword")
    Usuario toEntity(RegistroRequestDTO dto, String encryptedPassword);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "email", ignore = true)
    @Mapping(target = "rol", ignore = true)
    void actualizarEntidad(@MappingTarget Usuario usuario, LoginRequestDTO dto);

}