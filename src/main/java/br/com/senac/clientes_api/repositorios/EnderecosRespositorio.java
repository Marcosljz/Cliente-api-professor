package br.com.senac.clientes_api.repositorios;

import br.com.senac.clientes_api.entidades.Enderecos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecosRespositorio extends JpaRepository<Enderecos, Long> {
}
