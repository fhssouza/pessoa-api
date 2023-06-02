package com.souzatech.pessoaapi.api.controller;

import com.souzatech.pessoaapi.api.controller.request.PessoaRequest;
import com.souzatech.pessoaapi.api.controller.response.PessoaResponse;
import com.souzatech.pessoaapi.domain.model.Pessoa;
import com.souzatech.pessoaapi.domain.service.CadastroPessoaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/pessoa")
public class PessoaController {

    @Autowired
    private CadastroPessoaService cadastroPessoaService;

    @Autowired
    private ModelMapper mapper;

    @PostMapping(value = "/cadastrar")
    public ResponseEntity<PessoaResponse> save(@RequestBody PessoaRequest pessoaRequest) {
        Pessoa pessoa = mapper.map(pessoaRequest, Pessoa.class);
        PessoaResponse pessoaResponse = mapper.map(cadastroPessoaService.salvar(pessoa), PessoaResponse.class);
        return ResponseEntity.ok(pessoaResponse);
    }

}
