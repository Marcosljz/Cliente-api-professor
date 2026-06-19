package br.com.senac.clientes_api.services;

import br.com.senac.clientes_api.entidades.Estado;
import br.com.senac.clientes_api.repositorios.EstadoRepositorio;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EstadoService {

    private EstadoRepositorio estadoRepositorio;

    public EstadoService(EstadoRepositorio estadoRepositorio) {
        this.estadoRepositorio = estadoRepositorio;
    }

    public Estado listarPorId(Long id){
        if (id== null || id==0){
            throw new RuntimeException("estado não informado");
        }
        Optional<Estado> estadoRetorno= estadoRepositorio.findById(id);

        if (estadoRetorno.isPresent()){
            return estadoRetorno.get();
        }
        throw new RuntimeException("Estado não encontrado!");
    }
}
