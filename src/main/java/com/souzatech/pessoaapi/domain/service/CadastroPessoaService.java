package com.souzatech.pessoaapi.domain.service;

import com.souzatech.pessoaapi.domain.model.Pessoa;
import com.souzatech.pessoaapi.domain.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastroPessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public Pessoa salvar(Pessoa pessoa){
        return pessoaRepository.save(pessoa);
    }

}
