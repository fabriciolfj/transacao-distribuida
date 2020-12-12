package com.github.fabriciolfj.transacao.domain.cache;

import com.github.fabriciolfj.transacao.domain.model.TransacaoDistribuida;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.Cache;
import org.springframework.stereotype.Component;

import static com.github.fabriciolfj.transacao.infra.RedisConfig.CACHE_NAME;

@Component
@AllArgsConstructor
public class TransacaoDistribuidaCache {

    @Qualifier(CACHE_NAME)
    private final Cache cache;

    public void addTransacao(final TransacaoDistribuida transacaoDistribuida) {
        cache.put(transacaoDistribuida.getTransactionId(), transacaoDistribuida);
    }
}
