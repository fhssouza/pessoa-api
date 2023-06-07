package com.souzatech.pessoaapi.api.controller.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class PessoaRequest {

    private String nome;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dataNascimento;
}
