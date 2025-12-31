package br.com.feedbacks.functions;

import br.com.feedbacks.dtos.AvaliacaoDTO;
import br.com.feedbacks.repositories.AvaliacaoRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.function.Function;

import static br.com.feedbacks.mappers.AvaliacaoMapper.toEntity;

@Component
@AllArgsConstructor
public class AvaliacaoFunction {

    private final AvaliacaoRepository repo;

    @Bean
    public Function<AvaliacaoDTO, AvaliacaoDTO> gravarAvaliacao() {
        return avaliacao -> {
            if (avaliacao.nota() == null || avaliacao.nota() < 0 || avaliacao.nota() > 10) {
                throw new IllegalArgumentException("Nota deve ser 0 a 10");
            }

            if (!StringUtils.hasLength(avaliacao.descricao())) {
                throw new IllegalArgumentException("Descrição é obrigatória");
            }

            var entity = toEntity(avaliacao);

            repo.save(entity);

            return avaliacao;
        };
    }
}
