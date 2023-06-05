package com.souzatech.pessoaapi.domain.service;

import com.souzatech.pessoaapi.api.controller.request.PessoaNovoEnderecoRequest;
import com.souzatech.pessoaapi.domain.exception.EntidadeNaoEncontradaException;
import com.souzatech.pessoaapi.domain.model.Cidade;
import com.souzatech.pessoaapi.domain.model.Endereco;
import com.souzatech.pessoaapi.domain.model.Pessoa;
import com.souzatech.pessoaapi.domain.repository.EnderecoRepository;
import com.souzatech.pessoaapi.domain.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastroPessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    public Pessoa salvar(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    public Pessoa findById(Long id) {
        return pessoaRepository.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException(
                "Não foi possível localizar ID: " + id + ", Tipo: " + Pessoa.class.getName()));
    }

    public Pessoa novoEndereco(Long pessoaId, PessoaNovoEnderecoRequest pessoaNovoEnderecoRequest) {
        Pessoa pessoa = findById(pessoaId);

        Endereco endereco = new Endereco();
        endereco.setPessoa(pessoa);
        endereco.setLogradouro(pessoaNovoEnderecoRequest.getLogradouro());
        endereco.setCep(pessoaNovoEnderecoRequest.getCep());
        endereco.setNumero(pessoaNovoEnderecoRequest.getNumero());
        endereco.setCidade(new Cidade(pessoaNovoEnderecoRequest.getCidadeId(), null, null));

        pessoa.getEnderecos().add(endereco);

        enderecoRepository.saveAll(pessoa.getEnderecos());

        return pessoa;

    }
}
