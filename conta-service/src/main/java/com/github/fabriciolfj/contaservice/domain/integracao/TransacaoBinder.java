package com.github.fabriciolfj.contaservice.domain.integracao;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface TransacaoBinder {

    String OUT_PUT = "output-tansaction";

    @Output(OUT_PUT)
    MessageChannel output();
}
