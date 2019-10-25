package com.eventosapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.eventosapp.models.Cliente;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Long>{

}
