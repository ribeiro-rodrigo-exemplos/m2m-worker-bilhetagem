package br.com.m2msolutions.workerbilhetagem.features.cliente;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Long> {
}
