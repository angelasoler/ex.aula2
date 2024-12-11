package acc.br.projetodois.repository;

import org.springframework.data.repository.CrudRepository;

import acc.br.projetodois.model.Score;

public interface ScoreRepository extends CrudRepository<Score, Integer> {
    
}
