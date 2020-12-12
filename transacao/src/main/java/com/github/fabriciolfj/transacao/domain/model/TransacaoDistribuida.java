package com.github.fabriciolfj.transacao.domain.model;

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

    @Indexed
    private String transactionId;
    private String status;
}
