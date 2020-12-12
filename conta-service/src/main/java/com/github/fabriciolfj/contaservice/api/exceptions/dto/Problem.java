package com.github.fabriciolfj.contaservice.api.exceptions.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Problem {

    private List<ResponseError> erros;
}
