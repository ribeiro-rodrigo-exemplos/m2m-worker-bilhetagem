package br.com.m2msolutions.workerbilhetagem.features.cliente;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface ClienteRjConsultoresRepository extends CrudRepository<ClienteRjConsultores, Long> {
}
