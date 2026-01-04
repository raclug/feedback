package br.com.feedbacks.services;

import br.com.feedbacks.dtos.AvaliacaoDTO;

public interface EnviarAvaliacaoService {

    void enviarAvaliacao(AvaliacaoDTO avaliacao);
}
