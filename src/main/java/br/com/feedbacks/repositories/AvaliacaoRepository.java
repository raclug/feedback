package br.com.feedbacks.repositories;

import br.com.feedbacks.entities.AvaliacaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvaliacaoRepository extends JpaRepository<AvaliacaoEntity, String>  {
}
