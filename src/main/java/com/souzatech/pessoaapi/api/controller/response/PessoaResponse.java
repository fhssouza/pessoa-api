package com.souzatech.pessoaapi.api.controller.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class PessoaResponse {

    private Long id;
    private String nome;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dataNascimento;
}
