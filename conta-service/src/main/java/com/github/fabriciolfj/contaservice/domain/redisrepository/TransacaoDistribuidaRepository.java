package com.github.fabriciolfj.contaservice.domain.redisrepository;

import com.github.fabriciolfj.contaservice.domain.model.TransacaoDistribuida;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.Cache;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static com.github.fabriciolfj.contaservice.infraestrutura.RedisConfig.CACHE_NAME;

@RequiredArgsConstructor
@Component
public class TransacaoDistribuidaRepository {


    @Qualifier(CACHE_NAME)
    private final Cache cache;

    public Optional<TransacaoDistribuida> get(final String transactionId) {
        return Optional.ofNullable(cache.get(transactionId, TransacaoDistribuida.class));
    }

}
