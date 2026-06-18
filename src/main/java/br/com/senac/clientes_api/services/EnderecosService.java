package br.com.senac.clientes_api.services;

import br.com.senac.clientes_api.dtos.ClientesFiltroDto;
import br.com.senac.clientes_api.dtos.ClientesRequestDto;
import br.com.senac.clientes_api.dtos.EnderecosRequestDto;
import br.com.senac.clientes_api.entidades.Clientes;
import br.com.senac.clientes_api.entidades.Enderecos;
import br.com.senac.clientes_api.repositorios.EnderecosRespositorio;

import java.util.List;
import java.util.Optional;

public class EnderecosService {

    private EnderecosRespositorio enderecosRespositorio;

    private ClientesService clientesService;

    public EnderecosService(EnderecosRespositorio enderecosRespositorio, ClientesService clientesService) {
        this.enderecosRespositorio = enderecosRespositorio;
        this.clientesService = clientesService;
    }

    public Enderecos criar(EnderecosRequestDto endereco) {

        Enderecos enderecoPersist =
                this.enderecoResquestDtoParaendereco(endereco);

        Clientes clienteRetorno=
                clientesService.listarPorId(endereco.getClienteId());

        enderecoPersist.setCliente(clienteRetorno);

        return enderecosRespositorio.save(enderecoPersist);

    }

    public List<Enderecos> listar() {
        return enderecosRespositorio.findAll();
    }


    public Enderecos atualizar(
            Long id,
            EnderecosRequestDto endereco) {
        if(enderecosRespositorio.existsById(id)) {
            Enderecos enderecoPersist =
                    this.enderecoResquestDtoParaendereco(endereco);
            enderecoPersist.setId(id);

            Clientes clienteretorno=
                    clientesService.listarPorId(endereco.getClienteId());
            enderecoPersist.setCliente(clienteretorno);

            return enderecosRespositorio.save(enderecoPersist);
        }

        throw new RuntimeException("Endereço não encontrado");
    }

    public void deletar(Long id) {
        if(enderecosRespositorio.existsById(id)) {
            enderecosRespositorio.deleteById(id);
        }

        throw new RuntimeException("Cliente não encontrado");
    }

    public Enderecos listarPorId(Long id) {
        Optional<Enderecos> retorno = enderecosRespositorio.findById(id);
        if(retorno.isPresent()) {
            return retorno.get();
        }

        throw new RuntimeException("Cliente não encontrado");
    }

    private Enderecos enderecoResquestDtoParaendereco(
            EnderecosRequestDto entrada) {

        Enderecos saida = new Enderecos();
        saida.setBairro(entrada.getBairro());
        saida.setCep(entrada.getCep());
        saida.setCidade(entrada.getCidade());
        saida.setEstado(entrada.getEstado());
        saida.setComplemento(entrada.getComplemento());
        saida.setRua(entrada.getRua());

        return saida;
    }
}
