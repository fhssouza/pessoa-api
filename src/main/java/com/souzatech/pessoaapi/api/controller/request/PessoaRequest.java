package com.souzatech.pessoaapi.api.controller.request;

import lombok.Data;

import java.util.Date;

@Data
public class PessoaRequest {

    private String nome;

    private Date dataNascimento;
}
