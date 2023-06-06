package com.souzatech.pessoaapi.api.controller.response;

import com.souzatech.pessoaapi.domain.model.Endereco;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PessoaEnderecoResponse {

    private Long id;
    private String nome;
    private Date dataNascimento;

    private List<Endereco> enderecos = new ArrayList<>();
}
