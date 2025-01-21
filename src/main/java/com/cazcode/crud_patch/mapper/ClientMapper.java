package com.cazcode.crud_patch.mapper;

import com.cazcode.crud_patch.Client;
import com.cazcode.crud_patch.dto.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ClientMapper {
    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

    Cliente toCliente(Client client);

    Client toClient(Cliente cliente);
}
