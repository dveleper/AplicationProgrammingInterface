package com.neoris.aplicationprogramminginterface.infrastructure.rest.mapper;

import com.neoris.aplicationprogramminginterface.domain.model.Cliente;
import com.neoris.aplicationprogramminginterface.infrastructure.entity.ClienteEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ClienteMapperImpl implements ClienteMapper {

    @Override
    public Cliente toCliente(ClienteEntity clienteEntity) {
        if ( clienteEntity == null ) {
            return null;
        }

        Cliente cliente = new Cliente();

        cliente.setNombre( clienteEntity.getNombre() );
        cliente.setGenero( clienteEntity.getGenero() );
        cliente.setEdad( clienteEntity.getEdad() );
        cliente.setIdentificacion( clienteEntity.getIdentificacion() );
        cliente.setDireccion( clienteEntity.getDireccion() );
        cliente.setTelefono( clienteEntity.getTelefono() );
        cliente.setClienteId( clienteEntity.getClienteId() );
        cliente.setContrasena( clienteEntity.getContrasena() );
        cliente.setEstado( clienteEntity.getEstado() );

        return cliente;
    }

    @Override
    public List<Cliente> toClientes(List<ClienteEntity> clienteList) {
        if ( clienteList == null ) {
            return null;
        }

        List<Cliente> list = new ArrayList<Cliente>( clienteList.size() );
        for ( ClienteEntity clienteEntity : clienteList ) {
            list.add( toCliente( clienteEntity ) );
        }

        return list;
    }

    @Override
    public ClienteEntity toClienteEntity(Cliente cliente) {
        if ( cliente == null ) {
            return null;
        }

        ClienteEntity clienteEntity = new ClienteEntity();

        clienteEntity.setIdentificacion( cliente.getIdentificacion() );
        clienteEntity.setNombre( cliente.getNombre() );
        clienteEntity.setGenero( cliente.getGenero() );
        clienteEntity.setEdad( cliente.getEdad() );
        clienteEntity.setDireccion( cliente.getDireccion() );
        clienteEntity.setTelefono( cliente.getTelefono() );
        clienteEntity.setClienteId( cliente.getClienteId() );
        clienteEntity.setContrasena( cliente.getContrasena() );
        clienteEntity.setEstado( cliente.getEstado() );

        return clienteEntity;
    }
}
