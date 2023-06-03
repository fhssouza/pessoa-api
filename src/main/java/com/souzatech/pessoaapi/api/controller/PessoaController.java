package com.souzatech.pessoaapi.api.controller;

import com.souzatech.pessoaapi.api.controller.request.PessoaRequest;
import com.souzatech.pessoaapi.api.controller.response.PessoaResponse;
import com.souzatech.pessoaapi.domain.exception.EntidadeNaoEncontradaException;
import com.souzatech.pessoaapi.domain.model.Pessoa;
import com.souzatech.pessoaapi.domain.repository.PessoaRepository;
import com.souzatech.pessoaapi.domain.service.CadastroPessoaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/pessoa")
public class PessoaController {

    @Autowired
    private CadastroPessoaService cadastroPessoaService;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private ModelMapper mapper;

    @PostMapping(value = "/cadastrar")
    public ResponseEntity<PessoaResponse> save(@RequestBody PessoaRequest pessoaRequest) {
        Pessoa pessoa = mapper.map(pessoaRequest, Pessoa.class);
        PessoaResponse pessoaResponse = mapper.map(cadastroPessoaService.salvar(pessoa), PessoaResponse.class);
        return ResponseEntity.ok(pessoaResponse);
    }

    @PutMapping("editar/{pessoaId}")
    public ResponseEntity<?> editar(@PathVariable Long pessoaId, @RequestBody PessoaRequest pessoaRequest) {
        try {
            Pessoa pessoaAtual = pessoaRepository.findById(pessoaId).orElse(null);

            if (pessoaAtual != null) {
                BeanUtils.copyProperties(pessoaRequest, pessoaAtual, "id", "enderecos");
                pessoaAtual = cadastroPessoaService.salvar(pessoaAtual);

                return ResponseEntity.ok(pessoaAtual);
            }

            return ResponseEntity.notFound().build();

        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
}