package com.github.fabriciolfj.contaservice.api.mappers;

import com.github.fabriciolfj.contaservice.api.dto.request.TransacaoRequest;
import com.github.fabriciolfj.contaservice.domain.model.TransacaoModel;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class TransacaoModelMapper {

    private final ModelMapper modelMapper;

    public TransacaoModel toModel(final TransacaoRequest request, final String transactionId) {
        final TransacaoModel transacao = modelMapper.map(request, TransacaoModel.class);
        transacao.setTransacao(transactionId);
        return transacao;
    }
}
