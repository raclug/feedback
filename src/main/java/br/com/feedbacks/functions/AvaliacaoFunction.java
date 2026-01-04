package br.com.feedbacks.functions;

import br.com.feedbacks.dtos.AvaliacaoDTO;
import br.com.feedbacks.mappers.AvaliacaoMapper;
import br.com.feedbacks.repositories.AvaliacaoRepository;
import br.com.feedbacks.services.EnviarAvaliacaoService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.function.Function;

@Component
@AllArgsConstructor
public class AvaliacaoFunction {

    private final AvaliacaoRepository repo;

    private final AvaliacaoMapper avaliacaoMapper;

    private final EnviarAvaliacaoService enviarAvaliacaoService;

    @Bean
    public Function<AvaliacaoDTO, AvaliacaoDTO> gravarAvaliacao() {
        return avaliacao -> {
            if (avaliacao.nota() == null || avaliacao.nota() < 0 || avaliacao.nota() > 10) {
                throw new IllegalArgumentException("Nota deve ser 0 a 10");
            }

            if (!StringUtils.hasLength(avaliacao.descricao())) {
                throw new IllegalArgumentException("Descrição é obrigatória");
            }

            var entity = avaliacaoMapper.toEntity(avaliacao);

            repo.save(entity);

            enviarAvaliacaoService.enviarAvaliacao(avaliacao);

            return avaliacao;
        };
    }
}
