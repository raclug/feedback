package br.com.feedbacks.mappers;

import br.com.feedbacks.dtos.AvaliacaoDTO;
import br.com.feedbacks.entities.AvaliacaoEntity;

import java.util.Date;

public class AvaliacaoMapper {

    private AvaliacaoMapper() {
    }

    public static AvaliacaoEntity toEntity(AvaliacaoDTO dto) {
        return AvaliacaoEntity.builder().descricao(dto.descricao())
                .nota(dto.nota())
                .dataCriacao(new Date())
                .build();
    }
}
