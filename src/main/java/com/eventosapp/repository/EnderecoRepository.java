package com.eventosapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.eventosapp.models.Cliente;
import com.eventosapp.models.Endereco;

@Repository
public interface EnderecoRepository extends CrudRepository<Endereco, Long>{
	Endereco findEnderecoByIdCliente(Long idCliente);
}
