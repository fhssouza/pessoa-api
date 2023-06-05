package com.souzatech.pessoaapi.domain.repository;

import com.souzatech.pessoaapi.domain.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}
