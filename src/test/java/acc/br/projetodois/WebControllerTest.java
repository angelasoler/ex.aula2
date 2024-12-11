package acc.br.projetodois;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import acc.br.projetodois.controller.WebController;
import acc.br.projetodois.model.Score;
import acc.br.projetodois.service.ScoreService;
import org.springframework.ui.Model;


public class WebControllerTest {
    private final ScoreService mockService = Mockito.mock(ScoreService.class);
    private final WebController webController = new WebController(mockService);

    @Test
    void testGetScore() {
        // Arrange
        Score score = new Score(1, 2, 3);
        when(mockService.getScore()).thenReturn(score);

        // Act
        Score result = webController.getScore();

        // Assert
        assertEquals(1, result.getVitorias());
        assertEquals(2, result.getDerrotas());
        assertEquals(3, result.getEmpates());
        verify(mockService, times(1)).getScore();
    }

    @Test
    void testTeste() {
        // Arrange
        Model mockModel = Mockito.mock(Model.class);
        Score score = new Score(1, 0, 0);
        when(mockService.getScore()).thenReturn(score);
        when(mockService.processarEscolha("papel")).thenReturn("ganhou");

        // Act
        String viewName = webController.teste("papel", mockModel);

        // Assert
        assertEquals("resultado", viewName);
        verify(mockModel).addAttribute("saida", "ganhou");
        verify(mockModel).addAttribute("aEscolha", "papel");
        verify(mockModel).addAttribute("score", score);
    }
}
