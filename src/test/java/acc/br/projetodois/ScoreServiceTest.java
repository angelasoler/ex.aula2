package acc.br.projetodois;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import acc.br.projetodois.model.Score;
import acc.br.projetodois.repository.ScoreRepository;
import acc.br.projetodois.service.ScoreService;


public class ScoreServiceTest {
    private final ScoreRepository mockRepo = Mockito.mock(ScoreRepository.class);
    private final ScoreService scoreService = new ScoreService(mockRepo);

    @Test
    void testGetScore_whenScoreExists() {
        // Arrange
        Score score = new Score(1, 2, 3);
        when(mockRepo.findById(1)).thenReturn(java.util.Optional.of(score));

        // Act
        Score result = scoreService.getScore();

        // Assert
        assertEquals(1, result.getVitorias());
        assertEquals(2, result.getDerrotas());
        assertEquals(3, result.getEmpates());
        verify(mockRepo, times(1)).findById(1);
    }

    @Test
    void testGetScore_whenScoreDoesNotExist() {
        // Arrange
        when(mockRepo.findById(1)).thenReturn(java.util.Optional.empty());

        // Act
        Score result = scoreService.getScore();

        // Assert
        assertEquals(0, result.getVitorias());
        assertEquals(0, result.getDerrotas());
        assertEquals(0, result.getEmpates());
        verify(mockRepo, times(1)).save(any(Score.class));
    }

    @Test
    void testResetScore() {
        // Arrange
        Score score = new Score(1, 5, 4);
        when(mockRepo.findById(1)).thenReturn(java.util.Optional.of(score));

        // Act
        scoreService.resetScore();
        
        // Assert
        assertEquals(0, score.getVitorias());
        assertEquals(0, score.getDerrotas());
        assertEquals(0, score.getEmpates());
        verify(mockRepo, times(1)).save(score);
    }
}
