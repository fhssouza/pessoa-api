package com.souzatech.pessoaapi.api.controller.response;

import lombok.Data;

import java.util.Date;

@Data
public class PessoaResponse {

    private Long id;
    private String nome;
    private Date dataNascimento;
}
