package com.souzatech.pessoaapi.api.controller;

import com.souzatech.pessoaapi.api.controller.request.PessoaNovoEnderecoRequest;
import com.souzatech.pessoaapi.api.controller.request.PessoaRequest;
import com.souzatech.pessoaapi.api.controller.response.PessoaEnderecoResponse;
import com.souzatech.pessoaapi.api.controller.response.PessoaResponse;
import com.souzatech.pessoaapi.domain.exception.EntidadeNaoEncontradaException;
import com.souzatech.pessoaapi.domain.model.Pessoa;
import com.souzatech.pessoaapi.domain.repository.PessoaRepository;
import com.souzatech.pessoaapi.domain.service.CadastroPessoaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.ResponseEntity.ok;

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
        return ok(pessoaResponse);
    }

    @PutMapping("editar/{pessoaId}")
    public ResponseEntity<?> editar(@PathVariable Long pessoaId, @RequestBody PessoaRequest pessoaRequest) {
        try {
            Pessoa pessoaAtual = pessoaRepository.findById(pessoaId).orElse(null);

            if (pessoaAtual != null) {
                BeanUtils.copyProperties(pessoaRequest, pessoaAtual, "id", "enderecos");
                pessoaAtual = cadastroPessoaService.salvar(pessoaAtual);

                return ok(pessoaAtual);
            }

            return ResponseEntity.notFound().build();

        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @GetMapping("consultar/{pessoaId}")
    public ResponseEntity<PessoaResponse> consultar(@PathVariable Long pessoaId) {
        Pessoa pessoa = pessoaRepository.findById(pessoaId).orElse(null);

        if (pessoa != null) {
            PessoaResponse pessoaResponse = mapper.map(pessoa, PessoaResponse.class);
            return ok(pessoaResponse);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("listar")
    @ResponseStatus(HttpStatus.OK)
    public List<PessoaResponse> listar() {
        List<Pessoa> pessoa = pessoaRepository.findAll();
        return pessoa.stream()
                .map(p -> new ModelMapper().map(p, PessoaResponse.class))
                .collect(Collectors.toList());
    }

    @PostMapping(value = "/cadastrar/{pessoaId}/endereco")
    public ResponseEntity<Void> salvarEndereco(@RequestBody PessoaNovoEnderecoRequest pessoaNovoEnderecoRequest, @PathVariable Long pessoaId) {
        Pessoa pessoa = cadastroPessoaService.novoEndereco(pessoaId, pessoaNovoEnderecoRequest);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{pessoaId}").buildAndExpand(pessoa.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("enderecos/{pessoaId}")
    public ResponseEntity<PessoaEnderecoResponse> listaEnderecos(@PathVariable Long pessoaId) {
        Pessoa pessoa = pessoaRepository.findById(pessoaId).orElse(null);

        if (pessoa != null) {
            PessoaEnderecoResponse pessoaEnderecoResponse = mapper.map(pessoa, PessoaEnderecoResponse.class);
            return ok(pessoaEnderecoResponse);
        }
        return ResponseEntity.notFound().build();
    }
}


