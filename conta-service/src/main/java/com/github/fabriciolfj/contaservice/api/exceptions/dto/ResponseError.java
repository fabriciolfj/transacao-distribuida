package com.github.fabriciolfj.contaservice.api.exceptions.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseError {

    private OffsetDateTime data;
    private String mensagem;
}
