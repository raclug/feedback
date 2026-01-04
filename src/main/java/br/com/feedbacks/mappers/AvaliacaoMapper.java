package br.com.feedbacks.mappers;

import br.com.feedbacks.dtos.AvaliacaoDTO;
import br.com.feedbacks.entities.AvaliacaoEntity;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface AvaliacaoMapper {

    AvaliacaoEntity toEntity(AvaliacaoDTO dto);

    String toString(AvaliacaoDTO dto) throws JsonProcessingException;
}
