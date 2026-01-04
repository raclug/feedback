package br.com.feedbacks.mappers.impl;

import br.com.feedbacks.dtos.AvaliacaoDTO;
import br.com.feedbacks.entities.AvaliacaoEntity;
import br.com.feedbacks.mappers.AvaliacaoMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@AllArgsConstructor
public class AvaliacaoMapperImpl implements AvaliacaoMapper {

    private final ObjectMapper mapper;

    @Override
    public AvaliacaoEntity toEntity(AvaliacaoDTO dto) {
        return AvaliacaoEntity.builder().descricao(dto.descricao())
                .nota(dto.nota())
                .dataCriacao(new Date())
                .build();
    }

    @Override
    public String toString(AvaliacaoDTO dto) throws JsonProcessingException {
        return mapper.writeValueAsString(dto);
    }
}
