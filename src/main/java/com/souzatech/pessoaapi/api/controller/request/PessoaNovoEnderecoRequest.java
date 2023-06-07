package com.souzatech.pessoaapi.api.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PessoaNovoEnderecoRequest {

    private String logradouro;
    private String cep;
    private String numero;
    private Long cidadeId;
    private Boolean principal;

}
