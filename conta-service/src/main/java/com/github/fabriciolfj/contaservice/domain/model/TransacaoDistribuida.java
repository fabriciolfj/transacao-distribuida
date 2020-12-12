package com.github.fabriciolfj.contaservice.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@RedisHash("transacao")
public class TransacaoDistribuida {

    private TransacaoStatus status;
    @Indexed
    private String transactionId;
}
