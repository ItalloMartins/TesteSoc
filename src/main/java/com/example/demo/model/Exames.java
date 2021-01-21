package com.example.demo.model;

import lombok.*;

import java.time.OffsetDateTime;
import java.util.Date;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Exames {

    private long id;
    private String nomePaciente;
    private String nomeExame;

    private long dataExame;
    private String resultadoExame;



}
