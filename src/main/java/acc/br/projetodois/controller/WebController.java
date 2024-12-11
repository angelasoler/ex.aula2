package acc.br.projetodois.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import acc.br.projetodois.model.Score;
import acc.br.projetodois.service.ScoreService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class WebController {
    @Autowired
    private ScoreService scoreService;

    // Construtor para injeção de mocked ScoreService para tests
    public WebController(ScoreService scoreService) {
        this.scoreService = scoreService;
    }

    @PostMapping("/score/reset")
    public String resetScore(Model model) {
        scoreService.resetScore();
        Score score = scoreService.getScore();
        model.addAttribute("score", score);
        return "reset-score";
    }

    @ResponseBody
    @GetMapping("/score")
    public Score getScore() {
        return scoreService.getScore();
    }

    @GetMapping("/teste")
    public String teste(@RequestParam(name="escolha") String aEscolha, Model model) {
        String saida = scoreService.processarEscolha(aEscolha);
        Score score = scoreService.getScore();

        model.addAttribute("saida", saida);
        model.addAttribute("aEscolha", aEscolha);
        model.addAttribute("score", score);
        return "resultado";
    }
}